import Vue from "vue";
import Vuex from "vuex";

import locateStore from '@/store/module/locateStore.js';
import searchStore from '@/store/module/searchStore.js';
import userStore from '@/store/module/userStore.js';
import recentStore from '@/store/module/recentStore.js';

import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    locateStore,
    searchStore,
    userStore,
    recentStore,
  },

  plugins: [
    createPersistedState({
      paths: ["userStore", "searchStore", "recentStore"],
    }),
  ],
});