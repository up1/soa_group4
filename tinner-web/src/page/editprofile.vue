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
            <div v-for="num in loopOfFileDialog">
              <File :number=num></File>
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
  import GMap from '@/components/Map'
  export default{
    name:'EditProfile',
    components:{
      'Navbar' : Navbar,
      'Gmap' : GMap,
      'File' : FileDialog
    },
    data(){
      return {
        loopOfFileDialog : [1,2,3,4]
      }
    }
  }
</script>
<style scoped>
  .card-container-padding{
    padding: 24px;
  }
</style>
