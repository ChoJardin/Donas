<template>
  <div>
    <router-link
        v-for="list in messageList" :key="list.id" class="to-each-chat"
        :to="`/message/${list.otherId}/${list.otherName}`">
      <EachRoom :info="list"></EachRoom>
    </router-link>

    <TransitionPage>
      <router-view></router-view>
    </TransitionPage>
  </div>
</template>

<script>
import {mapState} from "vuex";
import EachRoom from "@/components/common/EachRoom";
import TransitionPage from "@/components/common/TransitionPage";

export default {
  name: "MessageList",
  components: {
    EachRoom,
    TransitionPage
  },
  methods: {
    onClick() {
      console.log('clicked')
    }
  },
  computed: {
    ...mapState({
      messageList: state => state.user.messageList
    })
  },
  created() {
    // 모든 채팅방 내역 요청보내기
    this.$store.dispatch('setMessageList')
  }
}
</script>

<style scoped>
.to-each-chat {
  color: #292929;
  text-decoration: none;
}
</style>