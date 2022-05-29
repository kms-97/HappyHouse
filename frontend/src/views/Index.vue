<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <h1>Happy House</h1>
              <h3>찾고싶은 아파트를 검색해보세요</h3>
              <br />

              <md-menu md-align-trigger id="relatedContainer">
                <md-field class="has-white">
                  <label>키워드로 검색</label>
                  <md-input
                    @input="getRelated"
                    ref="keyword"
                    md-menu-trigger
                  ></md-input>

                  <md-menu-content v-show="keyword" id="listWrapper">
                    <md-list class="listitems">
                      <div class="place">
                        동
                      </div>
                      <div class="listContainer">
                        <template v-if="related.dongs.length">
                          <md-list-item v-for="(dong, idx) in related.dongs">
                            <div
                              class="md-list-item-text"
                              data-type="dong"
                              :data-code="dong.dongCode"
                              :data-value="dong.dongName"
                              @click="setSearchOption"
                            >
                              {{
                                `${dong.sidoName} ${dong.gugunName} ${dong.dongName}`
                              }}
                            </div>
                          </md-list-item>
                        </template>
                        <template v-else>
                          <md-list-item>
                            <div>연관 키워드가 없습니다.</div>
                          </md-list-item>
                        </template>
                      </div>
                    </md-list>
                    <md-divider class="md-inset"></md-divider>
                    <md-list>
                      <div class="place">
                        아파트
                      </div>
                      <div class="listContainer">
                        <template v-if="related.apts.length">
                          <md-list-item v-for="(apt, idx) in related.apts">
                            <div
                              class="md-list-item-text"
                              data-type="apt"
                              :data-code="apt.aptCode"
                              :data-value="apt.aptName"
                              @click="setSearchOption"
                            >
                              <span>{{ `${apt.aptName}` }}</span>
                              <span class="aptdonglist">{{
                                `${apt.dongName} ${apt.jibun}`
                              }}</span>
                            </div>
                          </md-list-item>
                        </template>
                        <template v-else>
                          <md-list-item>
                            <div>연관 키워드가 없습니다.</div>
                          </md-list-item>
                        </template>
                      </div>
                    </md-list>
                  </md-menu-content>
                </md-field>
              </md-menu>
            </div>
          </div>
        </div>
      </div>
    </parallax>
    <!--main header end............................-->

    <!--content start..........-->
    <div class="main main-raised">
      <div class="section section-white">
        <!-- 광고start-->

        <div class="AdContentWrap">
          <div class="container">
            <Ad-View></Ad-View>
          </div>
        </div>
        <!-- 광고end-->
        <div class="container">
          <notice-news></notice-news>
        </div>
      </div>
      <!--content end-->
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { fetchData } from "@/util/request.js";
import * as _ from "lodash";
import NoticeNews from "./components/NoticeNewsView";
import AdView from "./components/AdView";
import { LoginCard } from "@/components";

export default {
  components: {
    NoticeNews,
    AdView,
    LoginCard,
  },
  name: "index",
  bodyClass: "index-page",
  props: {
    image: {
      type: String,
      default: require("@/assets/img/vue-mk-header.jpg"),
    },
  },
  data() {
    return {
      firstname: null,
      email: null,
      password: null,

      keyword: "",
      related: {
        apts: [],
        dongs: [],
      },
      type: null,
    };
  },
  methods: {
    ...mapActions("locateStore", ["setSelectedLocate"]),
    ...mapActions("searchStore", ["setType", "setCode", "setKeyword"]),
    getRelated: _.debounce(async function() {
      if (!this.$refs.keyword) return;
      const val = this.$refs.keyword.$el.value;

      if (!val || val === this.keyword) {
        this.keyword = val ? val : "";
        return;
      }

      const res = await fetchData(`/relate/${val}`);
      if (res.isError) {
      } //에러처리
      else {
        this.keyword = val;
        this.related = res.data;
      }
    }, 300),
    setSearchOption(e) {
      const $el = e.currentTarget;
      const type = $el.dataset.type;
      const code = $el.dataset.code;
      const val = $el.dataset.value;

      const Promises = [
        this.setType(type),
        this.setCode(code),
        this.setKeyword(val),
      ];

      Promise.allSettled(Promises).then(() => {
        this.linkTo("/landing");
      });
    },
    linkTo(link) {
      this.$router.push(link);
    },
  },
  computed: {
    ...mapState("locateStore", ["locates"]),
    ...mapState("userStore", ["isLogin", "nickname", "auth"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
      };
    },
  },
  created() {
    this.setType("");
    this.setCode("");
    this.setKeyword("");
  },
};
</script>

<style lang="scss" scoped>
.md-list-item-content {
  cursor: pointer;
}
.md-list {
  padding: 0 0;
}
</style>
<style scoped>
.md-menu {
  width: 650px;
}
.md-list {
  padding: 0 0;
}
.md-list-item {
  margin-bottom: 0px;
}
.listitems {
  padding: 0 0;
}
.listContainer {
  cursor: pointer;
  max-height: 10vh;
  overflow-y: scroll;
}

/* 스크롤바 설정*/
.listContainer::-webkit-scrollbar {
  /* 스크롤바 막대 너비 설정 */
  width: 6px;
}
/* 스크롤바 막대 설정*/
.listContainer::-webkit-scrollbar-thumb {
  /* 스크롤바 막대 높이 설정 */
  height: 17%;
  background-color: #344b26;
  /* 스크롤바 둥글게 설정 */
  border-radius: 10px;
}
/* 스크롤바 뒷 배경 설정*/
.listContainer::-webkit-scrollbar-track {
  background-color: rgba(0, 0, 0, 0);
}

.md-list-item-text {
  padding: 5px 0;
}
.md-menu-content {
  max-width: none !important;
  width: 650px;
}
#listWrapper {
  background-color: #afafaf;
  padding: 0 0;
}
.aptdonglist {
  padding: 5px 0;
  font-size: 8px;
  color: #afafaf;
}
.place {
  text-align: center;
  border-bottom: 1px solid;
  color: #000;
  font-weight: bold;
  padding: 5px 0;
}
</style>

<style lang="scss">
.section-download {
  .md-button + .md-button {
    margin-left: 5px;
  }
}
.listitems {
  padding: 0 0;
}

.aptdonglist {
  padding: 5px 0;
  font-size: 8px;
  color: #afafaf;
}
@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}
</style>
