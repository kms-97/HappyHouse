<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="name text-center">
            <h3><b>공지사항</b></h3>
          </div>
          <!--아코디언 공지사항 시작-->
          <div id="accordion">
            <template v-for="(notice, idx) in notices" v-key="idx">
              <details :open="idx === 0 ? true : false">
                <summary>
                  {{ notice.title }}
                  <small>{{ notice.regTime }}</small>
                  <span style="float: right;"><small v-if="auth === 'ROLE_ADMIN'" @click="deleteNotice($event)" :data-no="notice.no">삭제</small></span>
                </summary>
                <div>
                  {{ notice.content }}
                </div>
              </details>
            </template>

            <!--글쓰기 버튼-->
            <md-button
              id="bt"
              class="button md-simple md-round md-sm"
              @click="linkTo({ name: 'noticeWrite'})"
              v-if="auth === 'ROLE_ADMIN'"
              >등록
            </md-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { fetchData } from "@/util/request.js";
import { mapState, mapActions } from "vuex";

export default {
  bodyClass: "profile-page",
  data() {
    return {
      notices: [],
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg"),
    },
  },
  computed: {
    ...mapState('userStore', ["auth"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    linkTo(link) {
      this.$router.push(link);
    },
    async deleteNotice(e) {
      const no = e.target.dataset.no;
      
      try {
        const res = await fetch(`http://localhost:81/board/${no}`, {
          method: 'DELETE',
          headers: {
            'Authorization': this.token,
            'Content-Type': 'application/json',
          },
        })

        if (res.ok) this.getNotice();
        else throw {
          status: res.status,
          message: res.statusText,
        }
      } catch(e) {
        console.log("오류가 발생하였습니다. ", e);
      }
    },
    async getNotice() {
      try {
        const res = await fetchData('/board', {
          method: "GET",
          headers: {
            'Authorization': this.token,
            'Content-Type': 'application/json',
          },
        })

        if (!res.isError) this.notices = res.data;
        else throw { 
          status: res.data.status,
          message: res.data.message,
        };
      } catch(e) {
        console.log(e)
      }
    },
  },
  async created() {
    this.getNotice();
  }
};
</script>

<style lang="scss" scoped>
#accordion {
  margin: 50px 0;
}
.name {
  font-weight: bolder;
  margin: 25px 0;
}

.pag {
  justify-content: center;
}
.section {
  padding: 0;
}
.qnaitem {
  width: 100%;
}
.profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*="tab-pane-"] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }
}
@import url("https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800;900&display=swap");

*,
*:after,
*:before {
  box-sizing: border-box;
}

:root {
  font-size: 16px;
}

*:focus {
  outline: none;
}

details div {
  color: #6b6a6a;
  border-left: 1px solid #d3d3d3;
  border-right: 1px solid #d3d3d3;
  border-bottom: 1px solid #d3d3d3;
  padding: 2em;
}

details div > * + * {
  margin-top: 1.5em;
}

details + details {
  margin-top: 0.5rem;
}

summary {
  list-style: none;
}

summary::-webkit-details-marker {
  display: none;
}

summary {
  border: 1px solid #d3d3d3;
  padding: 0.75em 1em;
  cursor: pointer;
  position: relative;
  padding-left: calc(1.75rem + 0.75rem + 0.75rem);
}

summary:before {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  left: 0.75rem;
  content: "↓";
  width: 1.75rem;
  height: 1.75rem;
  background-color: #344b26;
  color: #fff;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  flex-shrink: 0;
}

details[open] summary {
  font-weight: bold;
  background-color: #eee;
}

details[open] summary:before {
  content: "↑";
}

summary:hover {
  background-color: #eee;
}

a {
  color: inherit;
  font-weight: 600;
  text-decoration: none;
  box-shadow: 0 1px 0 0;
}

a:hover {
  box-shadow: 0 3px 0 0;
}

code {
  font-family: monospace;
  font-weight: 600;
}

.button {
  border: 1px solid;
  float: right;
}
</style>
