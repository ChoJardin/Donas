<template>
  <div>
    <!--ProfileEdit-->
    <div id="profile-flex-container">

      <ComponentNav
          @on-arrow="$router.back()"
          title="프로필 정보 수정"
          button="수정완료" @on-button="onComplete" :disabled="disableComplete"/>

      <!--프로필 사진-->
      <div class="label-content-wrap">
        <div class="profile-edit-label text">
          <div class="profile-edit-label">
            프로필
          </div>
          <div class="profile-edit-label">
            사진
          </div>
        </div>


        <div class="profile-edit-content">
          <div id="profile-image-input">
            <label for="image-input">
              <img v-if="picture" class="profile-image" :src="picture" alt="">
              <img v-else class="profile-image" src="../../assets/donut1.png" alt="">
            </label>
            <!--사진첩/ 카메라 선택창 호출됨-->
            <input type="file" id="image-input" accept="image/*; capture=camera" style="display: none;">
            <span class="material-icons-outlined position">add_photo_alternate</span>
          </div>
        </div>
      </div>
      <!--프로필 사진-->

      <!--닉네임-->
      <div class="label-content-wrap nickname">
        <div class="profile-edit-label">
          닉네임
        </div>
        <div class="profile-edit-content nickname">
          <input type="text" placeholder="닉네임을 입력해주세요." v-model="nickname">
          <!--<div  class="profile-edit-error">에러메세지노출</div>-->
          <div v-if="nicknameError" class="profile-edit-error">{{nicknameError}}</div>


          <button v-if="needCheck || nicknameError || !isChanged.nickname"
              class="profile-edit-check-button" :disabled="!needCheck"
              @click="checkNickname">
            중복확인
          </button>
          <button v-else class="profile-edit-check-button success">
            확인완료
          </button>
        </div>
      </div>
      <!--닉네임-->

      <!--description-->
      <div class="label-content-wrap description">
        <div class="profile-edit-label">
          인사말
        </div>
        <div class="profile-edit-content">
          <textarea
              name="description" id="" cols="27" rows="3"
              autocapitalize="sentences" wrap="hard"
              placeholder="인사말을 작성해보세요!"
              v-model="description"></textarea>
        </div>
      </div>
      <!--description-->

      <!--비밀번호 변경-->
      <button-big
          class="profile-password-change" value="비밀번호 변경하기"
          @on-click="onClick"/>
      <div v-if="passwordChanged">
        비밀번호가 성공적으로 변경되었습니다.
      </div>
      <transition name="pop-up">
        <PasswordChange v-if="isPasswordChange" :id="loginUser.id"
                        @on-click="onClick" @password-changed="onPwChanged" />
      </transition>
      <!--비밀번호 변경-->

    </div> <!--profile-flex-container ends-->

  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import UserApi from "../../api/UserApi";
import ComponentNav from "../../components/common/ComponentNav";
import ButtonBig from "../../components/common/ButtonBig";
import PasswordChange from "../../components/user/PasswordChange";

import('@/assets/style/user/Profile.css')

export default {
  name: "ProfileEdit",
  // components
  components: {
    ComponentNav,
    ButtonBig,
    PasswordChange
  },
  // props
  // data
  data() {
    return {
      picture: '',
      nickname: '',
      description: '',
      isPasswordChange: false,
      passwordChanged: false,
      needCheck: false,
      nicknameError: false,
      isChanged: {
        picture: false,
        nickname: false,
        description: false
      }
    }
  },
  // methods
  methods: {
    // 비밀번호 변경하기
    onClick() {
      this.isPasswordChange = !this.isPasswordChange
    },
    // 중복확인
    checkNickname() {
      let params = {nickname: this.nickname}
      UserApi.checkNickname(
          params,
          res => {
            if (res.data === "CONFLICT") {
              this.error.nickname = "사용이 불가능한 닉네임입니다."
              this.nickname = ''
            } else {
              this.needCheck = false
            }
          },
          err => {
            console.log('error occurred')
            // 에러 발생 페이지로 연결 필요
          }
      )
    },
    // 수정완료
    onComplete() {
      let data = {}
      // 값이 수정된 경우에만 data에 추가
      Object.keys(this.isChanged).filter(key => this.isChanged[key]).forEach(
          key => data[key] = this[`${key}`]
      )
      // let push = {path: ''}
      let path
      UserApi.updateProfile(
          this.loginUser.id,
          data,
          res => {
            // 성공응답이 오는 경우 내 프로필 페이지로 아니면 에러페이지로
            // 닉네임이 변경되었을 수 있기 때문에 로그인 유저 정보도 리셋이 필요합니다.
            path = res.data === 'OK' ? `/user/profile/${this.nickname}` : '/error'
            this.$router.push(path)
          },
          err => {
            console.log(err)
            // this.$router.push('/error')
          })
    },
    onPwChanged() {
      this.passwordChanged = true
    }
  },
  // computed
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
    }),
    ...mapGetters(['isLoggedIn']),

    // 수정완료버튼 비활성화
    disableComplete() {
      // 변경된 값이 없는 경우, 중복확인이 필요한 경우, 닉네임 에러가 있는 경우
      return !Object.values(this.isChanged).some(change => change)
          || this.needCheck
          || this.nicknameError
    }
  },
  // watch
  watch: {
    picture: function (v) {
      this.isChanged.picture = !!this.picture !== !!this.loginUser.picture
    },
    nickname: function(v) {
      // 중복확인 종료된 이후 데이터 값의 변화 -> 중복확인 버튼 활성화
      if (!this.needCheck)
        this.needCheck = true

      // 기존 닉네임과 달라진 경우 중복확인 버튼 활성화
      this.needCheck = this.nickname !== this.loginUser.nickname;
      this.isChanged.nickname = this.nickname !== this.loginUser.nickname;

      // 글자수 확인
      if (this.nickname.length > 8 || !this.nickname.length) {
        this.nicknameError = "1~8자 사이의 닉네임을 입력해주세요."
        this.needCheck = false
      }
      else this.nicknameError = false
    },
    description: function (v) {
      this.isChanged.description = !!this.description !== !!this.loginUser.description
    }
  },
  // lifecycle hook
  created() {
    // 초기 내 정보 요청
    // 로그인 하지 않은 사용자 로그인 페이지로
    if (!this.isLoggedIn) {
      this.$router.push('/login')
    }

    let { picture, nickname, description } = this.loginUser
    this.picture = picture
    this.nickname = nickname
    this.description = description

  },
  // navigation guard
  // 프로필 페이지로 돌아갈 때 변경사항 반영이 필요함
  beforeRouteLeave(to, from, next) {
    if (to.name === 'Profile') {
      this.$store.dispatch('requestLoginUserProfile', this.loginUser)
    }
    next()
  }
}
</script>

