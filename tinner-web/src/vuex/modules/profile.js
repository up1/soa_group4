import Vue from 'vue'

const state = {
  information:false,
  profileInformation:{
    name:'',
    lastname:'',
    email:'',
    descriptions:'',
    gender:'',
    taste:'',
    birthdate:'',
    Img:[],
    location:{
      lat:0,
      lng:0
    }
  },
  matchingInformation:{
    gender:'',
    taste:'',
    maxAge:0,
    maxDistance:0
  }
}

const getters = {
  getProfile: (state) => state.profileInformation,
  getMatchingInformation: (state) => state.matchingInformation
}

const actions = {
  getProfileInfomation: ({commit},id) => {
    Vue.http.get('http://localhost:9006/AccountProfile?id='+id).then(
      (response) => {
        commit('setProfileInformation',{
          name:response.data.account_name,
          lastname:response.data.account_lastname,
          email:response.data.account_email,
          descriptions:response.data.account_descriptions,
          gender:response.data.account_sex
        })
       }
    )
  }
}

const mutations = {
  setProfileInformation: (state,profileInformation) => {
    state.profileInformation = profileInformation
  },
  setInformationStatus: (state,information) => {
    state.information = information
  }
}

export default {
    state,
    getters,
    actions,
    mutations
}
