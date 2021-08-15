<template>
  <div id="kakao" @click="kakaoLogin">
    <img src="@/assets/kakao_login_medium_wide.png" alt="" class="kakao-label">
  </div>
</template>

<script>
import UserApi from "@/api/UserApi";

export default {
  name: "KakaoLogin",
  methods: {
    kakaoAuth() {
      // UserApi.kakaoLogin()
      const params = {
        redirectUri: "http://localhost:8080/dev/test"
      }
      this.$kakao.Auth.authorize(params)
    },
    kakaoLogin() {
      this.kakaoAuth()

    }
  },
  watch: {
    '$route.query.code'(v) {
      console.log('코드 받아옴')
      console.log(this.$route.query.code)
    }
  }
}
</script>

<style scoped>
#kakao {
  width: 100%;
}
/*.kakao-label {*/
/*  width: 100%;*/
/*}*/
</style>