<template>
  <div>
    <!--ProfileEdit-->

    <!--profile start-->
    <div id="flex-container">

      <!--기본 프로필 start-->
      <div id="profile-wrap">
        <img v-if="profile.picture" class="profile-image" :src="profile.picture" alt="">
        <img v-else class="profile-image" src="@/assets/도넛1.png" alt="">
        <div id="profile-info">
          <div id="nickname">
            {{this.$route.params.nickname}}
          </div>
          <div id="badges">뱃지</div>
        </div>
        <!--메시지 전송 아이콘?-->
        <div v-if="!isMine">
          메세지 전송
        </div>
      </div>
      <!--기본 프로필 end-->

      <div id="description">
        소개글 컴포넌트
      </div>

      <!--팔로우/ 팔로잉/ 정보수정 start-->
      <div class="wrap">
        <!--팔로워-->
        <router-link :to="`/follow/${profile.nickname}/follower`" id="follow">
          <span>
            팔로워
          </span>
          <span class="cnt">
            {{ profile.follower }}
          </span>
        </router-link>

        <!--팔로잉-->
        <router-link :to="`/follow/${profile.nickname}/following`" id="follow">
          <span>
            팔로잉
          </span>
          <span class="cnt">
            {{ profile.following }}
          </span>
        </router-link>

        <!--isLoggedIn start-->
        <div v-if="this.isLoggedIn" id="profile-button">
          <!--본인 프로필-->
          <router-link :to="`/user/profile/${profile.id}/edit`" v-if="isMine" class="button">
            정보수정
          </router-link>
          <!--다른 유저 프로필-->
          <button v-else class="button" @click="onFollow">
            <span v-if="profile.isFollowing">
              팔로우 취소
            </span>
            <span v-else>
              팔로우
            </span>
          </button>
        </div>
       <!--isLoggedIn end-->

      </div>
      <!--팔로우/ 팔로잉/ 정보수정 end-->

    </div>
    <!--profile end-->

  </div>
</template>

<script>
import {mapState} from "vuex";

import('@/assets/style/user/Profile.css')

export default {
  name: "ProfileEdit",
  // components
  // props
  // data
  // methods
  // computed
  computed: {
    ...mapState({
      profile: state => state.user.selectedProfile
    })
  },
  // watch
  // lifecycle hook
}
</script>

<style scoped>

</style>