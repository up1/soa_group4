<!--
  component : navbar for all page
  original html code & design : Tanawat 57070025
  vue.js coding : Thatchakon 57070052
-->
<template>
  <div id="navbar">
    <ul id="menu" class="dropdown-content">
		<li><a href="#!">Edit Profile</a></li>
		<li class="divider"></li>
  	<li><a href="#!" v-on:click="logout()">Logout</a></li>
    </ul>
    <nav id="navbar">
      <div class="nav-wrapper">
        <div class="container">
          <ul class="left hide-on-med-and-down">
            <li>
              <a href="#" class="brand-logo custom-logo">TINNER</a>
            </li>
            <li class="active">
              <a href="#">Suggestion</a>
            </li>
            <li>
              <a href="#">Chat</a>
            </li>
          </ul>
          <ul class="right hide-on-med-and-down">
            <li>
              <a class="dropdown-button" href="#!" data-beloworigin="true" data-alignment="right" data-activates="menu"><i class="material-icons left">assignment_ind</i>{{profile.name}} {{profile.lastname}}<i class="material-icons right">arrow_drop_down</i></a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>
<script>
  import { mapGetters } from 'vuex'
  export default{
    name:'navbar',
    data(){
      return {
      }
    },
    mounted(){
      $(".dropdown-button").dropdown();
    },
    methods:{
      logout(){
        alert('Logout!')
      }
    },
    computed:mapGetters({
      profile: 'getProfile'
    })
  }
</script>
<style scoped>
  .nav-wrapper{
      background-color: #262626 !important;
  }
  .custom-logo{
  position: relative !important;
  display: block !important;
  margin-right: 10px;
  }
  .custom-logo:hover{
  background-color: transparent !important;
  }
</style>
