<template>
  <div id="edit-profile">
    <Navbar></Navbar>
      <div class="container">
        <div class="row">
          <div class="section col s12">
            <transition name="fade">
      				<div class="card card-container-padding">
                <div class="row">
                  <div class="col s4">
                    <h5>Profile Setting</h5>
                  </div>
                </div>
                <div class="row">
                  <div class="col s12" v-if="this.imageCounter < 4">
                    <a class="btn-floating right waves-effect red-button" v-on:click="addImageDialog()"><i class="material-icons">add</i></a>
                  </div>
                  <div class="col s12" v-else>
                    <a class="btn-floating right disabled"><i class="material-icons">add</i></a>
                  </div>
                </div>
                <div class="slider" v-if="this.ImageEdit == 0">
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
                  <Gender :value="information.gender" @value="information.gender = $event" name="genderProfile" idMale="maleProfile" idFemale="femaleProfile" v-if="information.gender != 'undefined'"></Gender>
                  <Taste id="tasteProfile" :value="information.taste" @value="information.taste = $event" v-if="information.taste != 'undefined'"></Taste>
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
                  <Gender :value="matching.gender" @value="matching.gender = $event" name="genderMatching" idMale="maleMatching" idFemale="femaleMatching" v-if="matching.gender != 'undefined'"></Gender>
                  <Taste id="tasteMatching" :value="matching.taste" @value="matching.taste = $event" v-if="matching.taste != 'undefined'"></Taste>
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
                    <div class="p">Maximum distance : {{matching.maxDistance}} KM</div>
                    <p class="range-field">
                      <input type="range" min="0" max="1800" v-model="matching.maxDistance">
                    </p>
                  </div>
                </div>
                <div class="row">
                  <div class="col s12 center">
                    <button class="btn waves-effect green" v-on:click="submit()">SAVE</button>
                    <router-link :to="{ path: '/' }" :class="['btn', 'waves-effect' ,'red']">CANCEL</router-link>
                  </div>
                </div>
              </div>
            </transition>
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
      this.setEditMatching(this.searching)
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
          taste:'',
          images:[]
        },
        matching:{
          gender:'',
          taste:'',
          minAge:0,
          maxAge:0,
          maxDistance:0
        },
        imageCounter:0,
        ImageEdit:0,
        ImageEditCount:0
      }
    },
    mounted(){
      let datepicker = $('.datepicker').pickadate({
        selectMonths: true,
        selectYears: 100,
        min:[1915,1,1],
        max:true,
        formatSubmit: 'yyyy-mm-dd'
      })
      picker = datepicker.pickadate('picker')
      picker.render()
      picker.set('select', this.information.birthdate)
      $('.datepicker').change((e)=>{
          this.information.birthdate = picker.get('select', 'yyyy/mm/dd')
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
        if (!this.errors.any()) {
          let date = new Date(this.information.birthdate)
          let taste_list = {1:'Normal',2:'Top',3:'Bottom'}
          let formData ={
            'account_email': this.information.email,
            'account_password': this.information.password,
            'account_name': this.information.firstname,
            'account_lastname': this.information.lastname,
            'account_birthday': date.getTime(),
            'account_sex': this.information.gender,
            'account_sexual_taste': taste_list[this.information.taste],
            'account_latitude': this.edit.location.lat,
            'account_longtitude': this.edit.location.lng,
            'account_descriptions': this.information.descriptions,
            'account_id': JSON.parse(this.$localStorage.get('user')).id,
            'search_sex': this.matching.gender,
            'search_sexual_taste': taste_list[this.matching.taste],
            'search_min_age': this.matching.minAge,
            'search_max_age': this.matching.maxAge,
            'search_distance': this.matching.maxDistance
          }
          this.$http.put(this.$URL.PROFILE+'/EditAccount',formData).then(
            data => {
                for(var index = 0; index < this.edit.Img.length ; ++index){
                  if (this.edit.Img[index] !== undefined) {
                    if (this.profile.Img[index] !== undefined) {
                      this.ImageEdit++
                      let formData = new FormData()
                      formData.append('file',this.edit.Img[index])
                      formData.append('identify',this.profile.Img[index])
                      formData.append('imagename',this.edit.Img[index].name)
                      this.$http.post(this.$URL.IMAGE+'/image/profile/url',formData).then(
                        response => {
                          this.ImageEditCount++
                        },
                        error => {
                          this.ImageEditCount++
                          Materialize.toast("เกิดข้อผิดพลาดกรุณาลองใหม่", 2000 ,'red darken-4')
                        }
                      )
                    }else{
                      if (this.profile.Img[index] === undefined) {
                        this.ImageEdit++
                        let formData = new FormData()
                        formData.append('file',this.edit.Img[index])
                        formData.append('uid1',JSON.parse(this.$localStorage.get('user')).id)
                        formData.append('uid2',0)
                        formData.append('imagename',this.edit.Img[index].name)
                        this.$http.post(this.$URL.IMAGE+'/image/profile/AzureUpload',formData).then(
                          response => {
                            this.ImageEditCount++
                          },
                          error => {
                            this.ImageEditCount++
                            Materialize.toast("เกิดข้อผิดพลาดกรุณาลองใหม่", 2000 ,'red darken-4')
                          }
                        )
                    }
                  }
                }
              }
              this.$store.dispatch('getProfileInfomation',JSON.parse(this.$localStorage.get('user')).id)
              Materialize.toast("อัพเดทข้อมูลเสร็จสมบูรณ์", 2000)
              if (this.ImageEdit == 0) {
                $('html, body').animate({ scrollTop: 0 }, 'fast')
              }
            },
            response => {
              Materialize.toast("เกิดข้อผิดพลาดกรุณาลองใหม่", 2000 ,'red darken-4')
            }
          )
        }else {
          Materialize.toast("กรุณากรอกข้อมูลให้ครบ", 2000 ,'red darken-4')
        }
      },
      addImageDialog(){
        this.imageCounter++
      }
    },computed:mapGetters({
      profile: 'getProfile',
      searching: 'getMatching',
      edit:'getEditInfomation'
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
      },
      'searching':function(){
        this.setEditMatching(this.searching)
      },
      'ImageEditCount':function(){
        if (this.ImageEdit === this.ImageEditCount) {
          this.$store.commit('resetImageEdit')
          $('html, body').animate({ scrollTop: 0 }, 'fast')
          this.ImageEdit = 0
          this.ImageEditCount = 0
          this.count = 0
          setTimeout(function(){
            window.location.reload(true)
          },1000)
        }
      }
    }
  }
</script>
<style src="../assets/css/editprofile.css" scoped>
</style>
