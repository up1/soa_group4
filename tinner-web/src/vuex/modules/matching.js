import Vue from 'vue'

const state = {
  shows:[],
  mama:{
    0:false
  }
}

const getters = {
  getShows: (state) => state.shows
}

const actions = {
}

const mutations = {
  editShowMatching: (state,data) => {
    Vue.set(state.shows,data.index,data.action)
  },
  initMatching: (state,init) => {
    state.shows.push(init)
  }
}

export default {
    state,
    getters,
    actions,
    mutations
}
