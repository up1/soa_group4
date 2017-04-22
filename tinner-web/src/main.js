// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VeeValidate from 'vee-validate'
import '!script-loader!jquery'
import '!style-loader!css-loader!materialize-css/bin/materialize.css'
import '!style-loader!css-loader!./assets/css/custom.css'
import '!script-loader!materialize-css/bin/materialize.js'

Vue.config.productionTip = false
Vue.use(VeeValidate)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
  http: {
            emulateJSON: true,
            emulateHTTP: true,
            headers: {
      'Content-Type': 'application/json'
    }
  }
})
