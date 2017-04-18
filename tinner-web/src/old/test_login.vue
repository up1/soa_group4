<template>
  <div id="login">
    <div class="form-group">
        <input
          type="text"
          class="form-control"
          placeholder="Enter your username"
          v-model="credentials.username"
        >
      </div>
      <div class="form-group">
        <input
          type="password"
          class="form-control"
          placeholder="Enter your password"
          v-model="credentials.password"
        >
      </div>
      <button @click="login()">Access</button>
  </div>
</template>

<script>
  import auth from '@/auth'
  export default {
    name:'login',
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
