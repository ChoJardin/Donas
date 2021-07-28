<template>
  <div>
    <h1>로그인</h1>

    <div>
      <label for="email">이메일</label>
      <input
          id="email"
          v-model="email"
          placeholder="이메일을 입력하세요."
          @keyup.enter="moveFocusToPw"
          @focus="onFocus"
          type="text">
      <div v-if="error.email">{{error.email}}</div>
    </div>

    <div>
      <label for="password">비밀번호</label>
      <input
          id="password"
          v-model="password"
          placeholder="비밀번호를 입력하세요"
          @keyup.enter="onLogin"
          @focus="onFocus"
          type="password">
      <div v-if="error.password">{{error.password}}</div>
    </div>

    <button class="button" :disabled="!isSubmit" @click="onLogin">로그인</button>

  </div>
</template>

<script>
import * as EmailValidator from "email-validator"
import PV from "password-validator"
import UserApi from "@/api/UserApi";

export default {
  name: "Login",
  // components
  // props
  // data
  data() {
   return {
     email: "",
     password: "",
     passwordSchema: new PV(),
     error: {
       email: false,
       password: false
     },
     isSubmit: false,
     loginError: {
       isFailed: false,
       message: "입력하신 정보와 일치하는 이메일 혹은 비밀번호가 존재하지 않습니다."
     },
   }
  },
  // methods
  methods: {
    // 형식 검증
    checkForm() {
      // 이메일 형식 검증
      if (this.email.length > 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다."
      else this.error.email = false

      // 비밀번호 입력하고, 입력된 이메일 형식에 문제가 없으면 버튼 활성화
      if (this.password && this.email && !this.error.email)
        this.isSubmit = true
    },

    // 로그인
    onLogin() {
      if (this.isSubmit) {
        let {email, password} = this
        let data = {
          email,
          password
        }

        // 버튼 비활성화
        this.isSubmit = false

        UserApi.requestLogin(
            data,
            res => {
              console.log(res)
              // 유저 닉네임 저장
              this.$store.dispatch('login', res.data)
              // 로그인 누르기 전 있던 곳으로
              this.$router.go(-1)
            },
            error => {
              console.log(error)
              this.loginError.isFailed = true
              this.email = ""
              this.password = ""
            }
        )
      }
    },

    // 로그인 실패 에러메시지가 노출되어 있을 때,
    // 새로 로그인을 시도하는 경우, 해당 메시지 사라지도록
    onFocus() {
      this.loginError.isFailed = false
    },

    // 입력 칸 포커스 이동
    moveFocusToPw() {
      document.getElementById('email').blur()
      document.getElementById('password').focus()
    },
  },
  // computed
  // watch
  watch: {
    email: function (v) {
      this.checkForm()
      // 대문자 입력 방지
      this.email = this.email.toLowerCase()
    },
    password: function (v) {
      this.checkForm()
    }
  },
  // lifecycle hook
}
</script>

<style scoped>
  .button {
    width: 20vw;
    height: 10vw;
    background-color: aquamarine;
    cursor: pointer;
  }
  .button:disabled {
    background-color: aliceblue;
    cursor: default;
  }
</style>