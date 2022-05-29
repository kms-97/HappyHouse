import { fetchData } from '@/util/request.js';

const locateStore = {
    namespaced: true,
    state: {
        locates: [],
        selectedLocate: null,
    },
    
    getters: {
        getLocates(state) {
            return state.locates;
        },

        getSelectedLocate(state) {
            return state.selectedLocate;
        }
    },

    mutations: {
        SET_LOCATES(state, locates) {
            state.locates = [...locates];
        },

        SET_SELECTED_LOCATE(state, locate) {
            state.selectedLocate = locate;
        }
    },

    actions: {
        async setLocates({ commit }) {
            const res = await fetchData('/dong');

            if (res.isError) {}// 에러처리
            else commit('SET_LOCATES', res.data);
        },

        setSelectedLocate({ commit }, locate) {
            commit('SET_SELECTED_LOCATE', locate);
        }
    }
}

export default locateStore;