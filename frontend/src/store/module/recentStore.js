const recentStore = {
    namespaced: true,
    state: {
        recent: [],
        codeSet: [],
    },
    
    getters: {
        getRecent(state) {
            return state.recent;
        },
    },

    mutations: {
        SET_RECENT(state, recent) {
            state.recent = recent;
        },
    },

    actions: {
        setRecent({ commit }, recent) {
            commit('SET_RECENT', recent);
        },

        addRecent({ state, dispatch }, apt) {
            if (state.codeSet.includes(apt.aptCode)) return;
            state.codeSet.push(apt.aptCode);
            
            const tmp = [apt, ...state.recent];
            if (tmp.length > 5) {
                const rest = tmp.pop();
                
                const idx = state.codeSet.indexOf(rest.aptCode);
                state.codeSet.splice(idx, 1);
            }

            dispatch('setRecent', tmp);
        }
    }
}

export default recentStore;