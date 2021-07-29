<template>
  <div>
    <h1>Follower</h1>

    <FollowUserProfile
        v-for="(follower, idx) in followers" :key="idx"
        :follow="follower"/>
  </div>
</template>

<script>
import {mapState} from "vuex";
import UserApi from "@/api/UserApi";

import FollowUserProfile from "@/components/user/FollowUserProfile";

export default {
  name: "Follower",
  components: {
    FollowUserProfile
  },
  computed: {
    ...mapState({
      id: state => state.user.selectedProfile.id,
      followers: state => state.user.followers
    }),
  },
  created() {
    let params = {id: this.id}
    UserApi.requestFollower(
        params,
        res => {
          // 팔로워가 있는 경우에만 저장
          // default === ''
          if (res.data !== 'NOT_FOUND') {
            this.$store.dispatch('setFollowers', res.data)
          }
        },
        err => {
          // 에러페이지 연결
          // this.$router.push('/error')
        }
    )
  }
}
</script>

<style scoped>

</style>