<style scoped>

.profile-edit-nav a,
.profile-edit-nav button {
  width: 60px;
}

/* 프로필 정보 수정 제목 */
.profile-edit-nav div {
  font-size: 1em;
  font-family: GongGothicBold;
  text-align: center;
}

/* 수정완료 버튼 */
.profile-edit-nav button {
  color: #183a1d;
  font-size: 0.8em;
  border: 1px solid #183a1d;
  padding: 2px 0;
  border-radius: 7px;
  box-shadow: 0 0 15px -9px rgba(0, 0, 0, 0.55);
}

.profile-edit-nav button:disabled {
  opacity: 0.5;
}

.label-content-wrap {
  display: flex;
  margin: 15px 5px 0 5px;
  border-bottom: 1px solid #292929;
  padding-bottom: 15px;
}

.profile-edit-label {
  flex: 1 1 0;
  font-size: 0.9em;
  align-self: center;
  text-align: center;
}

.profile-edit-label.text {
  display: flex;
  flex-direction: column;
}

.profile-edit-content {
  flex: 2.5 2.5 0;
}

/* 닉네임 */
.label-content-wrap.nickname {
  height: 70px;
}

/* 닉네임 입력창 */
.profile-edit-content.nickname input {
  width: 70%;
  padding-bottom: 2px;
  margin: 18px 2% 0 0;
  font-family: GongGothicLight;
  font-size: 0.9em;
}

.profile-edit-content.nickname input:focus {
  border-bottom: 1px solid #183a1d;
}

/* 닉네임 중복 확인 버튼 */
.profile-edit-check-button {
  width: 25%;
  color: #cd4e3e;
  font-size: 0.8em;
  cursor: pointer;
  border: 1px solid #cd4e3e;
  padding: 3px 1px;
  border-radius: 5px;
  box-shadow: 0 0 15px -8px rgba(0, 0, 0, 0.55);
  text-decoration: none;
}

.profile-edit-check-button:disabled {
  opacity: 0.5;
  pointer-events: none;
  cursor: default;
}

.profile-edit-check-button.success {
  color: #183a1d;
  border: none;
  box-shadow: none;
  cursor: default;
}

.profile-edit-error {
  position: absolute;
  font-family: GongGothicLight;
  font-size: 0.8em;
  color: #cd4e3e;
  padding-top: 3px;
}


/* 인사말 */
.label-content-wrap.description {
  height: 83px;
}


/* 인사말 입력 */
.profile-edit-content textarea {
  font-size: 0.9em;
  padding: 4px 0;
}

.profile-edit-content textarea:focus {
  border: 1px solid #183a1d;
}

/* 비밀번호 변경 */
.profile-password-change {
  margin-top: 20px
}

.pop-up-enter {
  opacity: 0;
  transform: translateY(100%);
}

.pop-up-enter-active {
  transition: all .5s ease;
}

.pop-up-leave-active {
  transition: all .5s ease;
}

.pop-up-enter-from,
.pop-up-leave-to {
  opacity: 0;
  transform: translateY(100%);
}

</style>