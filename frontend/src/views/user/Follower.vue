<template>
  <div>
    <FollowUserProfile class="follow-profile"
        v-for="(follower, idx) in followers" :key="idx"
        :profile="follower"/>
  </div>
</template>

<script>
import {mapState} from "vuex";
import UserApi from "@/api/UserApi";

import FollowUserProfile from "@/components/user/UserProfileCard";

export default {
  name: "Follower",
  components: {
    FollowUserProfile
  },
  computed: {
    ...mapState({
      id: state => state.user.selectedProfile.id,
      followers: state => state.user.follow.followerList
    }),
  },
  created() {
    let params = {id: this.id}
    // 팔로워 리스트 요청
    UserApi.requestFollowers(
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
  },
  mounted() {
    // console.log(this.$refs.profile)
    const profile = document.querySelectorAll('.follow-profile')
    for ( let i = 0; i < profile.length; i ++) {
      if (i%2 === 0) {
        profile[i].className += 'background'
      }
    }
    // console.log(profile)
  }
}
</script>

<style scoped>
.background {
  /*background-color: #e1eedd;*/
}
</style>