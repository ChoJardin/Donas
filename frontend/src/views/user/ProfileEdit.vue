<template>
  <div>
    <!--ProfileEdit-->
    <div id="profile-flex-container">
      <div id="profile-edit-nav">
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
      <div class="label-content-wrap">
        <div class="profile-edit-label">
          닉네임
        </div>
        <div class="profile-edit-content">
          <input type="text" placeholder="닉네임을 입력해주세요." v-model="profile.nickname">

          <button class="profile-edit-button">
            중복확인
          </button>
        </div>
      </div>
      <!--닉네임-->

      <!--description-->
      <div class="label-content-wrap">
        <div class="profile-edit-label">
          인사말
        </div>
        <div class="profile-edit-content">
          <textarea
              name="description" id="" cols="27" rows="3"
              autocapitalize="sentences" wrap="hard"
              placeholder="소개를 작성해보세요!"
              v-model="profile.description"></textarea>
        </div>

      </div>
      <!--description-->

    </div> <!--profile-flex-container ends-->

  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import UserApi from "../../api/UserApi";

import('@/assets/style/user/Profile.css')

export default {
  name: "ProfileEdit",
  // components
  // props
  // data
  data() {
    return {
      profile: {
        nickname: '',
        picture: '',
        description: ''
      }
    }
  },
  // methods
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

#profile-edit-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 10px 0;
}

.material-icons.color292929 {
  color: #292929;
}

#profile-edit-nav a,
#profile-edit-nav button {
  width: 20%;
}

#profile-edit-nav div {
  font-size: 1.1em;
  font-weight: bold;
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
  font-weight: bold;
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

.profile-edit-button {
  width: 24%;
  color: #cd4e3e;
  font-size: 0.8em;
  font-weight: bold;
  cursor: pointer;
  border: 1px solid #cd4e3e;
  padding: 5px 2px;
  border-radius: 5px;
  box-shadow: 0 0 15px -9px rgba(0, 0, 0, 0.55);
  text-decoration: none;
}

</style>