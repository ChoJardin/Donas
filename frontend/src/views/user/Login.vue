<template>
  <div id="login">
    <h1>로그인</h1>
    <br><br><br>

    <UserInput class="user-input"
        id="email" label="Email" placeholder="이메일을 입력하세요" type="email"
        :input.sync="email" :error="error.email"
        @keyup-enter="moveFocusToPw" @on-focus="onFocus" ref="email"/>

    <UserInput class="user-input"
        id="password" label="비밀번호" placeholder="비밀번호를 입력하세요" type="password"
        :input.sync="password" :error="error.password"
        @keyup-enter="onLogin" @on-focus="onFocus" ref="password"/>

    <div v-if="loginError.isFailed" class="error-message">
      {{ loginError.message }}
    </div>

    <button class="button" :disabled="!isSubmit" @click="onLogin">로그인</button>

    <br><br><br>
    <button @click="$router.push('/signup')"> 회원가입 </button>

  </div>
</template>

<script>
import * as EmailValidator from "email-validator"
import PV from "password-validator"
import UserApi from "@/api/UserApi";
import UserInput from "@/components/common/UserInput";

export default {
  name: "Login",
  // components
  components: {
    UserInput
  },
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
          password,
          token: '12341234'
        }

        // 버튼 비활성화
        this.isSubmit = false

        UserApi.requestLogin(
            data,
            res => {
              // 로그인 실패
              if (res.data === 'NOT_FOUND') {
                this.loginError.isFailed = true
                this.$refs.email.onReset()
                this.$refs.password.onReset()
              } else {
                // 로그인 성공
                // 유저 닉네임 저장
                this.$store.dispatch('login', res.data)
                // 로그인 누르기 전 있던 곳으로
                this.$router.go(-1)
              }
            },
            err => {
              this.$router.push('/error')
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
#login {
  display: flex;
  flex-direction: column;
}

.user-input {
  width: 90%;
  margin-left: 5%;
}

.error-message {
  color: #cd4e3e;
  margin-bottom: 20px;
  font-size: 0.8em;
  font-weight: bold;
  width: 65%;
  align-self: center;
}

.button {
  width: 90%;
  margin-left: 5%;
  height: 50px;
  background-color: #183a1d;
  color: #e1eedd;
  border-radius:25px;
  box-shadow: 0 0 15px -8px rgba(0, 0, 0, 0.55);
  font-size: 1em;
  cursor: pointer;
}
.button:disabled {
  opacity: 0.6;
  cursor: default;
}


</style>