<template>
  <div>
    <h1>Tinner Project</h1>
    <h1>Powered By Vue.js | Spring Boot</h1>
    <a class="waves-effect waves-light btn" href="#modal1">button</a>
    <div id="modal1" class="modal">
      <div class="modal-content">
        <h4>Modal Header</h4>
        <p>A bunch of text</p>
      </div>
      <div class="modal-footer">
        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Agree</a>
      </div>
      </div>
  </div>
</template>
<script>
  export default{
    name:'landing',
    components:{
    },
    mounted(){
      $('.modal').modal();
    }
  }
</script>
<style scope>
  h1{
    font-family: 'Josefin Sans', sans-serif;
  }
</style>
