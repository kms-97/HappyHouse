const searchStore = {
    namespaced: true,
    state: {
        isLogin: false,
        interested: [],
        type: "",
        id: "",
        email: "",
        nickname: "",
        token: "",
        auth: "",
    },
    
    getters: {
        getIsLogIn(state) {
            return state.isLogin;
        },
        
        getInterested(state) {
            return state.interested;
        },

        getType(state) {
            return state.type;
        },

        getId(state) {
            return state.id;
        },

        getEmail(state) {
            return state.email;
        },

        getNickName(state) {
            return state.nickname;
        },

        getToken(state) {
            return state.token;
        },

        getAuth(state) {
            return state.auth;
        },
    },

    mutations: {
        SET_ISLOGIN(state, isLogIn) {
            state.isLogin = isLogIn;
        },

        SET_INTERESTED(state, interested) {
            state.interested = interested;
        },

        SET_TYPE(state, type) {
            state.type = type;
        },

        SET_ID(state, id) {
            state.id = id;
        },

        SET_EMAIL(state, email) {
            state.email = email;
        },

        SET_NICKNAME(state, nickname) {
            state.nickname = nickname;
        },

        SET_TOKEN(state, token) {
            state.token = token;
        },

        SET_AUTH(state, auth) {
            state.auth = auth;
        },
    },

    actions: {
        setIsLogIn({ commit }, isLogIn) {
            commit('SET_ISLOGIN', isLogIn);
        },

        setInterested({ commit }, interested) {
            commit('SET_INTERESTED', interested);
        },

        setType({ commit }, type) {
            commit('SET_TYPE', type);
        },

        setId({ commit }, id) {
            commit('SET_ID', id);
        },

        setEmail({ commit }, email) {
            commit('SET_EMAIL', email);
        },

        setNickName({ commit }, nickname) {
            commit('SET_NICKNAME', nickname);
        },

        setToken({ commit }, token) {
            commit('SET_TOKEN', token);
        },

        setAuth({ commit }, auth) {
            commit('SET_AUTH', auth);
        },

        logout({ commit }) {
            if (confirm("로그아웃 하시겠습니까?")) {
                commit('SET_ISLOGIN', false);
                commit('SET_TYPE', "");
                commit('SET_ID', "");
                commit('SET_NICKNAME', "");
                commit('SET_TOKEN', "");
                commit('SET_AUTH', "");

                confirm('정상적으로 로그아웃 되었습니다.');
            }
        },

        logoutWithoutConfirm({ commit }) {
            commit('SET_ISLOGIN', false);
            commit('SET_TYPE', "");
            commit('SET_ID', "");
            commit('SET_NICKNAME', "");
            commit('SET_TOKEN', "");
            commit('SET_AUTH', "");
        },

        login({ commit }, { type, id, nickname, token, auth }) {
            commit('SET_ISLOGIN', true);
            commit('SET_TYPE', type);
            commit('SET_ID', id);
            commit('SET_NICKNAME', nickname);
            commit('SET_TOKEN', token);
            commit('SET_AUTH', auth);
        }
    }
}

export default searchStore;