<template>
  <div>
    <div class="col s2">
      <input class="group1 with-gap" :name="name" type="radio" :id="idMale" value="male" v-model="gender" />
        <label :for="idMale">Male</label>
    </div>
    <div class="col s2">
      <input class="group1 with-gap" :name="name" type="radio" :id="idFemale" value="female" v-model="gender"/>
        <label :for="idFemale">Female</label>
    </div>
  </div>
</template>
<script>
  export default {
      name:'gender_dialog',
      props: ['value','name','idMale','idFemale'],
      data(){
        return{
          gender: ''
        }
      },
      created () {
          this.gender = this.value
      },
      watch: {
          gender : function (val){
              this.$emit('value', this.gender)
          }
      }
  }
</script>
