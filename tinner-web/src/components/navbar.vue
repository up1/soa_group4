<template>
  <div id="navbar">
    <ul id="menu" class="dropdown-content">
  		<li><router-link :to="{ path: '/profile' }">Edit Profile</router-link></li>
  		<li class="divider"></li>
    	<li><a href="#!" v-on:click="logout()">Logout</a></li>
    </ul>
    <nav id="navbar">
      <div class="nav-wrapper">
        <div class="container">
          <ul class="left hide-on-med-and-down">
            <li>
              <router-link :to="{ path: '/' }" class="brand-logo custom-logo">TINNER</router-link>
            </li>
            <li v-bind:class="{ active: routeName === 'Home' }">
              <router-link :to="{ path: '/' }">Suggestion</router-link>
            </li>
            <li v-bind:class="{ active: routeChat}">
              <router-link :to="{ path: '/chat' }">Chat</router-link>
            </li>
          </ul>
          <ul class="right hide-on-med-and-down">
            <li>
              <a class="dropdown-button" href="#!" data-beloworigin="true" data-alignment="right" data-activates="menu" v-if="profile.firstname !== ' '"><i class="material-icons left">assignment_ind</i>{{profile.firstname}} {{profile.lastname}}<i class="material-icons right">arrow_drop_down</i></a>
              <a class="dropdown-button" href="#!" data-beloworigin="true" data-alignment="right" data-activates="menu" v-else><i class="material-icons left">assignment_ind</i>Not Set<i class="material-icons right">arrow_drop_down</i></a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>
<script>
  import { mapGetters } from 'vuex'
  import auth from '@/auth'
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
        auth.logout()
      },
      checkChat(){
        return routeName === 'Chat' || routeName === 'Chatroom'
      }
    },
    computed:{
      ...mapGetters({
        profile: "getProfile"
      }),
      routeName: function(){
        return this.$route.name
      },
      routeChat: function(){
        return this.$route.name === 'Chat' || this.$route.name === 'Chatroom'
      }
    }
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
