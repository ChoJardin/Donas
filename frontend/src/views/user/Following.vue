<template>
  <div>
    <FollowUserProfile
        v-for="(following, idx) in followings" :key="idx"
        :follow="following"/>
  </div>
</template>

<script>
import {mapState} from "vuex";
import UserApi from "@/api/UserApi";

import FollowUserProfile from "@/components/user/FollowUserProfile";

export default {
  name: "Following",
  components: {
    FollowUserProfile
  },
  computed: {
    ...mapState({
      id: state => state.user.selectedProfile.id,
      followers: state => state.user.follow.followingList
    }),
  },
  created() {
    let params = {id: this.id}
    // 팔로워 리스트 요청
    UserApi.requestFollowings(
        params,
        res => {
          // 팔로워가 있는 경우에만 저장
          // default === ''
          if (res.data !== 'NOT_FOUND') {
            this.$store.dispatch('setFollowings', res.data)
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