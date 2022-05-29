<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="green">
              <h4 slot="title" class="card-title">SignUp</h4>
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-facebook-square"></i>
              </md-button>
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-twitter"></i>
              </md-button>
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-google-plus-g"></i>
              </md-button>
              <p slot="description" class="description">{{ msg }}</p>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>face</md-icon>
                <label>아이디를 작성해주세요.</label>
                <md-input v-model="id"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>face</md-icon>
                <label>이름을 작성해주세요.</label>
                <md-input v-model="nickname"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label>Email을 작성해주세요.</label>
                <md-input v-model="email" type="email"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>패스워드를 입력해 주세요.</label>
                <md-input v-model="password" type="password"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>패스워드를 확인해 주세요.</label>
                <md-input v-model="passwordConfirm" type="password"></md-input>
              </md-field>
              <md-button slot="footer" class="md-simple md-success md-lg" @click="signup">
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

export default {
  components: {
    LoginCard,
  },
  bodyClass: "login-page",
  data() {
    return {
      id: null,
      email: null,
      password: null,
      passwordConfirm: null,
      nickname: null,
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
    async signup() {
      // Validation
      if (!this.id) {this.msg = "아이디를 입력해주세요."; return;}
      if (!this.email) {this.msg = "이메일을 입력해주세요."; return;}
      if (!this.nickname) {this.msg = "닉네임을 입력해주세요."; return;}
      if (this.password !== this.passwordConfirm) {this.msg = "비밀번호가 다릅니다."; return;}

      try {
        const res = await fetch('http://localhost:81/signup', {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: this.id,
                password: this.password,
                email: this.email,
                nickname: this.nickname,
            }),
        });

        if (res.ok) this.$router.push({name: 'login'})
        else throw {
          status: res.status,
          message: res.statusText,
        }
      } catch(e) {
        // 에러처리.
        if (e.status === 400) console.log("잘못된 입력입니다.")
        if (e.status === 409){
          this.msg = "중복된 아이디 또는 이메일입니다."
        }
      }
    }
  }
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
