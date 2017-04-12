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
                <input id="first_name" type="text" v:model="information.firstname">
                <label for="first_name">First Name</label>
              </div>
              <div class="input-field col s3">
                <input id="last_name" type="text" v:model="information.lastname">
                <label for="last_name">Last Name</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s6">
                <input id="email" type="email" v:model="information.email">
                <label for="email">E-mail</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s6">
                <textarea id="description" class="materialize-textarea" v:model="information.description"></textarea>
                <label for="description">Description</label>
              </div>
            </div>
            <div class="row">
              <Gender :value="information.gender" @value="information.gender = $event"></Gender>
            </div>
            <Gmap></Gmap>
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
  import GMap from '@/components/Map'
  export default{
    name:'EditProfile',
    components:{
      'Navbar' : Navbar,
      'Gmap' : GMap,
      'File' : FileDialog,
      'Gender' : Gender
    },
    data(){
      return {
        numberOfFileDialog : [1,2,3,4],
        information:{
          firstname:'',
          lastname:'',
          email:'',
          description:''
        }
      }
    }
  }
</script>
<style scoped>
  .card-container-padding{
    padding: 24px;
  }
</style>
