<template>
  <div>
    <ComponentNav @on-arrow="$router.back()" title="메시지"></ComponentNav>

    <div style="margin-top: 60px">
      <router-link
          v-for="list in messageList" :key="list.id" class="to-each-chat"
          :to="`/message/${list.otherId}/${list.otherName}`">
        <EachRoom :info="list"></EachRoom>
      </router-link>

      <TransitionPage>
        <router-view></router-view>
      </TransitionPage>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import EachRoom from "@/components/common/EachRoom";
import TransitionPage from "@/components/common/TransitionPage";
import ComponentNav from "@/components/common/ComponentNav";

export default {
  name: "MessageList",
  components: {
    ComponentNav,
    EachRoom,
    TransitionPage,
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