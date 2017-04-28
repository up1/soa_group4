<template>
  <div class="row">
    <div class="section col l12 m9 l10">
      <div class="card card-container-padding">
        <div class="card">
          <div class="card-image">
            <div :id="this.sliderId" class="slider">
              <ul class="slides" >
                <li v-for="image in this.images">
                  <img :src="image">
                </li>
              </ul>
            </div>
          </div>
          <div class="card-content">
            <span class="name-header card-title activator" v-on:click="openOrClose()"><i class="material-icons right">more_vert</i>{{matching.name}} , {{matching.age}} <i class="material-icons" v-show="matching.status === 1">star</i></span>
            <ul>
              <li class="data-list"><i class="left material-icons">my_location</i>{{matching.location}}</li>
              <li class="data-list"><i class="left material-icons">room</i>{{matching.distance.toFixed(1)}} km away</li>
            </ul>
          </div>
          <transition name="fade">
            <div class="card-action" v-if="this.profile">
              <h6 class="description-subheader">Descriptions</h6>
                <ul>
                <li class="data-list"><i class="left material-icons">stars</i>{{matching.taste}}</li>
                <li class="data-list">
                  <p>{{matching.descriptions}}</p>
                  </li>
                </ul>
              </div>
            </transition>
            <div class="center card-action">
              <button class="btn red" v-on:click="nope()">Nope</button>
              <button class="btn blue" v-on:click="sLike()">Super Like</button>
              <button class="btn green" v-on:click="like()">Like</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
<script>
  export default{
    name:'profile_card',
    props:['profile','matching','sliderId'],
    data(){
      return{
        render:0,
        images:[]
      }
    },
    created(){
      this.images = this.matching.images
    },
    mounted(){
      $("#"+this.sliderId).slider()
    },
    methods:{
      nope(){
        this.comfirm(3)
      },
      sLike(){
        this.comfirm(1)
      },
      like(){
        this.comfirm(2)
      },
      openOrClose(){
        this.$emit('value', !this.profile)
      },
      comfirm(code){
        let data = {
          account_do : JSON.parse(this.$localStorage.get('user')).id,
          account_done : this.matching.id,
          status : code
        }
        this.$http.post(this.$URL.MATCHING+'/matching/status',data).then(
          response => {
            this.setShow()
            console.log("Hello");
          }
        )
      },
      setShow(){
        this.$store.commit('editShowMatching',{
          index : this.matching.index,
          action : false
        })
        this.$store.commit('editShowMatching',{
          index : this.matching.index + 1,
          action : true
        })
      }
    },
    watch:{
      'matching':function(){
        this.images = this.matching.images
      }
    }
  }
</script>
<style scoped>
  .card-container-padding{
    padding: 24px;
  }
  .name-header{
	color: #ff1744;
  font-weight: bold !important;
  font-size: 30px !important;
  }
  .name-header {
    font-weight: bold;
    font-size: 36px;
  }
  .data-list{
    padding: 4px 0px;
  }
  .description-subheader {
    font-weight: bold;
    font-size: 20px;
  }
</style>
