<template>
  <div id="login">
    <ComponentNav @on-arrow="$router.back()"/>
    <div id="title">
      로그인
    </div>

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

    <button class="button" :disabled="!isSubmit" @click="onLogin">로 그 인</button>

    <!--소셜 로그인-->
    <div id="login-social" class="login-description">
      <hr>
      <span style="margin-left: 8%">소셜 로그인</span>
    </div>
    <!--소셜 로그인-->

    <!--to 회원가입-->
    <div id="login-to-signup">
      <div class="login-description">
        <hr>
        <span> 아직 회원이 아니신가요? </span>
        <hr>
      </div>

    <ButtonBig
        @on-click="$router.push({name: 'Signup', params: $route.params})"
        value="회 원 가 입" color="#e1eedd" text="#183a1d" id="to-signup-button"/>
    </div>
    <!--to 회원가입-->


  </div>
</template>

<script>
import * as EmailValidator from "email-validator"
import PV from "password-validator"
import UserApi from "@/api/UserApi";
import ComponentNav from "@/components/common/ComponentNav";
import UserInput from "@/components/common/UserInput";
import ButtonBig from "@/components/common/ButtonBig";
import { token } from '@/services/messaging/messaging.ts';

export default {
  name: "Login",
  // components
  components: {
    ComponentNav,
    UserInput,
    ButtonBig
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
          token: token.value
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
                // 유저 정보 다시 불러옵니다.
                this.$store.dispatch('requestLoginUserProfile', res.data)
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
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn
    }
  },
  // watch
  watch: {
    email: function (v) {
      this.checkForm()
      // 대문자 입력 방지
      this.email = this.email.toLowerCase()
    },
    password: function (v) {
      this.checkForm()
    },
    isLoggedIn() {
      if (this.$route.params.history === '/user/profile/undefined') {
        const nickname = this.$store.state.user.loginUser.nickname
        this.$router.push(`/user/profile/${nickname}`)
      } else {
        this.$router.push(this.$route.params.history)
      }
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

#login #title {
  color: #183a1d;
  margin-bottom: 30px;
  padding: 10px 0;
  font-family: GongGothicBold;
  font-size: 1.2em;
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
  margin-top: 25px;
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

/* 소셜로그인, 회원가입 */
.login-description{
  width: 90%;
  margin-left: 5%;
  font-family: GongGothicLight;
  font-size: 0.9em;
  color: #183a1d;
}

hr {
  display: inline-block;
  width: calc((100% - 200px) / 2)
}

#login-social {
  display: flex;
  align-items: center;
  justify-items: start;
  margin-top: 60px;
}

#login-to-signup .login-description{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 60px;
  margin-bottom: 30px;
}


#to-signup-button {
  width: 90%;
  margin: auto;
}
</style>
