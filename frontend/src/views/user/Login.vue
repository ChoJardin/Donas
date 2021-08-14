<template>
  <div id="login">
    <!--<button class="back-button" @click="$router.back()">-->
    <!--  <i class="material-icons color292929">arrow_back</i>-->
    <!--</button>-->
    <!--<ComponentNav @on-arrow="$router.back()"/>-->
    <!--<div id="title">-->
    <!--  로 그 인-->
    <!--</div>-->
    <div class="login-input">

    <UserInput class="user-input"
        id="email" label="Email" placeholder="이메일을 입력하세요" type="email"
        :input.sync="email" :error="error.email"
        @keyup-enter="moveFocusToPw" @on-focus="onFocus" ref="email"/>

    <UserInput class="user-input"
        id="password" label="비밀번호" placeholder="비밀번호를 입력하세요" type="password"
        :input.sync="password" :error="error.password"
        @keyup-enter="onLogin" @on-focus="onFocus" ref="password"/>
    </div>

    <div v-if="loginError.isFailed" class="error-message">
      {{ loginError.message }}
    </div>

    <button class="button" :disabled="!isSubmit" @click="onLogin">로 그 인</button>

    <!--소셜 로그인-->
    <!--<div id="login-social" class="login-description">-->
    <!--  <hr>-->
    <!--  <span style="margin-left: 8%">카카오로 함께하기</span>-->
    <!--  <img src="@/assets/kakao_login_medium.png" alt="카카오 로그인"-->
    <!--       class="kakao-login">-->
    <!--</div>-->
    <!--소셜 로그인-->
    <!--to 회원가입-->
    <div id="login-to-signup">
      <div class="login-description">
        <hr>
        <span> 소셜 로그인 </span>
        <hr>
      </div>
      <KakaoLogin class="kakao-login-long"></KakaoLogin>
    <!--<img src="@/assets/kakao_login_medium_wide.png" alt="" class="kakao-login-long">-->

    <!--<ButtonBig-->
    <!--    @on-click="$router.push({name: 'Signup', params: $route.params})"-->
    <!--    value="회 원 가 입" color="#e1eedd" text="#183a1d" id="to-signup-button"/>-->
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
import KakaoLogin from "@/views/user/KakaoLogin";

export default {
  name: "Login",
  // components
  components: {
    // ComponentNav,
    UserInput,
    KakaoLogin
    // ButtonBig
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
  },
  // lifecycle hook
  //navigation guard
  beforeRouteLeave(to, from, next) {
    if (to.name === 'Signup') {
      to.params.history = this.$route.params.history
      next(to.params)
    } else {
      next()
    }

  }
}
</script>

<style scoped>

#login {
  margin-top: 40px;
  display: flex;
  flex-direction: column;
  /*position: absolute;*/
  /*width: 90%;*/
  /*top: 60px;*/
}

#login .back-button {
  /*position: absolute;*/
  /*top: 10px;*/
  display: flex;
  margin-top: 10px;
}

#login #title {
  color: #183a1d;
  margin: 30px 0 40px;
  padding: 10px 0;
  font-family: GongGothicBold;
  font-size: 1.4em;
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
  margin-top: 20px;
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
  justify-content: space-between;
  margin-top: 40px;
}

.kakao-login {
  height: 40px;
}

.kakao-login-long {
  height: 50px;
  width: 90%;
  margin: auto;
  /*margin-left: 5%;*/
  /*margin-top: 10px;*/
}


#login-to-signup .login-description{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 80px;
  margin-bottom: 30px;
}


#to-signup-button {
  width: 90%;
  margin: auto;
}
</style>
