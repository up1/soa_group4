import router from '@/router/index'

const API_LOGIN = "http://localhost:9011"
const URL = "/auth"
const LOGIN_URL = API_LOGIN+URL

export default {
  user:{
    authentication :false
  },
  login(context, creds, redirect){

    context.$http.post(LOGIN_URL, creds).then(data => {
      localStorage.setItem('token', data.body.token)
      localStorage.setItem('user', JSON.stringify(data.body.user))
      this.user.authenticated = true

      if(redirect) {
        router.go(redirect)
      }

    }, response =>{
      if (response.body) {
        if (response.body.status === "Bad credentials") {
          Materialize.toast("ชื่อผู้ใช้หรือรหัสผ่านผิด", 2000 ,'red darken-4')
        }
      }
    })
  },
  logout() {
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    this.user.authenticated = false
  },

  checkAuth() {
    var jwt = localStorage.getItem('token')
    if(jwt) {
      this.user.authenticated = true
    }
    else {
      this.user.authenticated = false
    }
  },


  getAuthHeader() {
    return {
      'Authorization': localStorage.getItem('token')
    }
  }
}
