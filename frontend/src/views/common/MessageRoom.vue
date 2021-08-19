<template>
  <div>
    <ComponentNav @on-arrow="$router.back()"></ComponentNav>



      <div class="arrow-place" @click="$router.back()"></div>
      <div class="message-profile">

      <div>
        <img v-if="chatHistory.otherPicture" :src="chatHistory.otherPicture" alt=""
             @click="$router.push(`/user/profile/${chatHistory.otherName}`)">
        <img v-else src="https://donas.s3.ap-northeast-2.amazonaws.com/donuts/donut_profile.png" alt=""
             @click="$router.push(`/user/profile/${chatHistory.otherName}`)">
      </div>

      <div @click="$router.push(`/user/profile/${chatHistory.otherName}`)">{{chatHistory.otherName}}</div>
    </div>


    <div v-if="chatHistory.messages !== null">
      <div id="message-list">
        <div v-for="message in chatHistory.messages" :key="message.id">
          <div v-if="message.own === 1">
            <MeSend :message="message"></MeSend>
          </div>
          <div v-else>
            <YouSend :message="message"></YouSend>
          </div>
        </div>
      </div>
    </div>

    <MessageInput id="message-input" :receiveId="$route.params.id"></MessageInput>
  </div>
</template>

<script>
import {mapState} from "vuex";
import MessageInput from "@/components/common/MessageInput";
import ComponentNav from "@/components/common/ComponentNav";
import MeSend from "@/components/common/MeSend";
import YouSend from "@/components/common/YouSend";
import moment from "moment";

export default {
  name: "MessageRoom",
  components: {
    ComponentNav,
    MessageInput,
    MeSend,
    YouSend
  },
  data() {
    return {
    }
  },
  methods: {
    toRecent() {
      console.log('here')
      const messageList = this.$el.querySelector('#message-list')
      messageList.scrollTop(messageList.scrollHeight)
    }
  },
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
      chatHistory: state => state.user.chatHistory
    }),
    // messages() {
      // return this.$state.user.getters.messageCnt
    // }
  },
  watch: {
    // 메세지가 바뀌면, 다시 아래 스크롤
    // messages() {
    //   this.toRecent()
    // }
  },
  created() {
    // 메세지 내용 불러오기
    const data = {
      userId: this.loginUser.id,
      otherId: this.$route.params.id
    }
    this.$store.dispatch('setChat', data)
    this.$store.dispatch('setMessageList')
  },
  mounted() {
    console.log('mounted')
    // window.scrollTo(100, 100)
    // var messageList = document.getElementById('message-list')
    // console.log(messageList)
    // messageList.scrollTop = messageList.scrollHeight
    // this.toRecent()
  },
  updated() {
    console.log('여기 왔다 ')
    var messageList = document.getElementById('message-list').lastElementChild
    // console.log(messageList)
    messageList.scrollIntoView()
  }
}
</script>

<style scoped>

.arrow-place{
  position: fixed;
  height: 50px;
  width: 25px;
  /*border: 1px solid #292929;*/
  z-index: 7;
  cursor: pointer;
}


.message-profile {
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  z-index: 6;
  left: 0;
  right: 0;
  /*width: calc(100% - 30px);*/
  /*max-width: 395px;*/
  height: 50px;
  margin: auto;
}

.message-profile img {
  height: 35px;
  width: 35px;
  border-radius: 50%;
  border: 1px solid #292929;
  cursor: pointer;
}

.message-profile div {
  margin-left: 5px;
  font-family: GongGothicBold;
  font-size: 1.1em;
  cursor: pointer;

}



#message-list {
  display: flex;
  flex-direction: column;
  position: fixed;
  bottom: 120px;
  top: 120px;
  overflow-y: scroll;
  width: calc(100% - 30px);
  max-width: 400px;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  /*height: calc(100% - 120px);*/
}

#message-list::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}




#message-input {
  position: fixed;
  bottom: 70px;
  width: calc(100% - 30px);
  max-width: 400px;
}



</style>