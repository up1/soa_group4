<template>
  <div>
    <div class="col s2">
      <input class="group1 with-gap" name="group1" type="radio" id="male" value="male" v-model="gender" />
        <label for="male">Male</label>
    </div>
    <div class="col s2">
      <input class="group1 with-gap" name="group1" type="radio" id="female" value="female" v-model="gender"/>
        <label for="female">Female</label>
    </div>
  </div>
</template>
<script>
  export default {
      name:'gender_dialog',
      props: ['value'],
      data: () => ({
          gender: ''
      }),
      created () {
          this.gender = this.value
      },
      watch: {
          gender : function (val){
              this.$emit('value', this.gender)
              console.log(this.$emit('value', this.gender));
          }
      }
  }
</script>
