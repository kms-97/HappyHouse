<template>
    <div class="wrapper">
        <div class="section page-header header-filter" :style="headerStyle">
            <div class="container">
                <div class="md-layout">
                    <div
                        class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto">
                        <login-card header-color="green">
                            <h4 slot="title" class="card-title">MyPage</h4>
                            <p slot="description" class="description">{{ msg }}</p>
                            <md-field class="md-form-group" slot="inputs">
                                <md-icon>face</md-icon>
                                <md-input :value="changedId" readonly></md-input>
                            </md-field>
                            <md-field class="md-form-group" slot="inputs">
                                <md-icon>face</md-icon>
                                <md-input v-model="changedNickName"></md-input>
                            </md-field>
                            <md-field class="md-form-group" slot="inputs">
                                <md-icon>email</md-icon>
                                <md-input :value="email" type="email" readonly v-if="type === 'social'"></md-input>
                                <md-input v-model="changedEmail" type="email" v-else></md-input>
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
                            <md-button slot="footer" class="md-simple md-success md-lg" @click="update">
                                수정
                            </md-button>
                            <md-button slot="footer" class="md-simple md-success md-lg" @click="deleteUser">
                                탈퇴
                            </md-button>
                        </login-card>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { getToken, fetchData } from "@/util/request.js";
import { LoginCard } from "@/components";
import jwt_decode from "jwt-decode";

const emailValidationRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

export default {
    components: {
        LoginCard,
    },
    bodyClass: "login-page",
    data() {
        return {
            msg: "",
            changedId: "",
            changedEmail: "",
            changedNickName: "",
            password: "",
            passwordConfirm: "",
        };
    },
    props: {
        header: {
            type: String,
            default: require("@/assets/img/profile_city.jpg"),
        },
    },
    computed: {
        ...mapState("userStore", ['type', 'id', 'nickname', 'email']),
        headerStyle() {
            return {
                backgroundImage: `url(${this.header})`,
            };
        },
    },
    methods: {
        ...mapActions("userStore", ["setId", "setNickName", "setEmail", "setToken", "logoutWithoutConfirm"]),
        async update() {
            if (!this.changedId) { this.msg = "아이디를 입력해주세요."; return; }
            if (!this.email) { this.msg = "이메일을 입력해주세요."; return; }
            if (!emailValidationRegExp.test(this.changedEmail)) { this.msg = "올바른 이메일 형식이 아닙니다."; return; }
            if (!this.nickname) { this.msg = "닉네임을 입력해주세요."; return; }
            if (!this.password || !this.passwordConfirm) { this.msg = "비밀번호를 입력해주세요."; return; }
            if (this.password !== this.passwordConfirm) { this.msg = "비밀번호가 다릅니다."; return; }

            try {
                const res = await getToken('/user/update', {
                    method: "PUT",
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        username: this.changedId,
                        email: this.changedEmail,
                        nickname: this.changedNickName,
                        password: this.password,
                    }),
                });

                const decoded = jwt_decode(res);
                this.setId(decoded.username);
                this.setEmail(decoded.email);
                this.setNickName(decoded.nickname);
                this.setToken(res);

                this.$router.push({name: "index"})
            } catch (e) {
                console.log(e)
                if (e.status === '409') this.msg = "중복된 이메일입니다.";
                else if (e.status === '403') this.msg = "잘못된 비밀번호입니다.";
                else this.msg = "오류가 발생하였습니다.";
            }
        },
        async deleteUser() {
            if (!this.password || !this.passwordConfirm) { this.msg = "비밀번호를 입력해주세요."; return; }
            if (this.password !== this.passwordConfirm) { this.msg = "비밀번호가 다릅니다."; return; }

            try {
                const res = await fetch('http://localhost:81/user/delete', {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        username: this.changedId,
                        password: this.password,
                    }),
                });

                console.log(res)
                if (res.ok) {
                    this.logoutWithoutConfirm();
                    this.$router.push({ name: "index"});
                } else throw {
                    status: res.status,
                    message: res.statusText,
                }
            } catch(e) {
                console.log(e)
                if (e.status === '403') this.msg = "잘못된 비밀번호입니다.";
                else this.msg = "오류가 발생하였습니다.";
            }
        }
    },
    created() {
        if (this.type === 'social') this.changedId = `Social_${this.id}`;
        else this.changedId = this.id;
        this.changedEmail = this.email;
        this.changedNickName = this.nickname;
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
    -ms-overflow-style: none;
    /* IE and Edge */
    scrollbar-width: none;
    /* Firefox */
}

.section::-webkit-scrollbar {
    display: none;
    /* Chrome, Safari, Opera*/
}
</style>
