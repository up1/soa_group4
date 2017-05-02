<template>
  <router-link :to="{ name: 'Chatroom', params: { room: chat.channel } }" class="collection-item" v-bind:class="{active : this.room}" :id="this.chat.channel">
    <div class="row chat-list">
      <div class="col s4">
        <i class="medium material-icons left">person_pin</i>
      </div>
      <div class="col s6 chat-name">
        <span>{{this.chat.name}}</span>
      </div>
      <div class="col s2" style="margin-top:20px;color:#ff6232" v-if="this.own">
        ⬤
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
    mounted(){
      if (this.room) {
        this.activeChat()
      }
    },
    computed:{
      room: function(){
        return this.$route.params.room === this.chat.channel
      },
      own: function(){
        return this.chat.room_status === 0 || this.chat.room_status !== JSON.parse(this.$localStorage.get('user')).id
      },
      channel: function(){
        return this.$route.params.room
      }
    },
    methods:{
      'activeChat': ()=>{
        $('#chatList').animate({
          scrollTop: $("#"+this.chat.channel).position().top},
          'fast','swing');
      }
    },
    watch:{
      'channel':function(){
        if (this.room !== undefined) {
          if (this.room && this.own) {
              this.$http.put(this.$URL.CHAT+"/chatroom",{
                channel : this.$route.params.room,
                user_id : this.chat.id,
                status:this.chat.room_status
              }).then( data => {
              })
          }
        }
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
    overflow:hidden;
    text-overflow: ellipsis;
  }
</style>
