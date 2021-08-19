<template>
  <div>
    <ComponentNav @on-arrow="$router.back()" :title="$route.params.nickname"></ComponentNav>

    <div style="display: flex; flex-direction: column" id="message-list">

    <div v-for="message in chat.messages" :key="message.id" style="height: 100px">
      {{message}}
    </div>

    </div>


    <MessageInput id="message-input" :receiveId="$route.params.id"></MessageInput>
  </div>
</template>

<script>
import MessageInput from "@/components/common/MessageInput";
import ComponentNav from "@/components/common/ComponentNav";
import {mapState} from "vuex";

export default {
  name: "MessageRoom",
  components: {
    ComponentNav,
    MessageInput
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
      chat: state => state.user.chatHistory
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
  },
  mounted() {
    console.log('mounted')
    // window.scrollTo(100, 100)
    var messageList = document.getElementById('message-list')
    console.log(messageList)
    messageList.scrollTop = messageList.scrollHeight
    // this.toRecent()
  }
}
</script>

<style scoped>

#message-list {
  display: flex;
  flex-direction: column;
  position: fixed;
  bottom: 120px;
  top: 120px;
  overflow-y: scroll;
  /*height: calc(100% - 120px);*/
}


#message-input {
  position: fixed;
  bottom: 70px;
  width: calc(100% - 30px);
  max-width: 400px;
}



</style>