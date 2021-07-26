<template>
  <footer>
    <div id="flex-container">
      <a href="/">메인</a>
      <a href="/quests">퀘스트</a>
      <a href="/user/feed">피드</a>
      <a href="/user/mileage">마일리지</a>
      <a :href="`/user/profile/${nickname}`">마이페이지</a>
    </div>
  </footer>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: "Footer",
  // components
  // props
  // data
  // methods
  // computed
  computed: {
    ...mapState({
        nickname: state => state.user.userNickname,
    })
  },
  // watch
  // lifecycle hook
}
</script>

<style scoped>
#flex-container {
  height: 100%;
  display: flex;
  align-items: center;
}

#flex-container a {
  flex: auto;
}

</style>