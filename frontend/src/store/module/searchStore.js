const searchStore = {
    namespaced: true,
    state: {
        type: "",
        code: "",
        keyword: "",
    },
    
    getters: {
        getType(state) {
            return state.type;
        },

        getCode(state) {
            return state.code;
        },

        getKeyword(state) {
            return state.keyword;
        }
    },

    mutations: {
        SET_TYPE(state, type) {
            state.type = type;
        },

        SET_CODE(state, code) {
            state.code = code;
        },

        SET_KEYWORD(state, keyword) {
            state.keyword = keyword;
        }
    },

    actions: {
        setType({ commit }, type) {
            commit('SET_TYPE', type);
        },

        setCode({ commit }, code) {
            commit('SET_CODE', code);
        },

        setKeyword({ commit }, keyword) {
            commit('SET_KEYWORD', keyword);
        }
    }
}

export default searchStore;