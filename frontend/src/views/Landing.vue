<template>
  <div class="wrapper">
    <parallax class="section page-header2  header-filter" :style="headerStyle">
      <!-- <div class="container "></div> -->
    </parallax>
    <div class="main main-raised" style="height: 80vh;">
      <div style="height: 100%;">
        <!--map넣을 공간start-->
        <div id="map" style="width: 100%; height: 100%" ref="map"></div>
        <!--map넣을 공간end-->
        <div class="container" style="z-index: 10;">
          <div class="searchMap">
            <!-- search start-->
            <div class="md-layout">
              <div class="md-layout-item md-size-100">
                <nav-tabs-card no-label>
                  <template slot="content">
                    <md-tabs
                      class="md-success"
                      :md-active-tab="activeTab"
                      @md-changed="setActiveTab"
                    >
                      <md-tab
                        id="tab-apt"
                        md-label="아파트명"
                        md-icon="face"
                        ref="aptTab"
                        class="search-size"
                      >
                        <p>
                          <md-field>
                            <label>아파트명으로 찾기</label>
                            <md-input
                              :value="aptState.keyword"
                              ref="aptKeyword"
                              @input="getApts"
                            ></md-input>
                          </md-field>
                        </p>
                      </md-tab>

                      <md-tab
                        id="tab-dong"
                        md-label="법정동"
                        md-icon="face"
                        ref="dongTab"
                      >
                        <p>
                          <md-field>
                            <label>동으로 찾기</label>
                            <md-input
                              :value="dongState.keyword"
                              ref="dongKeyword"
                              @input="getApts"
                              class="search-size"
                            ></md-input>
                          </md-field>
                        </p>
                      </md-tab>
                    </md-tabs>

                    <!-- search Result -->
                    <md-list
                      style="cursor: pointer"
                      ref="tabAptList"
                      v-show="
                        activeTab === 'tab-apt' && aptState.results.length
                      "
                    ></md-list>
                    <b-pagination
                      v-model="aptState.page"
                      :total-rows="aptState.results.length"
                      :per-page="aptState.perPage"
                      size="sm"
                      limit="3"
                      align="center"
                      v-show="
                        activeTab === 'tab-apt' && aptState.results.length
                      "
                    ></b-pagination>
                    <md-list
                      style="cursor: pointer; margin:5px 0;"
                      ref="tabDongList"
                      v-show="
                        activeTab === 'tab-dong' && dongState.results.length
                      "
                    ></md-list>
                    <b-pagination
                      v-model="dongState.page"
                      :total-rows="dongState.results.length"
                      :per-page="dongState.perPage"
                      size="sm"
                      limit="3"
                      align="center"
                      v-show="
                        activeTab === 'tab-dong' && dongState.results.length
                      "
                    ></b-pagination>
                  </template>
                </nav-tabs-card>
              </div>
            </div>
            <!-- search end-->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { NavTabsCard } from "@/components";
import { fetchData } from "@/util/request.js";
import * as _ from "lodash";
import Map from "@/components/maps/MainMap.js";
import Pagination from "@/components/Pagination.js";

