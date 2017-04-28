<template>
  <router-link :to="{ name: 'Chatroom', params: { room: chat.channel } }" class="collection-item" v-bind:class="{active : this.room}">
    <div class="row chat-list">
      <div class="col s5">
        <i class="medium material-icons left">person_pin</i>
      </div>
      <div class="col s7 chat-name">
        <span>{{this.chat.name}}</span>
      </div>
    </div>
  </router-link>
</template>
<script>
  export default {
    name:"ChatList",
    props:['chat'],
    data(){
      return {
      }
    },
    computed:{
      room: function(){
        return this.$route.params.room === this.chat.channel
      }
    }
  }
</script>
<style scoped>
  .chat-list {
    margin-bottom: 0px;
  }
  .chat-name {
    margin-left: 100px;
    line-height: 4;
  }
</style>
