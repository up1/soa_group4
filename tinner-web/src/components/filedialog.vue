<template>
  <div id="filedialog">
    <div class="file-field input-field col s6">
      <div class="btn pink" v-on:change="chooseFiles()">
        <span>Image {{number}}</span>
        <input ref="file" id="fileUpload" type="file">
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
    data(){
      return {
        num : 0
      }
    },
    created(){
      this.num = this.number
    },
    methods:{
      chooseFiles: function() {
        let file = this.$refs.file.files;
        var value = {
          file : file[0],
          num : this.num
        }
        this.$store.commit('setImageEditInformation',value)
      }
    }
  }
</script>
<style scoped>

</style>
