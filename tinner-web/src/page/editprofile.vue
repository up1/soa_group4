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
            <div v-for="num in numberOfFileDialog">
              <File :number="num"></File>
            </div>
            <div class="row">
              <div class="input-field col s3">
                <input id="first_name" type="text" v-model="information.firstname">
                <label for="first_name">First Name</label>
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
                <input type="date" class="datepicker">
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
                  <input type="range" min="0" max="100" v-model="matching.maxDistance">
                </p>
              </div>
            </div>
            <div class="row">
              <div class="col s12 center">
                <button class="btn green">SAVE</button>
                <button class="btn red">CANCEL</button>
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
        numberOfFileDialog : [1,2,3,4],
        information:{
          firstname:'',
          lastname:'',
          email:'',
          description:'',
          gender:'',
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
      $('.datepicker').pickadate({
        selectMonths: true,
        selectYears: 15
      })
    }
  }
</script>
<style src="../assets/css/editprofile.css" scoped>
</style>
