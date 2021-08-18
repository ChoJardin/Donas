<template>
  <div class="overlay">

    <!--<componentNav id="nav-password-change"-->
    <!--    @on-arrow="$emit('on-click')"-->
    <!--    title="비밀번호 변경"-->
    <!--    button="저장" @on-button="onSubmit"/>-->

    <div id="profile-edit-password">
      <UserInput class="user-input"
          id="password" label="기존<br/>비밀번호" placeholder="기존 비밀번호를 입력하세요" type="password"
          :input.sync="password" :error="error.password"
          @keyup-enter="moveFocusToNewPw" ref="password"/>

      <UserInput class="user-input"
          id="new-password" label="새로운<br/>비밀번호" placeholder="새로운 비밀번호를 입력하세요" type="password"
          :input.sync="newPassword" :error="error.newPassword"
          @keyup-enter="moveFocusToNewPwConfirm" ref="newPassword"/>

      <UserInput class="user-input"
          id="new-password-confirm" label="비밀번호<br/>확인" placeholder="새로운 비밀번호를 다시 입력하세요" type="password"
          :input.sync="newPasswordConfirm" :error="error.newPasswordConfirm"
          @keyup-enter="onSubmit" ref="newPasswordConfirm"/>

      <ButtonBig class="save-password" :class="{disabled: isDisabled}"
                 value="새로운 비밀번호 저장" @click.native="onSubmit"/>

    </div>

  </div>
</template>

<script>
import PV from 'password-validator'

import UserApi from "../../api/UserApi";
import ComponentNav from "../common/ComponentNav";
import UserInput from "../common/UserInput";
import ButtonBig from "@/components/common/ButtonBig";

export default {
  name: "PasswordChange",
  // components
  components: {
    ButtonBig,
    // ComponentNav,
    UserInput,

  },
  // props
  props: {
    id: Number
  },
  // data
  data() {
    return {
      password: '',
      newPassword: '',
      newPasswordConfirm: '',
      passwordSchema: new PV(),
      error: {
        password: false,
        newPassword: false,
        newPasswordConfirm: false
      },
      isSubmit: false
    }
  },
  // methods
  methods: {
    onSubmit() {
      console.log('clicked')
      let {id, password, newPassword} = this
      let data = {id, password, newPassword}
      UserApi.updatePassword(
          data,
          // 변경 성공 -> 회원정보 페이지로 보내면서 변경 성공 메세지 버튼 아래 노출
          // NOT_FOUND -> 기존의 비밀번호와 일치하지 않습니다. password error로 출력
          // error -> 에러 페이지로
          res => {
            console.log('get sth')
            console.log(res.data)
            if (res.data === 'NOT_FOUND') {
              this.error.password = "기존의 비밀번호와 일치하지 않습니다"
              this.$refs.password.onReset()
              this.$refs.newPassword.onReset()
              this.$refs.newPasswordConfirm.onReset()
            } else {
              this.$emit('on-click')
              this.$emit('password-changed')
            }
          },
          err => {
            this.$router.push('/error')
          }
      )
    },

    checkForm() {
      // 기존 비밀번호
      if (this.password.length > 0 && !this.passwordSchema.validate(this.password))
        this.error.password = "영문,숫자 포함 8~16자리를 입력해주세요.";
      else this.error.password = false;

      // 새 비밀번호
      if (this.newPassword.length > 0 && !this.passwordSchema.validate(this.newPassword))
        this.error.newPassword = "영문,숫자 포함 8~16자리를 입력해주세요.";
      else if (this.password === this.newPassword)
        this.error.newPassword = "기존의 비밀번호와 동일합니다."
      else this.error.newPassword = false;

      // 비밀번호 확인
      if (this.newPasswordConfirm.length > 0 && this.newPassword !== this.newPasswordConfirm)
        this.error.newPasswordConfirm = "비밀번호가 일치하지 않습니다."
      else this.error.newPasswordConfirm = false
    },

    // enter 입력시 다음 칸으로 포커스 이동
    moveFocusToNewPw() {
      document.getElementById('password').blur()
      document.getElementById('new-password').focus()
    },
    moveFocusToNewPwConfirm() {
      document.getElementById('new-password').blur()
      document.getElementById('new-password-confirm').focus()
    },
  },
  // computed
  computed: {
    isDisabled() {
      return !(this.password && this.newPassword && this.newPasswordConfirm &&
          !Object.keys(this.error).some(key => this.error[key])
      )
    }
  },
  // watch
  watch: {
    password: function(v) {
      this.checkForm()
    },
    newPassword: function(v) {
      this.checkForm()
    },
    newPasswordConfirm: function(v) {
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
/*#nav-password-change {*/
/*  z-index: 999999 !important;*/
/*}*/


.overlay {
  position: fixed;
  top: 60px;
  right: 15px;
  bottom: 0;
  left: 15px;
  overflow: scroll;
  background: white;
}

#profile-edit-password {
  margin-top: 85px;
  width: 90%;
  margin-left: 5%;
}

.save-password.disabled {
  pointer-events: none;
  opacity: 0.6;
}

</style>