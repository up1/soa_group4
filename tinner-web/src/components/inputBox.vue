<template>
  <div class="row">
    <div class="col s10">
      <input type="text" v-model="inputText">
    </div>
    <div class="col s2">
      <a class="btn waves-effect pink" v-on:click="send('text')">Send</a>
    </div>
  </div>
</template>
<script>
  export default {
    name:'input',
    data(){
      return {
        inputText : ''
      }
    },
    methods:{
        send(text){
          if (text === 'text') {
            this.$socket.emit('send message', {
              user_id: JSON.parse(this.$localStorage.get('user')).id,
              room:this.room,
              reply:this.inputText
            })
            this.inputText = ''
            this.$forceUpdate()
          }
        }
    },
    computed:{
      'room': function(){
        return this.$route.params.room
      }
    }
  }
</script>
