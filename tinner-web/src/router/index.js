import Vue from 'vue'
import Router from 'vue-router'
import Landing from '@/page/landing'
import Login from '@/page/test_login'
import auth from '@/auth'
import VueResource from 'vue-resource'
Vue.use(VueResource)
Vue.use(Router)

Vue.http.headers.common['Authorization'] = localStorage.getItem('token');

auth.checkAuth()

var router = new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'Landing',
      component: Landing,
      meta: { reqAuth: true }
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
      meta: { reqAuth: false }
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
    next({path: '/login',
        query: {
          redirect: to.fullPath,
        }});
  }else if(to.name === 'login' && auth.user.authenticated){
    next({path:'/'})
  }else{
    next();
  }
})
