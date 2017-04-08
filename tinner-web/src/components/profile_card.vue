<!--
  component : profile card for matching list
  original html code & design : Tanawat 57070025
  vue.js coding : Thatchakon 57070052
-->
<template>
  <div class="row">
    <div class="section col l12 m9 l10">
      <div class="card card-container-padding">
        <div class="card">
          <div class="card-image">
            <img src="../assets/patan.jpg">
          </div>
          <div class="card-content">
            <p>I am a very simple card. I am good at containing small bits of information.
              I am convenient because I require little markup to use effectively.</p>
            </div>
            <div class="center card-action">
              <button class="btn red" v-on:click="nope()">Nope</button>
              <button class="btn red" v-on:click="sLike()">Super Like</button>
              <button class="btn red" v-on:click="like()">Like</button>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>
<script>
  export default{
    name:'profile_card',
    methods:{
      nope(){
        alert("nope!")
      },
      sLike(){
        alert("Super Like!")
      },
      like(){
        alert("Like!")
      }
    }
  }
</script>
<style scoped>
  .card-container-padding{
    padding: 24px;
  }
</style>
