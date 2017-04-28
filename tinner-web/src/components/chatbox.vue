<template>
  <div id="chatbox" class="card">
      <div id="title-chatbox">
        <p>{{name}}</p>
        <a class="btn-flat dropdown-menu-chat menu-chat right" href="#!" data-beloworigin="true" data-alignment="right" data-activates='menu-chat'><i class="material-icons">toc</i></a>
        <ul id='menu-chat' class='dropdown-content'>
          <li><a href="#!">Report</a></li>
          <li><a href="#!">Unmatch</a></li>
        </ul>
      </div>
      <div id="main-chatbox">
      </div>
      <div id="textbox-chatbox">
        <div class="row">
          <div class="col s10">
            <input id="chat-input" type="text">
          </div>
          <div class="col s2">
            <a class="btn waves-effect pink">Send</a>
          </div>
        </div>
      </div>
  </div>
</template>
<script>
  export default{
    name:"ChatBox",
    props:['name'],
    mounted(){
      $('.dropdown-menu-chat').dropdown()
    },
    method:{
      send(){
      }
    }
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
