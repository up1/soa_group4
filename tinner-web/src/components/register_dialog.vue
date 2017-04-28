<template>
  <div id="regis-box">
      <div class="row">
        <div class="col s12 img">
          <img src="../assets/logo.png" alt="Logo" class="circle responsive-img">
        </div>
        <div class="col s12 signup-box">
          <input v-validate="'required'" data-vv-name="username" id="username" type="text" placeholder="Username" v-model="credentials.registerUsername">
          <span v-show="errors.has('username')" class="invalid-color">{{ errors.first('username') }}</span>
        </div>
        <div class="col s12 signup-box">
          <input v-validate="'required|email'" data-vv-name="email" id="email" type="email" placeholder="Email" v-model="credentials.registerEmail">
          <span v-show="errors.has('email')" class="invalid-color">{{ errors.first('email') }}</span>
        </div>
        <div class="col s12 signup-box">
          <input v-validate="'required|min:8'" data-vv-name="password" id="password" type="password" placeholder="Password" v-model="credentials.registerPassword">
          <span v-show="errors.has('password')" class="invalid-color">{{ errors.first('password') }}</span>
        </div>
        <div class="col s12 center">
          <button class="btn red" v-on:click="register()">Sign up</button>
        </div>
      </div>
  </div>
</template>

<script>
  import auth from '@/auth'
  export default {
    name:'regisDialog',
    data(){
      return {
        credentials: {
          registerUsername: '',
          registerEmail:'',
          registerPassword:'',
        }
      }
    },
    methods:{
      register(){
        let formData = new FormData();
        formData.append('username', this.credentials.registerUsername);
        formData.append('email', this.credentials.registerEmail);
        formData.append('password', this.credentials.registerPassword);
        let data = {
          username:this.credentials.registerUsername,
          email:this.credentials.registerEmail,
          password:this.credentials.registerPassword
        }
        if (!this.errors.any()) {
          this.$http.post(this.$URL.PROFILE+"/regis",data).then(
            data => {
              var credentials = {
                username: this.credentials.registerUsername,
                password: this.credentials.registerPassword
              };
              auth.login(this, credentials, "profile");
            },
            response => {
              console.log(response);
              Materialize.toast("ไม่สามารถสมัครได้ โปรลองใหม่อีกครั้ง", 2000 ,'red darken-4')
            }
          )
        }else{
          Materialize.toast("กรุณากรอกข้อมูลให้ครบ", 2000 ,'red darken-4')
        }
      }
    }
  }
</script>

<style scoped>
  .signup-box input{
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
  #regis-box{
   margin: 0 auto;
   width: 25%;
   padding: 20px;
   margin-top: 5%;
   background-color: rgba(26, 26, 26, 0.8) !important;
  }
  .signup-box{
   margin-left: 50px;
   margin-right: 50px;
  }
  .img img{
   margin: 0 auto;
   display: block;
   margin-bottom: 10px
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
