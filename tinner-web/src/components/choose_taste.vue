<template>
  <div class="col s2">
    <div class="select-wrapper">
      <select :id="id">
          <option value disabled selected>Choose your Taste</option>
          <option value="1">Normal</option>
          <option value="2">Top</option>
          <option value="3">Bottom</option>
      </select>
      <label>Taste Select</label>
    </div>
  </div>
</template>
<script>
  export default{
    name:'chooseTaste',
    props:['value','id'],
    data(){
      return {
        taste:0,
        selectId:''
      }
    },
    created(){
      this.taste = this.value
      this.selectId = "#"+this.id
    },
    mounted(){
      $('select').material_select()
      $(this.selectId).change((e)=>{
        this.taste = e.currentTarget.value
      })
    },
    watch:{
      taste: function　(val) {
        this.$emit('value', this.taste)
      }
    }
  }
</script>
