<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="green">
              <h4 slot="title" class="card-title">Login</h4>
              <md-button
                slot="buttons"
                :href="naver"
                class="md-just-icon md-simple md-white"
              >
                <img src="@/img/naver.png"/>
              </md-button>
              <md-button
                slot="buttons"
                :href="kakao"
                class="md-just-icon md-simple md-white"
              >
                <img src="@/img/kakao.png"/>
              </md-button>
              <p slot="description" class="description">{{ msg }}</p>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>face</md-icon>
                <label>아이디</label>
                <md-input v-model="id"></md-input>
              </md-field>

              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>비밀번호</label>
                <md-input v-model="password" type="password"></md-input>
              </md-field>
              <md-button slot="footer" class="md-simple md-success md-lg" @click="login">
                로그인
              </md-button>
              <md-button
                slot="footer"
                @click="linkTo('/signup')"
                class="md-simple md-success md-lg"
              >
                회원가입
              </md-button>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import { kakao, naver } from "@/util/socialLogin.js";
import { getToken } from "@/util/request.js";
import { mapState, mapActions } from "vuex";
import jwt_decode from "jwt-decode";

export default {
  components: {
    LoginCard,
  },
  bodyClass: "login-page",
  data() {
    return {
      kakao, naver,
      id: null,
      password: null,
      msg: "",
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg"),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    ...mapActions("userStore", ["setIsLogIn", "setType", "setId", "setAuth", "setNickName", "setEmail", "setToken"]),
    linkTo(link) {
      this.$router.push(link);
    },
    async login() {
      try {
        const res = await getToken('/login', {
              method: "POST",
              headers: {
                  'Content-Type': 'application/json',
              },
              body: JSON.stringify({
                  username: this.id,
                  password: this.password,
              }),
          });

        const decoded = jwt_decode(res);
        this.setIsLogIn(true);
        this.setType('');
        this.setId(decoded.username);
        this.setEmail(decoded.email);
        this.setNickName(decoded.nickname);
        this.setAuth(decoded.authorities[0].authority);
        this.setToken(res);

        this.$router.push({name: "index"})

      } catch(e) {
        this.msg = "아이디 또는 비밀번호가 잘못되었습니다.";
      }
    }
  },
};
</script>

<style lang="css" scoped>
.description {
  color: red;
}
.wrapper {
  height: 100vh;
}
.section {
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.section::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
</style>
