<!--
  page : Home
  vue.js coding : Thatchakon 57070053
-->
<template>
  <div id="home">
    <Navbar></Navbar>
    <div class="container">
      <Card :matching="match" :profile="showProfile" @value="showProfile = $event"></Card>
    </div>
  </div>
</template>
<script>
  import Navbar from '@/components/navbar'
  import ProfileCard from '@/components/profile_card'
  export default{
    name:'home',
    data(){
      return {
        showProfile:false,
        match:{
          id:'',
          username:'',
          name:'',
          lastname:'',
          age:0,
          location:'',
          distance:'',
          descriptions:'',
          taste:'',
          images:[]

        }
      }
    },
    components:{
      Navbar : Navbar,
      Card : ProfileCard
    },
    created(){
      this.$http.get('http://128.199.211.151:9001/matching?id='+JSON.parse(this.$localStorage.get('user')).id).then(
        (response) => {
          this.match = {
            id:response.data[0].id,
            username:response.data[0].username,
            name:response.data[0].name,
            lastname:response.data[0].lastname,
            age:response.data[0].age,
            location:response.data[0].locationName,
            distance:response.data[0].distance,
            descriptions:response.data[0].description,
            taste:response.data[0].sexTaste,
            images:response.data[0].imgProfile
          }
        }
      )
    }
  }
</script>
<style src="../assets/css/home.css" scoped>
</style>
