<!--
  page : Home
  vue.js coding : Thatchakon 57070053
-->
<template>
  <div id="home">
    <Navbar></Navbar>
    <div class="container">
      <Card></Card>
    </div>
  </div>
</template>
<script>
  import Navbar from '@/components/navbar'
  import ProfileCard from '@/components/profile_card'
  export default{
    name:'home',
    components:{
      Navbar : Navbar,
      Card : ProfileCard
    }
  }
</script>
<style src="../assets/css/home.css" scoped>
</style>
