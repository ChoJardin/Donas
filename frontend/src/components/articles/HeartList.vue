<template>
  <div class="overlay">

    <componentNav
        @on-arrow="$emit('on-click')"
        title="좋아요"/>

  </div>
</template>

<script>
import PV from 'password-validator'

import UserApi from "../../api/UserApi";
import ComponentNav from "../common/ComponentNav";

export default {
  name: "PasswordChange",
  // components
  components: {
    ComponentNav,
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

  },
  // computed
  // watch
  // lifecycle hook
}
</script>

<style scoped>

#profile-edit-password {
  margin-top: 25px;
  width: 90%;
  margin-left: 5%;
}

</style>