<template>
  <div id="chat">
    <Navbar></Navbar>
    <div class="container">
      <div class="row">
        <div class="section col l12 m9 l10">
          <div class="card card-container-padding fitpage" v-if="this.chatList.length > 0">
            <div class="row">
  						<div class="col s3">
  							<div class="card">
  								<div class="row">
  									<div class="col s12 center">
                        <div id="chat-list" class="collection fixbox">
                          <div v-for="(item, index) in this.chatList">
                            <ListBox :chat="item" ></ListBox>
                          </div>
                        </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col s9">
                <transition name="fade" mode="out-in">
                  <router-view></router-view>
                </transition>
              </div>
            </div>
          </div>
          <div v-if="this.loading">
            <Loading></Loading>
          </div>
          <div class="card card-container-padding center invalid-color" v-if="this.chatList.length === 0 && !this.loading">
            <h1>No Chat Avaliable</h1>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import Navbar from '@/components/navbar'
  import ListBox from '@/components/chat_list_box'
  import Loading from '@/components/loading'
  export default {
    name:"Chat",
    data(){
      return {
        chatList : [],
        loading:true
      }
    },
    components:{
      Navbar : Navbar,
      ListBox : ListBox,
      Loading : Loading
    },
    created(){
      this.fetchData()
    },
    methods:{
      'fetchData': function(){
        this.$http.get(this.$URL.CHAT+"/chatlist/"+JSON.parse(this.$localStorage.get('user')).id).then(
          result => {
            this.chatList = []
            for (var index = 0; index < result.data.length; ++index) {
              this.chatList.push(result.data[index])
            }
            this.loading = false
            setTimeout(this.fetchData, 5000);
          }
        )
      }
    }
  }
</script>
<style src="../assets/css/chat.css" scoped>
</style>
