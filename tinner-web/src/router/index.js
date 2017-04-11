import Vue from 'vue'
import Router from 'vue-router'
import Landing from '@/page/landing'
import Home from '@/page/home'
import auth from '@/auth'
import EditProfile from '@/page/editprofile'
import VueResource from 'vue-resource'
import store from '@/vuex'
Vue.use(VueResource)
Vue.use(Router)

Vue.http.headers.common['Authorization'] = localStorage.getItem('token');

auth.checkAuth()

if (auth.user.authenticated) {
  store.dispatch('getProfileInfomation',1)
}

var router = new Router({
  mode:'history',
  routes: [
    {
      path: '/landing',
      name: 'Landing',
      component: Landing,
      meta: { reqAuth: false }
    },
    {
      path: '/',
      name: 'Home',
      component: Home,
      meta: { reqAuth: true }
    },
    {
      path:'/profile',
      name:'EditProfile',
      component: EditProfile,
      meta: { reqAuth: true }
    },
    {
      path: '*',
      redirect: '/',
    }
  ],

})

export default router

router.beforeEach((to, from, next) => {

  if(to.meta.reqAuth && !auth.user.authenticated){
    console.log(to.fullPath);
    console.log(to.query.redirect);
    alert('You not login');
    next({path: '/landing',
        query: {
          redirect: to.fullPath,
        }});
  }else if(to.name === 'Landing' && auth.user.authenticated){
    next({path:'/'})
  }else{
    next();
  }
})
