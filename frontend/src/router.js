import Vue from "vue";
import Router from "vue-router";
import Index from "@/views/Index.vue";
import MainNavbar from "@/layout/MainNavbar.vue";
import MainFooter from "@/layout/MainFooter.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "index",
      components: {
        default: Index,
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
    {
      path: "/landing",
      name: "landing",
      components: {
        default: () => import("@/views/Landing.vue"),
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
    {
      path: "/mapdetail/:code",
      name: "mapdetail",
      components: {
        default: () => import("@/views/map/MapDetail.vue"),
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: "/mypage",
      name: "mypage",
      components: {
        default: () => import("@/views/MyPage.vue"),
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
      },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: "/login",
      name: "login",
      components: {
        default: () => import("@/views/Login.vue"),
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
      },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: "/signup",
      name: "signup",
      components: {
        default: () => import("@/views/Signup.vue"),
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
      },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: "/notice",
      name: "notice",
      components: {
        default: () => import("@/views/Notice.vue"),
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: "/write",
      name: "noticeWrite",
      components: {
        default: () => import("@/views/board/NoticeWrite.vue"),
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: "/oauth/redirect/kakao",
      name: "kakao",
      components: {
        default: () => import("@/views/oauth/kakao.vue"),
      }
    },
    {
      path: "/oauth/redirect/naver",
      name: "naver",
      components: {
        default: () => import("@/views/oauth/naver.vue"),
      }
    }
  ],
  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});
