<template>
  <div id="edit-profile">
    <Navbar></Navbar>
    <div class="container">
      <div class="row">
        <div class="section col s12">
  				<div class="card card-container-padding">
            <div class="row">
              <div class="col s4">
                <h5>Profile Setting</h5>
              </div>
            </div>
            <div class="row">
              <div class="col s12" v-if="this.test < 4">
                <a class="btn-floating right waves-effect red-button" v-on:click="addImageDialog()"><i class="material-icons">add</i></a>
              </div>
              <div class="col s12" v-else>
                <a class="btn-floating right disabled"><i class="material-icons">add</i></a>
              </div>
            </div>
            <div class="slider">
              <ul class="slides">
                <li v-for="image in information.images">
                  <img :src="image">
                </li>
              </ul>
            </div>
            <div class="row">
              <div v-for="num in this.imageCounter">
                  <File :number="num"></File>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s3">
                <input v-validate="'required'" id="first_name" type="text" data-vv-name="firstname" v-model="information.firstname">
                <label for="first_name">First Name</label>
                <span v-show="errors.has('firstname')" class="invalid-color">{{ errors.first('firstname') }}</span>
              </div>
              <div class="input-field col s3">
                <input v-validate="'required'" id="last_name" type="text" data-vv-name="lastname" v-model="information.lastname">
                <label for="last_name">Last Name</label>
                <span v-show="errors.has('lastname')" class="invalid-color">{{ errors.first('lastname') }}</span>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s6">
                <input v-validate="'required|email'" id="email" type="email" data-vv-name="email" v-model="information.email">
                <label for="email">E-mail</label>
                <span v-show="errors.has('email')" class="invalid-color">{{ errors.first('email') }}</span>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s6">
                <textarea v-validate="'required'" data-vv-name="description" id="description" class="materialize-textarea" v-model="information.descriptions"></textarea>
                <label for="description">Description</label>
                <span v-show="errors.has('description')" class="invalid-color">{{ errors.first('description') }}</span>
              </div>
            </div>
            <div class="row">
              <Gender :value="information.gender" @value="information.gender = $event" name="genderProfile" idMale="maleProfile" idFemale="femaleProfile"></Gender>
              <Taste id="tasteProfile" :value="information.taste" @value="information.taste = $event"></Taste>
            </div>
            <div class="row">
              <div class="col s3">
                <label>Birth date</label>
                <input type="date" class="datepicker" name="birthdate" >
              </div>
            </div>
            <Gmap></Gmap>
            <div class="row">
              <div class="col s4" style="margin-top:15px">
                <h5>Matching Setting</h5>
              </div>
            </div>
            <div class="row">
              <Gender :value="matching.gender" @value="matching.gender = $event" name="genderMatching" idMale="maleMatching" idFemale="femaleMatching"></Gender>
              <Taste id="tasteMatching" :value="matching.taste" @value="matching.taste = $event"></Taste>
            </div>
            <div class="row">
              <div class="input-field col s3">
                <input id="minAge" type="text" data-vv-name="Minimun Age" v-validate="'required|numeric|min_value:15|max_value:120'" v-model="matching.minAge">
                <label for="age">Minimum age</label>
                <span v-show="errors.has('Minimun Age')" class="invalid-color">{{ errors.first('Minimun Age') }}</span>
              </div>
              <div class="input-field col s3">
                <input id="maxAge" type="text" data-vv-name="Maximun Age" v-validate="'required|numeric|min_value:15|max_value:120'" v-model="matching.maxAge">
                <label for="age">Maximum age</label>
                <span v-show="errors.has('Maximun Age')" class="invalid-color">{{ errors.first('Maximun Age') }}</span>
              </div>
            </div>
            <div class="row">
              <div class="col s6">
                <div class="p">Maximum distance</div>
                <p class="range-field">
                  <input type="range" min="0" max="1800" v-model="matching.maxDistance">
                </p>
              </div>
            </div>
            <div class="row">
              <div class="col s12 center">
                <button class="btn green">SAVE</button>
                <router-link :to="{ path: '/' }" :class="['btn', 'red']">CANCEL</router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import Navbar from '@/components/navbar'
  import FileDialog from '@/components/filedialog'
  import Gender from '@/components/gender_radio_button.vue'
  import Taste from '@/components/choose_taste'
  import GMap from '@/components/Map'
  import { mapGetters } from 'vuex'
  const URL = {
    UPDATEPROFILE: ""
  }
  var picker
  export default{
    name:'EditProfile',
    components:{
      'Navbar' : Navbar,
      'Gmap' : GMap,
      'File' : FileDialog,
      'Gender' : Gender,
      'Taste': Taste
    },created(){
      this.setEditProfile(this.profile)
      this.setEditMatching(this.$store.getters.getMatchingInformation)
      if (this.information.images.length != 0) {
        this.imageCounter = this.information.images.length
      }
    },
    data(){
      return {
        count:0,
        information:{
          firstname:'',
          lastname:'',
          email:'',
          descriptions:'',
          gender:'',
          birthdate:'',
          taste:0,
          images:[]
        },
        matching:{
          gender:'',
          taste:0,
          minAge:0,
          maxAge:0,
          maxDistance:0
        },
        imageCounter:1
      }
    },
    mounted(){
      let datepicker = $('.datepicker').pickadate({
        selectMonths: true,
        selectYears: 50,
        formatSubmit: 'yyyy-mm-dd'
      })
      picker = datepicker.pickadate('picker')
      picker.render()
      picker.set('select', this.information.birthdate)
      $('.datepicker').change((e)=>{
          this.birthdate = picker.get('select', 'yyyy/mm/dd')
      })
    },
    methods:{
      setEditProfile(value){
        this.information.firstname = value.firstname
        this.information.lastname = value.lastname
        this.information.email = value.email
        this.information.descriptions = value.descriptions
        this.information.gender = value.gender
        this.information.birthdate = value.birthdate
        this.information.taste = value.taste
        this.information.images = value.Img
      },
      setEditMatching(value){
        this.matching = value
      },
      submit(){
        let formData = new FormData()
        this.http.put(URL.UPDATEPROFILE,formData).then(
          data => {
            this.$store.dispatch('getProfileInfomation',JSON.parse(this.$localStorage.get('user')).id)
            Materialize.toast("อัพเดทเสร็จสมบูรณ์", 2000)
          },
          response => {
            console.log(response.body)
          }
        )
      },
      addImageDialog(){
        this.imageCounter++
      }
    },computed:mapGetters({
      profile: 'getProfile'
    }),
    updated() {
      if (this.information.images.length > 0 && !this.count) {
        $(".slider").slider()
        this.count++
      }
      $('label').addClass('active')
    },
    watch:{
      'profile': function() {
        this.setEditProfile(this.profile)
        picker.set('select', this.information.birthdate)
        if (this.information.images.length != 0) {
          this.imageCounter = this.information.images.length
        }
      }
    }
  }
</script>
<style src="../assets/css/editprofile.css" scoped>
</style>
