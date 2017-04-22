import Vue from 'vue'
import Vuex from 'vuex'
import Profile from './modules/profile.js'
Vue.use(Vuex)

var store = new Vuex.Store({
  state: {},
  mutations : {},
  getters: {},
  actions: {},
  modules: {
    Profile
  }
})

export default store
