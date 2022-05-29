<template>
    <div>
        <b-spinner label="Loading..."></b-spinner>
    </div>
</template>

<script>
import { getToken } from "@/util/request.js";
import { mapState, mapActions } from "vuex";
import jwt_decode from "jwt-decode";

export default {
    name: "naver",
    methods: {
        ...mapActions("userStore", ["setIsLogIn", "setType", "setId", "setAuth", "setNickName", "setToken"]),
    },
    async created() {
        const code = this.$route.query.code;
        const state = this.$route.query.state;
        console.log(code);

        try {
            const res = await getToken('/auth/naver', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    code,
                }),
            });

            const decoded = jwt_decode(res);
            this.setIsLogIn(true);
            this.setType('social');
            this.setId(decoded.username);
            this.setNickName(decoded.nickname);
            this.setAuth(decoded.authorities[0].authority);
            this.setToken(res);

            this.$router.push({name: "index"})
        } catch(e) {
            alert("로그인 중 오류가 발생하였습니다.");
            this.$router.push({name: "login"})
        }
    },
}
</script>

<style></style>