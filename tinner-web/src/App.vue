<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  import store from './vuex'
  export default {
    name: 'app',
    store
  }
</script>

<style>
  @import url('https://fonts.googleapis.com/icon?family=Material+Icons');
  body{
    background-color: #353535;
  }
  .fade-enter-active, .fade-leave-active {
    transition: opacity .2s
  }
  .fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
    opacity: 0
  }
</style>
