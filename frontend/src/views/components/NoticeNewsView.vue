<template>
  <div class="wrapper">
    <hr />
    <div id="navigation-pills">
      <div class="md-layout">
        <div class="md-layout-item md-size-50 md-small-size-100">
          <div class="title" style="position: relative">
            <h3 style="display: inline-block;"><small>공지사항</small></h3>
            <small
              style="cursor:pointer; position: absolute; bottom: 0; right: 0;"
              @click="linkTo({ name: 'notice' })"
              >전체보기</small
            >
          </div>
          <md-list>
            <md-list-item v-for="(item, idx) in notices.slice(0, 5)">
              {{ item.title }} <code style="cursor: pointer;" @click="noticeModalOpen(item)">>></code>
            </md-list-item>
          </md-list>
        </div>
        <div class="md-layout-item md-size-50 md-small-size-100">
          <div class="title">
            <h3><small>부동산 뉴스</small></h3>
          </div>
          <md-list>
            <md-list-item
              v-for="(item, idx) in articles"
              :href="item.link"
              target="_blank"
            >
              <div
                class="notice-click"
                v-html="item.title"
                style="text-overflow: ellipsis; width: 90%; overflow: hidden;"
              ></div>
              <code>>></code>
            </md-list-item>
          </md-list>
        </div>
      </div>
    </div>

    <!-- notice modal -->
    <md-dialog-alert
    :md-active.sync="noticeModalActive"
    :md-title="noticeModalTitle"
    :md-content="noticeModalContent"
    md-confirm-text="닫기" />
  </div>


</template>

<script>
import { Tabs } from "@/components";
import { fetchData } from "@/util/request.js";

export default {
  components: {
    Tabs,
  },
  data() {
    return {
      notices: [],
      articles: [],
      noticeModalActive: false,
      noticeModalTitle: "",
      noticeModalContent: "",
    };
  },
  methods: {
    async getNotice() {
      try {
        const res = await fetchData("/board", {
          method: "GET",
          headers: {
            Authorization: this.token,
            "Content-Type": "application/json",
          },
        });

        if (!res.isError) this.notices = res.data.slice(0, 5);
        else
          throw {
            status: res.data.status,
            message: res.data.message,
          };
      } catch (e) {
        console.log(e);
      }
    },
    linkTo(link) {
      this.$router.push(link);
    },
    async getArticles() {
      try {
        const res = await fetchData(`/news`, {
          method: "GET",
        });
        console.log(res);
        if (!res.isError) this.articles = res.data.items;
        else
          throw {
            status: res.data.status,
            message: res.data.message,
          };
      } catch (e) {
        console.log(e);
      }
    },
    noticeModalOpen(notice) {
      this.noticeModalTitle = notice.title;
      this.noticeModalContent = notice.content;
      this.noticeModalActive = true;
    }
  },
  created() {
    this.getNotice();
    this.getArticles();
  },
};
</script>

<style lang="css" scoped>
.wrapper {
  margin: 20px 0 50px 0;
}
.notice-view {
  cursor: pointer;
}
</style>
