<template>
  <li class="message" :class="'message-me':this.data">
    {{this.data.msg}}
  </li>
</template>
<script>
  export default {
    name : 'MesssageBox',
    props : ['data'],
    computed:{
      'isOwnId': function() {
        return this.data.id === JSON.parse(this.$localStorage.get('user')).id
      }
    }
  }
</script>
<style scoped>
  .message {
    background: #eee;
    padding: 10px 15px;
    border-radius: 20px;
    margin-bottom: 5px;
    float: left;
    clear: both;
  }
  .message-me {
    background: #ff1744;
    float: right;
  }
</style>
