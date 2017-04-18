<template>
  <nav id="navbar">
    <div class="nav-wrapper">
      <div class="container">
        <a href="#" class="brand-logo">Tinner</a>
          <ul class="right hide-on-med-and-down">
            <li class="signin-box">
              <input id="username" type="text" placeholder="Username" v-model="credentials.username">
            </li>
            <li class="signin-box">
              <input id="password" type="password" placeholder="Password" v-model="credentials.password">
            </li>
            <button class="btn red" v-on:click="login()">Sign in</button>
          </ul>
      </div>
    </div>
  </nav>
</template>
<script>
  import auth from '@/auth'
  export default {
    name:'navbarLogin',
    data(){
      return{
        credentials: {
          username: '',
          password: ''
        },
        error: ''
      }
    },
    methods:{
      login(){
        var credentials = {
          username: this.credentials.username,
          password: this.credentials.password
        };
        auth.login(this, credentials, this.$route.query.redirect);
      }
    }
  }
</script>

<style scoped>
  .signin-box{
    margin-right: 20px
  }
  .signin-box input{
    background-color: white !important;
    color: black !important;
    border-radius: 4px;
    padding: 2px 2px 2px 2px !important;
    height: 2rem !important;
  }
  .nav-wrapper{
    background-color: #262626 !important;
  }
  .red{
    background-color: #e70c49 !important;
  }
  *::-webkit-input-placeholder {
      color: red !important;
  }
  *:-moz-placeholder {
      /* FF 4-18 */
      color: red !important;
  }
  *::-moz-placeholder {
      /* FF 19+ */
      color: red !important;
  }
  *:-ms-input-placeholder {
      /* IE 10+ */
      color: red !important;
  }
</style>
