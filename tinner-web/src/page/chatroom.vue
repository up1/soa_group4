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
        username: JSON.parse(this.$localStorage.get('user')).username,
        room: this.$route.params.room
      })
    },
    mounted(){
      this.$http.get(this.$URL.CHAT+"/chat/123/1235").then( data => {
        console.log(data)
      } );
    },
    destroyed(){
      this.$socket.emit('unsubscribe', {
        username: JSON.parse(this.$localStorage.get('user')).username,
        room: this.$route.params.room
      })
    }
  }
</script>
<style src="../assets/css/chat.css" scoped>
</style>
