<template>
  <div>
    <i class="bi bi-heart-fill" @click="checked($event)" v-show="act"></i>
    <i class="bi bi-heart" @click="checked($event)" v-show="!act"></i>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { fetchData } from "@/util/request";

export default {
  data() {
    return {};
  },
  props: {
    code: {
      type: String,
      default: "",
    },
    active: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    ...mapActions("userStore", ["setInterested"]),
    async checked(e) {
      const $el = e.target;
      const classList = $el.classList;

      try {
        const res = await fetchData(`/interested/${this.id}/${this.code}`, {
          method: classList.contains("bi-heart") ? "POST" : "DELETE",
          headers: {
            Authorization: this.token,
            "Content-Type": "application/json",
          },
        });

        console.log(res);
        if (!res.isError) {
          this.$emit("likeSetter", res.data);
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
  },
  computed: {
    ...mapState("userStore", ["isLogIn", "id", "interested"]),
    act() {
      return this.active ? true : false;
    },
  },
};
</script>

<style scoped>
* {
  color: crimson;
}
.bi-heart {
  font-size: 30px;
  line-height: 30px;
  color: crimson;
}

.bi-heart-fill {
  font-size: 30px;
  line-height: 30px;
  color: crimson;
}
</style>
