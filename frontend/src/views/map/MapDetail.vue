<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="btname text-left">
            <div class="name">
              <h2>
                <b>{{ aptInfo.aptName }}</b>
              </h2>
              <h6 class="dongName">
                {{ aptInfo.sidoName }} {{ aptInfo.gugunName }}
                {{ aptInfo.dongName }} {{ aptInfo.jibun }}
              </h6>
              <h6 class="detailName">
                {{ aptInfo.roadName }} {{ aptInfo.roadNameBonbun }} -
                {{ aptInfo.roadNameBubon }}
              </h6>
              <!-- 좋아요 -->
              <span class="hearticon" v-if="isLogin" style="float: right">
                <heart-icon :code="aptCode" :active="interested.includes(aptCode)"></heart-icon>
              </span>
            </div>
          </div>

          <!--아파트사진start-->
          <div id="aptimglist">
            <div class="aptimgdetail">
              <div class="row">
                <div class="col-md-8">
                  <div class="thumbnail">
                    <img
                      :src="getImg()"
                      style="width:100%"
                    />
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="thumbnail">
                    <img
                      src="@/img/empty2.png"
                      style="width:100%"
                    />
                    <div class="thumbnail">
                      <img
                        src="@/img/empty2.png"
                        style="width:100%"
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--aptimgdetail end -->
          </div>
          <!--아파트사진 end-->
          <div id="aptaboutwrap">
            <nav-tabs-card no-label tabs-plain>
              <template slot="content" class="wrap">
                <md-tabs
                  class="md-success"
                  md-alignment="left"
                  style="font-size:20px;"
                >
                  <md-tab :md-label="area" v-for="(area, idx) in areas">
                    <map-detail-list :deals="deals.get(area)"></map-detail-list>
                  </md-tab>
                </md-tabs>
              </template>
            </nav-tabs-card>

            <Map-Place :lat="aptInfo.lat" :lng="aptInfo.lng"></Map-Place>
          </div>
        </div>
        <!--container END-->
      </div>
    </div>
  </div>
</template>

<script>
import MapDetailList from "./MapDetailList.vue";
import MapPlace from "./MapPlace.vue";
import HeartIcon from "@/views/components/HeartIcon.vue";
import randomImg from '@/util/img.js';
import { NavTabsCard } from "@/components";
import { fetchData } from "@/util/request.js";
import { mapState, mapActions } from "vuex";

export default {
  components: {
    NavTabsCard,
    MapDetailList,
    MapPlace,
    HeartIcon,
  },
  bodyClass: "profile-page",
  data() {
    return {
      // 기본값 설정 가능
      aptCode: null,
      aptInfo: {
        aptName: "",

        // 지번 주소
        sidoName: "",
        gugunName: "",
        dongName: "",
        jibun: null,

        // 도로명 주소
        roadName: "",
        roadNameBonbun: "",
        roadNameBubun: "",

        // 좌표
        lat: "",
        lng: "",
      },
      areas: [],
      deals: new Map(),
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg"),
    },
  },
  computed: {
    ...mapState("userStore", ["isLogin", "id", "interested"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    ...mapActions("recentStore", ["addRecent"]),
    linkTo(link) {
      this.$router.push(link);
    },
    getImg() {
      return require(`@/img/apt/apt${randomImg()}.jpg`);
    },
  },
  created() {
    this.aptCode = this.$route.params.code;
    fetchData(`/dealHistory/${this.aptCode}`)
      .then((res) => res.data)
      .then((res) => {
        this.aptInfo = {
          ...this.aptInfo,
          aptName: res.aptName,
          sidoName: res.sidoName,
          dongName: res.dongName,
          gugunName: res.gugunName,
          jibun: res.jibun,
          roadName: res.roadName,
          roadNameBonbun: res.roadNameBonbun,
          roadNameBubun: res.roadNameBubun,
          lat: res.lat,
          lng: res.lng,
        };

        this.addRecent({
          ...this.aptInfo,
          aptCode: this.aptCode,
        });
        res.history.forEach((el) => {
          // 원래는 소수점 3번째자리 이하 버림.
          // 소수점 이하로 달라지는게 너무 많아서 그냥 내림으로 처리함.
          const area = Math.floor(el.area);

          if (this.deals.has(area)) this.deals.get(area).push(el);
          else this.deals.set(area, [el]);
        });

        this.areas = [...Array.from(this.deals.keys()).sort((a, b) => a - b)];
      })
      .catch((e) => console.log(e));
  },
};
</script>

<style scoped>
.thumbnail {
  margin: 20px 0;
}
.thumbnail>img{
  background-color: #344B26;
  object-fit: cover;
}
.container {
  padding: 50px;
}
.shadow-textarea textarea.form-control::placeholder {
  font-weight: 300;
  border: 0px;
}
.shadow-textarea textarea.form-control {
  padding-left: 0.8rem;
}
.section {
  padding: 0;
}
.dongName {
  color: #878787;
}
.detailName {
  color: #878787;
  font-size: small;
}
</style>
