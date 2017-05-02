<template>
  <div id="chatroom col s9">
      <ChatBox></ChatBox>
  </div>
</template>
<script>
  import Navbar from '@/components/navbar'
  import ListBox from '@/components/chat_list_box'
  import ChatBox from '@/components/chatbox'
  export default {
    name:"ChatRoom",
    components:{
      Navbar : Navbar,
      ListBox : ListBox,
      ChatBox: ChatBox
    },
    data(){
     return {

     }
    },
    created(){
      this.$socket.emit('subscribe', {
        username: JSON.parse(this.$localStorage.get('user')).id,
        room: this.$route.params.room
      })
    },
    destroyed(){
      this.$socket.emit('unsubscribe', {
        username: JSON.parse(this.$localStorage.get('user')).id,
        room: this.$route.params.room
      })
    },
    beforeRouteUpdate(to, from, next){
      this.$socket.emit('unsubscribe', {
        username: JSON.parse(this.$localStorage.get('user')).id,
        room: from.params.room
      })
      this.$socket.emit('subscribe', {
        username: JSON.parse(this.$localStorage.get('user')).id,
        room: to.params.room
      })
      next();
    }
  }
</script>
<style src="../assets/css/chat.css" scoped>
</style>
