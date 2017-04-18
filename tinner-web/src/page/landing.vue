<template>
  <div id="landing">
    <navbar></navbar>
    <regisBox></regisBox>
  </div>
</template>
<script>
  import navbar from '@/components/navbar_login'
  import regisBox from '@/components/register_dialog'
  export default{
    name:'landing',
    components:{
      navbar,
      regisBox
    }
  }
</script>
<style src="../assets/css/landing.css" scoped>
</style>
