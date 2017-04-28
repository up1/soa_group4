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
    mounted(){
      this.$http.get(this.$URL.CHAT+"/chat/123/1235").then( data => {
        console.log(data)
      } );
    }
  }
</script>
<style src="../assets/css/chat.css" scoped>
</style>
