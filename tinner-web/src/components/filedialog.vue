<template>
  <div id="filedialog">
    <div class="file-field input-field col s6">
      <div class="btn pink" v-on:change="chooseFiles()">
        <span>Image {{number}}</span>
        <input id="fileUpload" type="file">
      </div>
      <div class="file-path-wrapper">
        <input class="file-path validate" type="text">
      </div>
    </div>
  </div>
</template>
<script>
  export default{
    name:'FileDialog',
    props:['number'],
    methods:{
      chooseFiles: function() {
      console.log($('#fileUpload'))
      }
    }
  }
</script>
<style scoped>

</style>
