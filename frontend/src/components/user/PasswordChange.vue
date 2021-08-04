<template>
  <div class="overlay">
    <div class="profile-edit-nav">
      <a href="#" @click.prevent="$emit('on-click')">
        <i class="material-icons color292929">arrow_back</i>
      </a>
      <div>
        비밀번호 변경
      </div>
      <button>
        저장
      </button>
    </div>

    <div id="profile-edit-password">
      <UserInput class="user-input"
          id="password" label="기존<br/>비밀번호" placeholder="기존 비밀번호를 입력하세요" type="password"
          :input.sync="password" :error="error.password"
          @keyup-enter="moveFocusToNewPw" ref="password"/>

      <UserInput class="user-input"
          id="new-password" label="새로운<br/>비밀번호" placeholder="새로운 비밀번호를 입력하세요" type="password"
          :input.sync="newPassword" :error="error.passwordConfirm"
          @keyup-enter="moveFocusToNewPwConfirm" ref="newPassword"/>

      <UserInput class="user-input"
          id="new-password-confirm" label="비밀번호<br/>확인" placeholder="새로운 비밀번호를 다시 입력하세요" type="password"
          :input.sync="newPasswordConfirm" :error="error.passwordConfirm"
          @keyup-enter="onSubmit" ref="newPasswordConfirm"/>
    </div>

  </div>
</template>

<script>
import UserInput from "../common/UserInput";

export default {
  name: "PasswordChange",
  // components
  components: {
    UserInput
  },
  // props
  // data
  data() {
    return {
      password: '',
      newPassword: '',
      newPasswordConfirm: '',
      error: {
        password: false,
        newPassword: false,
        newPasswordConfirm: false
      }

    }
  },
  // methods
  methods: {
    onSubmit() {
      console.log('clicked')
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
  }
  // computed
  // watch
  // lifecycle hook
}
</script>

<style scoped>
.overlay {
  position: fixed;
  top: 60px;
  right: 0;
  bottom: 0;
  left: 0;
  overflow: scroll;
  background: white;
}

.profile-edit-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 10px 15px 10px 15px;
  color: #183a1d;
}

.material-icons.color292929 {
  color: #183a1d;
}

#profile-edit-password {
  margin-top: 25px;
  width: 90%;
  margin-left: 5%;
}

</style>