<!--
  page : Home
  vue.js coding : Thatchakon 57070053
-->
<template>
  <div id="home">
    <Navbar></Navbar>
    <div class="container" v-for="(item, index) in this.matchs">
      <Card :matching="item" :sliderId="index" :profile="showProfile" @value="showProfile = $event" v-if="shows[index]"></Card>
    </div>
    <div class="container" v-if="shows[shows.length-1]">
      <h1>Hello</h1>
    </div>
    <div class="container" v-if="matchs.length === 0">
      <NotFound></NotFound>
    </div>
  </div>
</template>
<script>
  import Navbar from '@/components/navbar'
  import ProfileCard from '@/components/profile_card'
  import NotFound from '@/components/notfound'
  import { mapGetters } from 'vuex'
  export default{
    name:'home',
    data(){
      return {
        showProfile:false,
        matchs:[],
        notMatching:true
      }
    },
    components:{
      Navbar : Navbar,
      Card : ProfileCard,
      NotFound : NotFound
    },
    created(){
      this.$http.get('http://128.199.111.93:9001/matching?id='+JSON.parse(this.$localStorage.get('user')).id).then(
        (response) => {
          for (var index = 0; index < response.data.length; ++index) {
              this.matchs.push({
                index:index,
                id:response.data[index].id,
                username:response.data[index].username,
                name:response.data[index].name,
                lastname:response.data[index].lastname,
                age:response.data[index].age,
                location:response.data[index].locationName,
                distance:response.data[index].distance,
                descriptions:response.data[index].description,
                taste:response.data[index].sexTaste,
                images:response.data[index].imgProfile
              })
              if (index === 0) {
                this.$store.commit('initMatching',true)
              }else{
                this.$store.commit('initMatching',false)
              }
          }
          this.$store.commit('initMatching',false)
        }
      )
    },
    computed:mapGetters({
      shows: 'getShows'
    })
  }
</script>
<style src="../assets/css/home.css" scoped>
</style>
