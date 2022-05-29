<template>
  <div class="wrapper">
    <div id="AddView">
      <!--최근 조회 아파트 3개-->
      <div class="nametag text-center " style="margin-top:35px;">
        <h2 class="Topfont">어떤 공간을 찾고 있나요?</h2>
        <span class="tim-note">최근 조회한 아파트입니다.</span>
      </div>
      <div class="md-layout h-25" ref="recentList">
        <template v-for="(i, idx) in [0, 1, 2]">
          <template v-if="recent && recent[i]">
            <div class="md-layout-item md-size-33 mx-auto md-small-size-100">
              <md-card>
                <!--좋아요 표시 START-->
                <div class="hearticon" v-if="isLogin">
                  <heart-icon
                    :code="recent[i].aptCode"
                    @likeSetter="setLiked"
                    :active="interested.includes(recent[i].aptCode)"
                  ></heart-icon>
                </div>
                <!--좋아요 END-->
                <div class="sub" :data-aptCode="recent[i].aptCode">
                  <div class="aboutSub">
                    <h2>{{ recent[i].aptName }}</h2>
                  </div>
                  <carousel :per-page="1">
                    <!--만약 이미지 넣을꺼면 이곳에 넣으면 됩니다-->
                    <img :src="getImg()" :alt="recent[i].aptName" height="250px" />
                    <!--만약 이미지 넣을꺼면 이곳에 넣으면 됩니다-->
                  </carousel>
                </div>
              </md-card>
            </div>
          </template>

          <template v-else>
            <div class="md-layout-item md-size-33 mx-auto md-small-size-100">
              <md-card>
                <div class="sub">
                  <div class="aboutSub">
                    <h2>Empty</h2>
                  </div>
                  <carousel :per-page="1">
                    <!--만약 이미지 넣을꺼면 이곳에 넣으면 됩니다-->
                    <img :src="empty" alt="empty" height="250px" />
                    <!--만약 이미지 넣을꺼면 이곳에 넣으면 됩니다-->
                  </carousel>
                </div>
              </md-card>
            </div>
          </template>
        </template>
      </div>
      <!--최근 조회 아파트 3개 END-->

      <!--좋아요 아파트 3개 START-->
      <template v-if="isLogin">
        <div class="nametag text-center ">
          <h2 class="Topfont">내가 찾은 나만의 아파트</h2>
          <span class="tim-note">당신이 눈여겨 본 공간</span>
        </div>
        <div class="md-layout h-25" ref="likedList">
          <template v-for="(i, idx) in [0, 1, 2]">
            <template v-if="interested && liked[i]">
              <div class="md-layout-item md-size-33 mx-auto md-small-size-100">
                <md-card>
                  <!--좋아요 표시 START-->
                  <div class="hearticon" v-if="isLogin">
                    <heart-icon
                      :code="liked[i].aptCode"
                      @likeSetter="setLiked"
                      :active="interested.includes(liked[i].aptCode)"
                    ></heart-icon>
                  </div>
                  <!--좋아요 END-->

                  <div class="sub" :data-aptCode="liked[i].aptCode">
                    <div class="aboutSub">
                      <div class="hearticon"></div>
                      <h2>{{ liked[i].aptName }}</h2>
                    </div>
                    <carousel :per-page="1">
                      <!--만약 이미지 넣을꺼면 이곳에 넣으면 됩니다-->
                      <img :src="getImg()" :alt="liked[i].aptName" height="250px" />
                      <!--만약 이미지 넣을꺼면 이곳에 넣으면 됩니다-->
                    </carousel>
                  </div>
                </md-card>
              </div>
            </template>

            <template v-else>
              <div class="md-layout-item md-size-33 mx-auto md-small-size-100">
                <md-card>
                  <div class="sub">
                    <div class="aboutSub">
                      <h2>Empty</h2>
                    </div>
                    <carousel :per-page="1">
                      <!--만약 이미지 넣을꺼면 이곳에 넣으면 됩니다-->
                      <img :src="empty" alt="empty" height="250px" />
                      <!--만약 이미지 넣을꺼면 이곳에 넣으면 됩니다-->
                    </carousel>
                  </div>
                </md-card>
              </div>
            </template>
          </template>
        </div>
      </template>
      <!--좋아요  END-->

      <!--랜덤 START-->
      <div class="nametag text-center ">
        <h2 class="Topfont">{{ random.dongName }} 추천 아파트</h2>
        <span class="tim-note">이 동네는 어떠신가요?</span>
      </div>
      <div class="md-layout h-25" ref="randList">
        <template v-for="(i, idx) in [0, 1, 2]">
          <template v-if="random && random.randomApt && random.randomApt[i]">
            <div class="md-layout-item md-size-33 mx-auto md-small-size-100">
              <md-card>
                <!--좋아요 표시 START-->
                <div class="hearticon" v-if="isLogin">
                  <heart-icon
                    :code="random.randomApt[i].aptCode"
                    @likeSetter="setLiked"
                    :active="interested.includes(random.randomApt[i].aptCode)"
                  ></heart-icon>
                </div>
                <!--좋아요 END-->

                <div class="sub" :data-aptCode="random.randomApt[i].aptCode">
                  <div class="aboutSub">
                    <div class="hearticon"></div>
                    <h2>{{ random.randomApt[i].aptName }}</h2>
                  </div>
                  <carousel :per-page="1">
                    <!--만약 이미지 넣을꺼면 이곳에 넣으면 됩니다-->
                    <img :src="getImg()" :alt="random.randomApt[i].aptName" height="250px" />
                    <!--만약 이미지 넣을꺼면 이곳에 넣으면 됩니다-->
                  </carousel>
                </div>
              </md-card>
            </div>
          </template>

          <template v-else>
            <div class="md-layout-item md-size-33 mx-auto md-small-size-100">
              <md-card>
                <div class="sub">
                  <div class="aboutSub">
                    <h2>Empty</h2>
                  </div>
                  <carousel :per-page="1">
                    <!--만약 이미지 넣을꺼면 이곳에 넣으면 됩니다-->
                    <img :src="empty" alt="empty" height="250px" />
                    <!--만약 이미지 넣을꺼면 이곳에 넣으면 됩니다-->
                  </carousel>
                </div>
              </md-card>
            </div>
          </template>
        </template>
      </div>
      <!--랜덤 END-->
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { fetchData } from "@/util/request.js";
import randomImg from "@/util/img.js";
import heartIcon from "./HeartIcon";
import HeartIcon from "./HeartIcon.vue";

