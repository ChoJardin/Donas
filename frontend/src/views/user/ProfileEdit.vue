<template>
  <div>
    <!--ProfileEdit-->
    <div id="profile-flex-container">
      <div class="profile-edit-nav">
        <a href="#" @click.prevent="$router.back()">
          <i class="material-icons color292929">arrow_back</i>
        </a>
        <div>
          프로필 정보 수정
        </div>
        <button>
          수정완료
        </button>
      </div>

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
              <img v-if="profile.picture" class="profile-image" :src="profile.picture" alt="">
              <img v-else class="profile-image" src="@/assets/도넛1.png" alt="">
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
          <input type="text" placeholder="닉네임을 입력해주세요." v-model="profile.nickname">

          <button class="profile-edit-check-button">
            중복확인
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
              v-model="profile.description"></textarea>
        </div>
      </div>
      <!--description-->

      <!--비밀번호 변경-->
      <button-big
          class="profile-password-change" value="비밀번호 변경하기"
          @on-click="onClick"/>
      <transition name="pop-up">
        <PasswordChange v-if="isPasswordChange" @on-click="onClick"/>
      </transition>
      <!--비밀번호 변경-->

    </div> <!--profile-flex-container ends-->

  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import UserApi from "../../api/UserApi";
import ButtonBig from "../../components/common/ButtonBig";
import PasswordChange from "../../components/user/PasswordChange";

import('@/assets/style/user/Profile.css')

export default {
  name: "ProfileEdit",
  // components
  components: {
    ButtonBig,
    PasswordChange
  },
  // props
  // data
  data() {
    return {
      profile: {
        nickname: '',
        picture: '',
        description: ''
      },
      isPasswordChange: false
    }
  },
  // methods
  methods: {
    onClick() {
      this.isPasswordChange = !this.isPasswordChange
    }
  },
  // computed
  computed: {
    ...mapState({
      user: state => state.user.loginUser,
    }),
    ...mapGetters(['isLoggedIn'])
  },
  // watch
  // lifecycle hook
  created() {
    // 초기 내 정보 요청
    // 로그인 하지 않은 사용자 로그인 페이지로
    if (!this.isLoggedIn) {
      this.$router.push('/login')
    }

    UserApi.requestMyPage(
        this.user.id,
        res => {
          // 일치하는 유저 아이디 없음
          if (res.data === 'NOT_FOUND') {
            this.$router.push('/error')
          } else {
            // 정상적으로 유저 정보 가져온 경우
            this.profile = res.data
          }
        }
    )
  }
}
</script>

<style scoped>

.profile-edit-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  color: #183a1d;
}

.material-icons.color292929 {
  color: #183a1d;
}

.profile-edit-nav a,
.profile-edit-nav button {
  width: 60px;
}

/* 프로필 정보 수정 제목 */
.profile-edit-nav div {
  font-size: 1em;
  font-family: GongGothicBold;
  /*font-weight: bold;*/
  text-align: center;
  /*flex: 3.5 3.5 0*/
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
  font-family: GongGothicLight;
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
  /*font-weight: bold;*/
  cursor: pointer;
  border: 1px solid #cd4e3e;
  padding: 3px 1px;
  border-radius: 5px;
  box-shadow: 0 0 15px -9px rgba(0, 0, 0, 0.55);
  text-decoration: none;
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