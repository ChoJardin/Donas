<template>
  <div>
    <h1>Signup</h1>
    <div>
      <label for="nickname">닉네임</label>
      <input
          id="nickname"
          v-model="nickname"
          placeholder="닉네임을 입력하세요."
          @keyup.enter="moveFocusToEmail"
          type="text">
      <div v-if="error.nickname">{{error.nickname}}</div>
    </div>

    <div>
      <label for="email">이메일</label>
      <input
          id="email"
          v-model="email"
          placeholder="이메일을 입력하세요."
          @keyup.enter="moveFocusToPw"
          type="text">
      <div v-if="error.email">{{error.email}}</div>
    </div>

    <div>
      <label for="password">비밀번호</label>
      <input
          id="password"
          v-model="password"
          placeholder="비밀번호를 입력하세요."
          @keyup.enter="moveFocusToPwConfirm"
          type="password">
      <div v-if="error.password">{{error.password}}</div>
    </div>

    <div>
      <label for="password-confirm">비밀번호 확인</label>
      <input
          id="password-confirm"
          v-model="passwordConfirm"
          placeholder="비밀번호를 다시 입력하세요."
          @keyup.enter="onSignup"
          type="password">
      <div v-if="error.passwordConfirm">{{error.passwordConfirm}}</div>
    </div>

    <button class="button" :disabled="isDisabled" @click="onSignup">회원가입</button>

  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator"

import UserApi from "@/api/UserApi";

export default {
  name: "Signup",
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
    }
  },
  // methods
  methods: {
    // validation
    checkForm() {
      if (this.nickname.length > 8)
        this.error.nickname = "최대 8자까지 입력 가능합니다."
      else this.error.username = false

      if (this.email.length > 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다."
      else this.error.email = false

      if (this.password.length > 0 && !this.passwordSchema.validate(this.password))
        this.error.password = "영문,숫자 포함 8~16자리를 입력해주세요.";
      else this.error.password = false;

      if (this.passwordConfirm.length > 0 && this.password !== this.passwordConfirm)
        this.error.passwordConfirm = "앞에서 입력하신 비밀번호와 일치하지 않습니다."
      else this.error.passwordConfirm = false

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
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
    },
    email: function (v) {
      this.checkForm()
      // 대문자 입력 방지
      this.email = this.email.toLowerCase()
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