<template>
  <div>
    <h1>Signup</h1>
    <br><br>

    <!--<div class="input-with-button-wrap">-->
      <!--<div class="input-with-button">-->
      <!--  <UserInput class="user-input set"-->
      <!--      id="nickname" label="닉네임" placeholder="닉네임을 입력하세요" type="text"-->
      <!--      :input.sync="nickname" :error="error.nickname" :label-right="needCheck.nickname"-->
      <!--      @keyup-enter="moveFocusToEmail" ref="nickname"/>-->
      <!--  <a @click.prevent="checkNickname" v-if="needCheck.nickname" :class="{disabled: error.nickname||!nickname.length}">중복확인</a>-->
      <!--  <a v-else class="success">확인완료</a>-->
      <!--</div>-->
        <UserInput class="user-input"
            id="nickname" label="닉네임" placeholder="닉네임을 입력하세요" type="text"
            :input.sync="nickname" :error="error.nickname" :label-right="needCheck.nickname"
            @keyup-enter="moveFocusToEmail" ref="nickname"/>
      <transition name="slide-fade">
        <Button80 v-if="needCheck.nickname && !error.nickname && nickname.length"
            class="user-input" :class="{disabled: error.nickname||!nickname.length}"
            value="중  복  확  인"/>
      </transition>
      <transition name="slide-fade">
        <Button80 v-if="needCheck.nickname"
            class="user-input" text="#6cb9a2" color="white"
            value="확 인 완 료"/>
      </transition>

      <!--<a @click.prevent="checkNickname" v-if="needCheck.nickname" :class="{disabled: error.nickname||!nickname.length}">중복확인</a>-->
      <!--<a v-else class="success">확인완료</a>-->

      <div class="input-with-button">
        <UserInput class="user-input set"
            id="email" label="이메일" placeholder="이메일을 입력하세요" type="email"
            :input.sync="email" :error="error.email"
            @keyup-enter="moveFocusToPw" ref="email"/>
        <a @click.prevent="checkEmail" v-if="needCheck.email" :class="{disabled: error.email||!email.length}">중복확인</a>
        <a v-else class="success">확인완료</a>
      </div>
    <!--</div>-->

    <UserInput class="user-input"
        id="password" label="비밀번호" placeholder="비밀번호를 입력하세요" type="password"
        :input.sync="password" :error="error.password"
        @keyup-enter="moveFocusToPwConfirm" ref="password"/>

    <UserInput class="user-input"
        id="password-confirm" label="비밀번호 확인" placeholder="비밀번호를 다시 입력하세요" type="password"
        :input.sync="passwordConfirm" :error="error.passwordConfirm"
        @keyup-enter="onSignup" ref="passwordConfirm"/>

    <button class="button" :disabled="isDisabled" @click="onSignup">회원가입</button>

  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator"

import UserApi from "@/api/UserApi";
import UserInput from "@/components/user/UserInput";
import Button80 from "@/components/common/ButtonBig";

import ('@/assets/style/user/Signup.css')

export default {
  name: "Signup",
  components: {UserInput, Button80},
  // components
  // props
  // data
  data: () => {
    return {
      nickname: "",
      email: "",
      password: "",
      passwordConfirm: "",
      passwordSchema: new PV(),
      error: {
        nickname: false,
        email: false,
        password: false,
        passwordConfirm: false,
      },
      isSubmit: false,
      needCheck: {
        nickname: true,
        email: true
      }
    }
  },
  // methods
  methods: {
    // validation
    checkForm() {
      if (this.nickname.length > 8)
        this.error.nickname = "최대 8자까지 입력 가능합니다."
      else this.error.nickname = false

      if (this.email.length > 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다."
      else this.error.email = false

      if (this.password.length > 0 && !this.passwordSchema.validate(this.password))
        this.error.password = "영문,숫자 포함 8~16자리를 입력해주세요.";
      else this.error.password = false;

      if (this.passwordConfirm.length > 0 && this.password !== this.passwordConfirm)
        this.error.passwordConfirm = "비밀번호가 일치하지 않습니다."
      else this.error.passwordConfirm = false

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },

    // 중복확인
    checkNickname() {
      // GET /user/nickname?nickname="kimssafy"
      let params = {nickname: this.nickname}
      UserApi.checkNickname(
          params,
          res => {
            if (res.data === "CONFLICT") {
              this.error.nickname = "사용이 불가능한 닉네임입니다."
              this.$refs.nickname.onReset()
            }
            else {
              this.needCheck.nickname = false
              // 다음칸으로 포커스 이동
              this.moveFocusToEmail()
            }
          },
          err => {
            console.log('error occurred')
            // 에러 발생 페이지로 연결 필요
          }
      )
    },
    checkEmail() {
      let params = {email: this.email}
      UserApi.checkEmail(
          params,
          res => {
            if (res.data === "CONFLICT") {
              this.error.email = "사용이 불가능한 이메일입니다."
              this.$refs.email.onReset()
            }
            else {
              this.needCheck.email = false
              this.moveFocusToPw()
            }
          },
          err => {
            console.log('error occurred')
            // 에러 발생 페이지로 연결 필요
          }
      )
    },

    // 회원가입
    onSignup() {
      if (this.isSubmit) {
        let { nickname, email, password } = this
        let data = {
          nickname,
          email,
          password
        }

        this.isSubmit = false;

        UserApi.requestSignup(
          data,
          res => {
            this.isSubmit = true;

            this.$router.go(-1)

          },
          error => {
            this.isSubmit = true;
            this.$router.push('/error')
          }
        );
      }
    },

    // enter 입력시 다음 칸으로 포커스 이동
    moveFocusToEmail() {
      document.getElementById('nickname').blur()
      document.getElementById('email').focus()
    },
    moveFocusToPw() {
      document.getElementById('email').blur()
      document.getElementById('password').focus()
    },
    moveFocusToPwConfirm() {
      document.getElementById('password').blur()
      document.getElementById('password-confirm').focus()
    },
  },
  // computed
  computed: {
    // 버튼 비활성화
    isDisabled() {
      return !(this.nickname.length && this.email.length && this.password.length && this.passwordConfirm.length && this.isSubmit)
    }
  },
  // watch
  watch: {
    // 형식 검증
    nickname: function (v) {
      this.checkForm()
      // 중복확인이 끝난 후 데이터 값에 변화가 있는 경우, 중복확인 버튼을 다시 살려주기
      if (!this.needCheck.nickname)
        this.needCheck.nickname = true
    },
    email: function (v) {
      this.checkForm()
      // 대문자 입력 방지
      this.email = this.email.toLowerCase()
      // 중복확인이 끝난 후 데이터 값에 변화가 있는 경우, 중복확인 버튼을 다시 살려주기
      if (!this.needCheck.email)
        this.needCheck.email = true
    },
    password: function (v) {
      this.checkForm()
    },
    passwordConfirm: function (v) {
      this.checkForm()
    }
  },
  // lifecycle hook
  created() {
    this.passwordSchema
      // 영문, 숫자 포함 8~16자리
      .is().min(8)
      .is().max(16)
      .has().digits()
      .has().letters()
  }
}
</script>

<style scoped>
/*.user-input {*/
/*  color: #6cb9a2;*/
/*}*/
</style>
