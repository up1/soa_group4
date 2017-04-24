import Vue from 'vue'

const state = {
  information:false,
  profileInformation:{
    firstname:'',
    lastname:'',
    email:'',
    descriptions:'',
    gender:'undefined',
    taste:'undefined',
    birthdate:'',
    Img:[],
    location:{
      lat:0,
      lng:0
    }
  },
  matchingInformation:{
    gender:'undefined',
    taste:'undefined',
    minAge:0,
    maxAge:0,
    maxDistance:0
  },
  editInfomation:{
    Img:[],
    location:{
      lat:0,
      lng:0
    }
  }
}

const getters = {
  getProfile: (state) => state.profileInformation,
  getMatching: (state) => state.matchingInformation,
  getEditInfomation: (state) => state.editInfomation
}

const actions = {
  getProfileInfomation: ({commit},id) => {
    Vue.http.get('http://139.59.231.62:9008/AccountProfile/'+id).then(
      (response) => {
        let taste_list = {'Normal':1,'Top':2,'Bottom':3}
        commit('setProfileInformation',{
          firstname:response.data.account_name,
          lastname:response.data.account_lastname,
          email:response.data.account_email,
          descriptions:response.data.account_descriptions,
          gender:response.data.account_sex,
          taste:taste_list[response.data.account_sexual_taste],
          birthdate:response.data.account_birthday,
          Img:response.data.account_imgsprofile,
          location:{
            lat:response.data.account_latitude,
            lng:response.data.account_longtitude
          }
        })
        commit('setMatchingInformation',{
          gender:response.data.search_sex,
          taste:taste_list[response.data.search_sexual_taste],
          minAge:response.data.search_min_age,
          maxAge:response.data.search_max_age,
          maxDistance:response.data.search_distance
        })
       }
    )
  }
}

const mutations = {
  setProfileInformation: (state,profileInformation) => {
    state.profileInformation = profileInformation
  },
  setMatchingInformation: (state,matchingInformation) => {
    state.matchingInformation = matchingInformation
  },
  setLocationEditInformation: (state,location) => {
    state.editInfomation.location = location
  },
  setImageEditInformation: (state,value) => {
    if (state.editInfomation.Img[value.num-1] !== undefined) {
      state.editInfomation.Img[value.num-1] = value.file
    }else{
      state.editInfomation.Img.push(value.file)
    }
  },
  resetEditInformation: (state) => {
    state.editInfomation = {
      Img:[],
      location:{
        lat:0,
        lng:0
      }
    }
  }
}

export default {
    state,
    getters,
    actions,
    mutations
}
