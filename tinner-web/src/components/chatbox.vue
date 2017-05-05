<template>
  <div id="chatbox" class="card">
    <transition name="fade" mode="out-in">
      <div class="progress pink accent-1" style="margin:0"v-if="this.loading">
          <div class="indeterminate red accent-3"></div>
      </div>
    </transition>
      <div id="title-chatbox">
        <p>{{this.getName}}</p>
        <a class="btn-flat dropdown-menu-chat menu-chat right" href="#!" data-beloworigin="true" data-alignment="right" data-activates='menu-chat'><i class="material-icons">toc</i></a>
        <ul id='menu-chat' class='dropdown-content'>
          <li><a href="#report">Report</a></li>
          <li><a href="#!" v-on:click="unmatch()">Unmatch</a></li>
        </ul>
      </div>
      <div id="main-chatbox">
        <ul v-for="(item, index) in this.chat">
          <Message :data="item" :id="index"></Message>
        </ul>
      </div>
      <div id="textbox-chatbox">
        <inputBox></inputBox>
      </div>
      <div id="report" class="modal">
        <div class="modal-content">
          <h4>Report</h4>
          <p>
            <input name="group1" type="radio" id="report1" value="0" v-model="report"  />
            <label for="report1">ชื่อไม่เหมาะสม</label>
          </p>
          <p>
            <input name="group1" type="radio" id="report2" value="1" v-model="report" />
            <label for="report2">พฤติกรรมไม่เหมาะสม</label>
          </p>
        </div>
        <div class="modal-footer">
          <a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat">Close</a>
          <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" v-on:click="sendReport()">Send</a>
        </div>
      </div>
  </div>
</template>
<script>
  import MessageBox from '@/components/message_box'
  import _ from 'lodash'
  import inputBox from '@/components/inputBox'
  export default{
    name:"ChatBox",
    components:{
      Message : MessageBox,
      inputBox : inputBox
    },
    data(){
      return {
        chat: [],
        inputText: '',
        dataPosition:0,
        data: {
          name: '',
          id: 0
        },
        loading:true,
        report:0
      }
    },
    mounted(){
      $('.dropdown-menu-chat').dropdown()
      this.getProfile(0)
      this.getChatInfomation()
      $('#main-chatbox').scroll(()=> {
          var height = $('#main-chatbox').scrollTop();
          if(height === 0 && this.chat.length > 0){
            this.getProfile(this.chat.length)
          }
      })
      $('.modal').modal()
    },
    methods:{
      sendReport(){
        let description = ['ชื่อไม่เหมาะสม','พฤติกรรมไม่เหมาะสม']
        this.$http.post(this.$URL.REPORT+"/report"+"/request",{
          reporter_id : JSON.parse(this.$localStorage.get('user')).id,
          reported_id : this.data.id,
          report_topic : description[this.report]
        }).then( data => {
          Materialize.toast("Report เรียบร้อย", 2000)
        },
        response => {
          Materialize.toast("เกิดข้อผิดพลาดกรุณาลองใหม่", 2000 ,'red darken-4')
        })
      },
      removeDuplicates(){
        this.chat = _.uniqBy(this.chat, 'cr_id')
      },
      unmatch(){
        this.$http.put(this.$URL.MATCHING+"/matching/unmatch",{
          account_do : JSON.parse(this.$localStorage.get('user')).id,
          account_done : this.data.id,
          status:0
        }).then( data => {
          Materialize.toast("Unmatch เรียบร้อย", 1000,'',()=>{this.$router.push('/')})
        },
        response => {
          Materialize.toast("เกิดข้อผิดพลาดกรุณาลองใหม่", 2000 ,'red darken-4')
        })
      },
      getChatInfomation(){
        this.$http.get(this.$URL.CHAT+"/chat/"+this.$route.params.room).then( data => {
          this.data.name = data.body.name
          this.data.id = data.body.user_id
        })
      },
      getProfile(offset){
        this.$http.get(this.$URL.CHAT+"/chat/"+this.$route.params.room+"/"+offset).then( data => {
          if (offset === 0) {
            for(var index = 0 ; index < data.body.length ; index++){
              this.chat.push(data.body[index])
            }
            $('#main-chatbox').animate({
              scrollTop: $("#main-chatbox").prop('scrollHeight')+$("#main-chatbox").prop('clientHeight')},
              'fast','swing');
          }else{
            for(var index = data.body.length-1 ; index > 0 ; index--){
              this.chat.unshift(data.body[index])
            }
          }
          this.removeDuplicates()
          this.dataPosition = data.body.length
          this.loading = false
        })
      }
    },
    computed:{
      'getName':function(){
        return this.data.name
      },
      'room': function(){
        return this.$route.params.room
      }
    },
    updated(){
      if ($('#'+this.dataPosition).position() !== undefined) {
        var height = $('#'+this.dataPosition).position().top
        $('#main-chatbox')[0].scrollTop = height-150
      }
    },
    watch:{
      'room':function(){
        this.chat=[]
        this.data.name = ''
        this.loading = true
        this.report = 0
        this.getProfile(0)
        this.getChatInfomation()
        this.$forceUpdate()
      }
    },
    sockets:{
      'conversation private post':function(val){
        this.chat.push(val)
        $('#main-chatbox').animate({
          scrollTop: $("#main-chatbox").prop('scrollHeight')+$("#main-chatbox").prop('clientHeight')},
          'fast','swing');
      }
    },
  }
</script>
<style scope>
  #chatbox{
    height: 76vh;
  }
  #title-chatbox{
    height: 10%;
    padding: 3px;
    padding-left: 10px;
    border-bottom: 1px solid #e0e0e0;
  }
  #title-chatbox p{
    display: inline-block;
  }
  #title-chatbox a{
    margin-top: 7px;
  }
  #textbox-chatbox{
    border-top: 1px solid #e0e0e0;
    width: 100%;
    position: absolute;
    height: 10%;
    bottom: 0;
    padding: 5px;
    padding-left: 10px;
    padding-right: 10px
  }
  #textbox-chatbox a{
    margin-top: 7px
  }
  #main-chatbox{
    overflow: auto;
    height: 80%;
    padding:10px
  }
  .menu-chat{
    padding: 0 0.5rem !important
  }
  #main-chatbox::-webkit-scrollbar-track
  {
  	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
  	background-color: #F5F5F5;
  }

  #main-chatbox::-webkit-scrollbar
  {
  	width: 2px;
  	background-color: #F5F5F5;
  }

  #main-chatbox::-webkit-scrollbar-thumb
  {
  	background-color: #000000;
  	border: 2px solid #555555;
  }

</style>
