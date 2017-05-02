<template>
  <li class="message" v-bind:class="{'message-me':isOwnId}">
    {{this.data.reply}}
  </li>
</template>
<script>
  export default {
    name : 'MesssageBox',
    props : ['data'],
    computed:{
      'isOwnId': function() {
        return this.data.user_id === JSON.parse(this.$localStorage.get('user')).id
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
    background: #f8bbd0;
    float: right;
  }
</style>