//const images = require.context('~assets/images/charts/'. false, /\.png$/)

export default {
  components: { heartIcon, HeartIcon },
  data() {
    return {
      empty: require("@/img/empty.png"),

      liked: [],
      random: [],
    };
  },
  methods: {
    ...mapActions("userStore", ["setInterested"]),
    setLiked(liked) {
      this.liked = [...liked];
    },
    linkTo(link) {
      this.$router.push(link);
    },
    getImg() {
      return require(`@/img/apt/apt${randomImg()}.jpg`);
    },
  },
  computed: {
    ...mapState("userStore", ["isLogin", "id", "interested"]),
    ...mapState("recentStore", ["recent"]),
  },
  async created() {
    try {
      const res = await fetchData("/getRandomApt/5", {
        method: "GET",
        headers: {
          Authorization: this.token,
          "Content-Type": "application/json",
        },
      });

      if (!res.isError) this.random = res.data;
      else
        throw {
          status: res.data.status,
          message: res.data.message,
        };
    } catch (e) {
      console.log(e);
    }

    if (!this.isLogin) return;
    try {
      const res = await fetchData(`/interested/${this.id}`, {
        method: "GET",
        headers: {
          Authorization: this.token,
          "Content-Type": "application/json",
        },
      });

      if (!res.isError) {
        this.liked = [...res.data];
        this.setInterested(res.data.map((el) => el.aptCode));
      } else
        throw {
          status: res.data.status,
          message: res.data.message,
        };
    } catch (e) {
      console.log(e);
    }
  },
  mounted() {
    [this.$refs.recentList, this.$refs.likedList, this.$refs.randList].forEach(
      (el) => {
        if (el) {
          el.addEventListener("click", (e) => {
            const sub = e.target.closest(".sub");
            if (!sub) return;

            const code = sub.dataset.aptcode;
            if (!code) return;
            this.linkTo({ name: "mapdetail", params: { code: code } });
          });
        }
      }
    );
  },
};
</script>
<style scoped>
.md-card img {
  height: 250px;
  object-fit: cover;
}
.tim-note {
  color: #8b8b8b;
}
.nametag {
  line-height: 2px;
  letter-spacing: -1px;
}
.Topfont {
  color: rgba(97, 97, 97);
  font-weight: bold;
}
.VueCarousel {
  cursor: pointer;
  background-color: #344b26;
  height: 250px;
}
.VueCarousel-wrapper {
  background-color: #d3d3d3;
  height: 250px;
  width: 350px;
}
.sub {
  flex-direction: column;
  align-items: center;

  display: flex;
  height: 100%;
  justify-content: center;
  position: relative;
  z-index: 2;
}
.hearticon {
  z-index: 10;
  right: 10px;
  top: 10px;
  position: absolute;
}

.aboutSub {
  font-size: 15px;
  text-align: center;
  z-index: 10;
  color: rgba(255, 255, 255, 0.3);
  position: absolute;
}

.sub:hover > .aboutSub {
  cursor: pointer;
  color: rgba(255, 255, 255, 1);
  font-weight: bolder;
}

.sub:hover img {
  filter: brightness(50%);
}
</style>
