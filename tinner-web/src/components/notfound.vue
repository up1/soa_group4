<template>
  <div class="row">
    <div class="section col l12 m9 l10">
      <div class="card card-container-padding">
        <div class="card">
          <div class="card-content center invalid-color">
            <h1>{{this.msg}}</h1>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'NotFound',
    data(){
      return{
        msg:'User matching not found'
      }
    }
  }
</script>
<style scoped>
  h1{
    font-size: 3em
  }
</style>
