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
</style>
