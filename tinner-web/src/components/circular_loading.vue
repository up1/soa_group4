<template>
  <div class="preloader-wrapper big active margin-loading">
     <div class="spinner-layer spinner-blue">
       <div class="circle-clipper left">
         <div class="circle"></div>
       </div><div class="gap-patch">
         <div class="circle"></div>
       </div><div class="circle-clipper right">
         <div class="circle"></div>
       </div>
     </div>

     <div class="spinner-layer spinner-red">
       <div class="circle-clipper left">
         <div class="circle"></div>
       </div><div class="gap-patch">
         <div class="circle"></div>
       </div><div class="circle-clipper right">
         <div class="circle"></div>
       </div>
     </div>

     <div class="spinner-layer spinner-yellow">
       <div class="circle-clipper left">
         <div class="circle"></div>
       </div><div class="gap-patch">
         <div class="circle"></div>
       </div><div class="circle-clipper right">
         <div class="circle"></div>
       </div>
     </div>

     <div class="spinner-layer spinner-green">
       <div class="circle-clipper left">
         <div class="circle"></div>
       </div><div class="gap-patch">
         <div class="circle"></div>
       </div><div class="circle-clipper right">
         <div class="circle"></div>
       </div>
     </div>
   </div>
</template>
<script>
  export default{
    name:'CircularLoading'
  }
</script>
<style scoped>
  .margin-loading{
    margin: 10px;
  }
</style>
