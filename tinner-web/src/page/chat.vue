<template>
  <div id="chat">
    <Navbar></Navbar>
    <div class="container">
      <div class="row">
        <div class="section col l12 m9 l10">
          <div class="card card-container-padding fitpage">
            <div class="row">
  						<div class="col s3">
  							<div class="card">
                  <div class="progress pink accent-1" v-if="this.loading">
                      <div class="indeterminate red accent-3"></div>
                  </div>
  								<div class="row">
  									<div class="col s12 center">
                      <transition name="fade" mode="out-in">
                        <div id="chat-list" class="collection fixbox" v-for="(item, index) in this.chatList">
                          <ListBox :chat="item" ></ListBox>
                        </div>
                      </transition>
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
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import Navbar from '@/components/navbar'
  import ListBox from '@/components/chat_list_box'
  import CircularLoading from '@/components/circular_loading'
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
      Loading : CircularLoading
    },
    created(){
      this.$http.get(this.$URL.CHAT+"/chatlist/"+JSON.parse(this.$localStorage.get('user')).id).then(
        result => {
          for (var index = 0; index < result.data.length; ++index) {
            this.chatList.push(result.data[index])
          }
          this.loading = false
        }
      )
    }
  }
</script>
<style src="../assets/css/chat.css" scoped>
</style>
