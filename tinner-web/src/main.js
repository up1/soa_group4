import Vue from 'vue'
import App from './App'
import router from './router'
import VeeValidate from 'vee-validate'
import URL from './assets/url'
import VueSocketio from 'vue-socket.io';
import '!script-loader!jquery'
import '!style-loader!css-loader!materialize-css/bin/materialize.css'
import '!style-loader!css-loader!./assets/css/custom.css'
import '!script-loader!materialize-css/bin/materialize.js'

Vue.config.productionTip = false
Vue.use(VeeValidate)
Vue.use(VueSocketio, 'http://161.246.131.173:9012')
Vue.prototype.$URL = URL

new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
  http: {
            emulateJSON: true,
            emulateHTTP: true,
            headers: {
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin':'*'
    }
  }
})
