<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="btname description text-center">
            <div class="name">
              <h3>공지사항 등록</h3>
            </div>
          </div>
          <!--글쓰는 칸-->
          <div id="writecontent">
            <div class="texttitle">
              <div class="form-group shadow-textarea">
                <label for="exampleFormControlTextarea6"></label>
                <textarea
                  class="form-control z-depth-1"
                  v-model="title"
                  rows="1"
                  placeholder="제목을 입력해 주세요."
                ></textarea>
              </div>
            </div>
            <div class="cont">
              <div class="form-group shadow-textarea">
                <label for="exampleFormControlTextarea6"></label>
                <textarea
                  class="form-control z-depth-1"
                  v-model="content"
                  rows="20"
                  placeholder="내용을 입력해 주세요."
                ></textarea>
              </div>
            </div>
            <!--버튼 -->
            <md-button
              id="bt"
              class="button md-simple md-round md-sm float-right"
              @click="linkTo({name: 'notice'})"
              >취소</md-button
            >
            <md-button
              id="bt"
              class="button md-simple md-round md-sm float-right"
              @click="regist"
              >등록</md-button
            >
          </div>
          <!--글쓰는 칸END-->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Pagination } from "@/components";
import { mapState, mapActions } from "vuex";

export default {
  components: { Pagination },
  bodyClass: "profile-page",
  data() {
    return {
      title: "",
      content: "",
      type: "2",
    }
  },
  props: {
  },
  computed: {
    ...mapState("userStore", ["id", "token"]),
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
    async regist() {
      try {
        const res = await fetch('http://localhost:81/board', {
          method: "POST",
          headers: {
            'Authorization': this.token,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            type: this.type,
            title: this.title,
            content: this.content,
            writerId: this.id,
          })
        })

        if (res.ok) this.linkTo({name: 'notice'})
        else throw {
          status: res.status,
          message: res.statusText,
        }
      } catch(e) {
        alert("오류가 발생하였습니다.")
      }
    },
  },
};
</script>

<style lang="scss" scoped>
#writecontent {
  width: 100%;
  height: 100vh;
}
.shadow-textarea textarea.form-control::placeholder {
  font-weight: 300;
  border: 0px;
}
.shadow-textarea textarea.form-control {
  padding-left: 0.8rem;
}
textarea {
  resize: none;
}
.ftitle {
  border: 1px solid #888;
  height: 30px;
}
.fcont {
  border: 1px solid #888;
}
#bt {
  place-self: flex-end;
  width: 100px;
}
.button {
  border: 1px solid;
}
.pag {
  justify-content: center;
}
.section {
  padding: 0;
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
</style>
