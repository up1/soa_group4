<template>
  <div id="home">
    <Navbar></Navbar>
      <div class="container" v-for="(item, index) in this.matchs">
        <Card :matching="item" :sliderId="index" :profile="showProfile" @value="showProfile = $event" v-if="shows[index]"></Card>
      </div>
      <div class="container" v-if="shows[shows.length-1]">
        <NotFound></NotFound>
      </div>
      <div class="container" v-show="matchs.length === 0 && !loading">
        <NotFound></NotFound>
      </div>
      <div class="container" v-show="this.loading">
        <Loading></Loading>
      </div>
  </div>
</template>
<script>
  import Navbar from '@/components/navbar'
  import ProfileCard from '@/components/profile_card'
  import NotFound from '@/components/notfound'
  import Loading from '@/components/loading'
  import URL from '@/assets/url'
  import { mapGetters } from 'vuex'
  export default{
    name:'home',
    data(){
      return {
        showProfile:false,
        matchs:[],
        notMatching:true,
        loading:true
      }
    },
    components:{
      Navbar : Navbar,
      Card : ProfileCard,
      NotFound : NotFound,
      Loading : Loading
    },
    created(){
      this.$http.get(this.$URL.MATCHING+'/matching?id='+JSON.parse(this.$localStorage.get('user')).id).then(
        (response) => {
          this.loading = true
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
                images:response.data[index].imgProfile,
                status:response.data[index].superlike_status
              })
              if (index === 0) {
                this.$store.commit('initMatching',true)
              }else{
                this.$store.commit('initMatching',false)
              }
          }
          this.$store.commit('initMatching',false)
          this.loading = false
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
