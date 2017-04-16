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
            <div v-for="num in 4">
              <File :number="num"></File>
            </div>
            <div class="row">
              <div class="input-field col s3">
                <input v-validate="'required'" id="first_name" type="text" name="firstname" v-model="information.firstname">
                <label for="first_name">First Name</label>
                <span v-show="errors.has('firstname')" class="invalid-color">{{ errors.first('firstname') }}</span>
              </div>
              <div class="input-field col s3">
                <input id="last_name" type="text" v-model="information.lastname">
                <label for="last_name">Last Name</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s6">
                <input id="email" type="email" v-model="information.email">
                <label for="email">E-mail</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s6">
                <textarea id="description" class="materialize-textarea" v-model="information.description"></textarea>
                <label for="description">Description</label>
              </div>
            </div>
            <div class="row">
              <Gender :value="information.gender" @value="information.gender = $event" name="genderProfile" idMale="maleProfile" idFemale="femaleProfile"></Gender>
              <Taste id="tasteProfile" :value="information.taste" @value="information.taste = $event"></Taste>
            </div>
            <div class="row">
              <div class="col s3">
                <label>Birth date</label>
                <input type="date" class="datepicker" name="birthdate" v-validate="'required'">
                <span v-show="errors.has('birthdate')" class="invalid-color">{{ errors.first('birthdate') }}</span>
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
                <input id="age" type="text"  v-model="matching.maxAge">
                <label for="age">Maximum age</label>
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
    },
    data(){
      return {
        information:{
          firstname:'',
          lastname:'',
          email:'',
          description:'',
          gender:'',
          birthdate:'',
          taste:0
        },
        matching:{
          gender:'',
          taste:0,
          maxAge:0,
          maxDistance:0
        }
      }
    },
    mounted(){
      this.setEditProfile(this.$store.getters.getProfile)
      this.setEditMatching(this.$store.getters.getMatchingInformation)
      let datepicker = $('.datepicker').pickadate({
        selectMonths: true,
        selectYears: 50,
        formatSubmit: 'yyyy-mm-dd',
      })
      picker = datepicker.pickadate('picker')
      picker.render()
      $('.datepicker').change((e)=>{
          this.birthdate = picker.get('select', 'yyyy/mm/dd')
          if (true) {

          }
      })
    },
    methods:{
      setEditProfile(value){
        this.information.firstname = value.firstname
        this.information.lastname = value.lastname
        this.information.email = value.email
        this.information.descriptions = value.descriptions
        this.information.gender = value.gender
      },
      setEditMatching(value){
        this.matching = value
      },
      submit(){
        let formData = new FormData()
        this.http.put(URL.UPDATEPROFILE,formData).then(
          data => {
            this.$store.dispatch('getProfileInfomation',1)
            Materialize.toast("อัพเดทเสร็จสมบูรณ์", 2000)
          },
          response => {
            console.log(response.body)
          }
        )
      }
    }
  }
</script>
<style src="../assets/css/editprofile.css" scoped>
</style>
