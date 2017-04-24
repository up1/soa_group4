import Vue from 'vue'
import Vuex from 'vuex'
import Profile from './modules/profile.js'
import matching from './modules/matching.js'
Vue.use(Vuex)

var store = new Vuex.Store({
  state: {},
  mutations : {},
  getters: {},
  actions: {},
  modules: {
    Profile,
    matching
  }
})

export default store