export default {
  components: {
    NavTabsCard,
  },
  data() {
    return {
      // 초기 활성화 탭
      activeTab: null,

      // 키워드 관련
      searchType: "",
      aptState: {
        keyword: "",
        code: "",
        results: [],
        pagination: null,
        page: 1,
        perPage: 5,
        $list: null,
        $search: null,
        url: `/aptName/`,
      },
      dongState: {
        keyword: "",
        code: "",
        results: [],
        pagination: null,
        page: 1,
        perPage: 5,
        $list: null,
        $search: null,
        url: `/aptDong/`,
      },

      // 맵 객체 ref
      $map: null,
    };
  },
  computed: {
    ...mapState("searchStore", ["type", "code", "keyword"]),
    activeState() {
      return this.activeTab === "tab-apt" ? this.aptState : this.dongState;
    },
    page() {
      return this.activeState.page;
    },
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
      };
    },
  },
  watch: {
    page() {
      this.displayResultsOnMap();
    },
  },
  created() {
    // init keyword
    if (this.keyword) {
      if (this.type === "apt") {
        this.activeTab = "tab-apt";
        this.activeState.keyword = this.keyword;
        this.activeState.code = this.code;

        fetchData(`/aptCode/${this.activeState.code}`)
          .then((res) => (this.activeState.results = res.data))
          .then(
            () =>
              (this.activeState.pagination = new Pagination(
                this.activeState.results,
                this.activeState.perPage
              ))
          )
          .then(() => this.displayResultsOnMap())
          .catch((e) => console.log(e));
      } else if (this.type === "dong") {
        this.activeTab = "tab-dong";
        this.activeState.keyword = this.keyword;
        this.activeState.code = this.code;

        fetchData(`/aptList/${this.activeState.code}`)
          .then((res) => (this.activeState.results = res.data))
          .then(
            () =>
              (this.activeState.pagination = new Pagination(
                this.activeState.results,
                this.activeState.perPage
              ))
          )
          .then(() => this.displayResultsOnMap())
          .catch((e) => console.log(e));
      }
    }
  },
  methods: {
    getApts: _.debounce(async function() {
      const state = this.activeState;
      const val = state.$search.value;

      if (!val || val === state.keyword) {
        state.keyword = val ? val : "";
        if (state.keyword === "") state.results = [];
        return;
      }

      const res = await fetchData(`${state.url}${val}`);
      if (res.isError) {
      } // 에러처리
      else {
        state.keyword = val;
        state.results = res.data;
        state.pagination = new Pagination(state.results, state.perPage);

        this.displayResultsOnMap();
      }
    }, 300),

    setActiveTab(e) {
      if (!e) return;
      this.activeTab = e;

      this.displayResultsOnMap();
      //this.searchType = e.split("-")[1];
    },

    displayResultsOnMap() {
      if (!this.activeState.pagination) return;
      const results = this.activeState.pagination.getData(
        this.activeState.page
      );
      if (!results.length) return;

      this.$map.displayPlaces(results);
      this.displayResultsList(this.$map.getEl());
    },

    displayResultsList(el) {
      const $li = this.activeState.$list;
      this.removeAllChildNods($li);

      el.forEach((e) => {
        $li.appendChild(e);
      });
    },

    removeAllChildNods(el) {
      while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
      }
    },

    linkTo(link) {
      this.$router.push(link);
    },
  },
  mounted() {
    this.$map = new Map({
      $target: this.$refs.map,
      options: {
        lat: 33.450701,
        lng: 126.570667,
        level: 4,
        draggable: true,
        zoomable: true,
      },
    });

    // state link to dom
    this.aptState.$list = this.$refs.tabAptList.$el;
    this.aptState.$search = this.$refs.aptKeyword.$el;
    this.dongState.$list = this.$refs.tabDongList.$el;
    this.dongState.$search = this.$refs.dongKeyword.$el;

    // event delegation
    this.aptState.$list.addEventListener("click", (e) => {
      const $li = e.target.closest(".aptItem");
      if (!$li) return;

      const aptCode = $li.dataset.code;
      this.linkTo({ name: "mapdetail", params: { code: aptCode } });
    });

    this.dongState.$list.addEventListener("click", (e) => {
      const $li = e.target.closest(".aptItem");
      if (!$li) return;

      const aptCode = $li.dataset.code;
      this.linkTo({ name: "mapdetail", params: { code: aptCode } });
    });
  },
  props: {
    image: {
      type: String,
      default: require("@/assets/img/city-profile.jpg"),
    },
  },
};
</script>
<style scoped>
.aptItem {
  padding: 5px 0;
}
</style>

<style lang="scss" scoped>
.md-list-item {
  padding: 5px 0;
}
.main {
  height: 100%;
}
.searchMap {
  position: fixed;
  width: 300px;
  top: 150px;
  left: 50px;
}
.aptItem {
  padding: 5px 0;
}
.aptListitems {
  padding: 5px 0;
}
.page-header2 {
  color: #0000 important;
}
.md-card-actions.text-center {
  display: flex;
  justify-content: center !important;
}
.contact-form {
  margin-top: 30px;
}

.md-has-textarea + .md-layout {
  margin-top: 15px;
}
.md-ripple {
  font-size: 15px;
}
</style>
