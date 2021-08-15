<template>
  <div id="sign-in-up">
    <button class="back-button" @click="onBack">
      <i class="material-icons color292929">arrow_back</i>
    </button>

    <!--<componentNav  @on-arrow="$router.back()" />-->
    <div id="follow-wrap">
    <router-link to="/login" class="follow">
      로그인
    </router-link>
    <router-link to="/signup" class="follow">
      회원가입
    </router-link>
    </div>

    <router-view></router-view>
  </div>
</template>

<script>
import ComponentNav from "@/components/common/ComponentNav";

export default {
  name: "SignInUp",
  components: {
    // ComponentNav
  },
  methods: {
    onBack() {
      // 이전에 거쳐온 히스토리 기록이 없으면 메인 페이지로 -> 새로고침으로 인한 현상
      if (Object.keys(this.$route.params).length === 0) {
        this.$router.push('/main')
        // 프로필 페이지에서 넘어왔음
      } else if (this.$route.params.history === '/user/profile/undefined') {
        const nickname = this.$store.state.user.loginUser.nickname
        this.$router.push(`/user/profile/${nickname}`)
      // 이전에 왔던 곳으로
      } else {
        this.$router.push(this.$route.params.history)
      }
    }
  }
}
</script>

<style scoped>
#sign-in-up {
  position: absolute;
  /*top: 40px;*/
  /*width: 90%;*/
  width: calc(100% - 30px);
}

.back-button {
  display: flex;
  margin: 10px 0;
}

#follow-wrap {
  /*margin-top: 60px;*/
  height: 30px;
  margin: 20px 0 10px 0;
  width: 100%;
  display: flex;
  justify-content: space-between;
  font-size: 1em;
  font-family: GongGothicBold;
}

#follow-wrap a.router-link-exact-active {
  border-bottom: #f0a04b solid;
}

.follow {
  width: 90%;
  margin-left: 1%;
  margin-right: 1%;
  border-bottom: lightgray solid 1px;
  text-decoration: none;
  color: #183a1d;
  text-align: center;
}

</style>