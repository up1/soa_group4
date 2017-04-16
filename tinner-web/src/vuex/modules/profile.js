import Vue from 'vue'

const state = {
  information:false,
  profileInformation:{
    firstname:'',
    lastname:'',
    email:'',
    descriptions:'',
    gender:'',
    taste:0,
    birthdate:'',
    Img:[],
    location:{
      lat:0,
      lng:0
    }
  },
  matchingInformation:{
    gender:'',
    taste:0,
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
  getMatchingInformation: (state) => state.matchingInformation,
  getEditInfomation: (state) => state.editInfomation
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
