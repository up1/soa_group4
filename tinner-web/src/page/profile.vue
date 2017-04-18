<template>
  <div id="profile">
    <Navbar></Navbar>
    <div class="container">
      <ProfileCard profile="true"></ProfileCard>
    </div>
  </div>
</template>
<script>
  import Navbar from '@/components/navbar'
  import ProfileCard from '@/components/profile_card'
  export default {
    name:'Profile',
    components:{
      'Navbar':Navbar,
      'ProfileCard':ProfileCard
    }
  }
</script>
<style src="../assets/css/profile.css" scoped>
</style>
