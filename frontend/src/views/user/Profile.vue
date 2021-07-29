<template>
  <div>
    <!--profile start-->
    <div id="flex-container">

      <!--기본 프로필 start-->
      <div id="profile-wrap">
        <img id="profile-image" :src="profile.picture" alt="">
        <div id="profile-info">
          <div id="nickname">
            {{this.$route.params.nickname}}
          </div>
          <div id="badges">뱃지</div>
        </div>
        <!--메시지 전송 아이콘?-->
        <div v-if="!isMine">
          메세지 전송
        </div>
      </div>
      <!--기본 프로필 end-->

      <div id="description">
        소개글 컴포넌트
      </div>

      <!--팔로우/ 팔로잉/ 정보수정 start-->
      <div class="wrap">
        <!--팔로워-->
        <router-link :to="`/follow/${profile.nickname}/follower`" id="follow">
          <span>
            팔로워
          </span>
          <span class="cnt">
            {{ profile.follower }}
          </span>
        </router-link>

        <!--팔로잉-->
        <router-link :to="`/follow/${profile.nickname}/following`" id="follow">
          <span>
            팔로잉
          </span>
          <span class="cnt">
            {{ profile.following }}
          </span>
        </router-link>

        <!--isLoggedIn start-->
        <div v-if="this.isLoggedIn" id="profile-button">
          <!--본인 프로필-->
          <router-link :to="`/user/profile/${profile.nickname}/edit`" v-if="isMine" class="button">
            정보수정
          </router-link>
          <!--다른 유저 프로필-->
          <button v-else class="button">
            <span v-if="profile.isFollowing">
              팔로우 취소
            </span>
            <span>
              팔로우
            </span>
          </button>
        </div>
       <!--isLoggedIn end-->

      </div>
      <!--팔로우/ 팔로잉/ 정보수정 end-->

    </div>
    <!--profile end-->

    <!--article start-->
    <div id="on-going-quests">
      진행 중 퀘스트 컴포넌트
    </div>

    <div id="article-wrap">
      <div class="article-image" v-for=" article in articles" :key="article.id">
        <ArticleImage class="inner" :article="article"/>
      </div>
    </div>
    <!--article end-->

  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";

import ArticleImage from "@/components/articles/ArticleImage";
import UserApi from "@/api/UserApi";

export default {
  name: "Profile",
  // components
  components: {
    ArticleImage
  },
  // props
  // data
  // methods
  // computed
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
      articles: state => state.articles.feeds,
      profile: state => state.user.selectedProfile,
    }),
    ...mapGetters(['isLoggedIn']),
    isMine() {
      return this.loginUser.nickname === this.$route.params.nickname
    }
  },
  // watch
  watch: {
    isLoggedIn(v) {
      this.$mount()
    }
  },
  // lifecycle hook
  created() {
    console.log('------')
    console.log(this.profile)
    // 페이지 로딩시 초기 정보 요청
    UserApi.requestProfileInfo(
        this.$route.params.nickname,
        res => {
          this.$store.dispatch('setUserProfile', res.data)
          this.$store.dispatch('setFeeds', res.data.articles)
        },
        // 요청 실패하는 경우 -> 에러 페이지로 연결
        err => {
          console.log(err)
          // this.$router.push('/error')
        }
    )


  }
}
</script>

<style scoped>
#flex-container {
  display: flex;
  flex-direction: column;
  text-align: start;
  margin: 15px 0px;
}

#profile-wrap {
  display: flex;
  height: 100px;
}

#profile-image {
  width: 100px;
  height: 100px;
  border: 1px solid #292929;
  border-radius: 50%;
}

#profile-info {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  flex: 2 2 0;
  margin-left: 15px;
}

#nickname {
  font-size: 1.8em;
  font-weight: bold;
}

#badges {

}


/* 소개글 */
#description {
  margin: 15px;
  font-size: 0.9em;
}

/* 팔로우 팔로잉 */
.wrap {
  height: 30px;
  display: flex;
  justify-content: space-between;
  font-size: 1.1em;
  font-weight: bold;
}

#follow {
  flex: 1 1 0;
  /*background-color: #cd4e3e;*/
  display: flex;
  justify-content: space-around;
  /*justify-content: center;*/
  align-items: center;
  text-decoration: none;
  color: #292929;
  cursor: pointer;
}

.cnt {
  font-size: 1.4em;
}

#profile-button {
  flex: 1 1 0;
  align-self: center;
}

#profile-button .button {
  width: 100%;
  float: right;
  text-decoration: none;
  text-align: center;
  align-self: center;
  color: #292929;
  background-color: #6cb9a2;
}

/* 진행 중인 퀘스트 */
#on-going-quests {
  height: 80px;
  display: flex;
  align-items: center;
  border: 1px solid black;
  margin-bottom: 15px
}

#article-wrap {
  width: 100%;
  margin-bottom: 60px;
  display: flex;
  flex-wrap: wrap;
}

.article-image {
  border: 1px solid olivedrab;
  width: 33.3333%;
  position: relative;
}

.article-image:after {
  content: "";
  display: block;
  padding-bottom: 100%;
}

.article-image .inner {
  position: absolute; /* Take your picture out of the flow */
  top: 0;
  bottom: 0;
  left: 0;
  right: 0; /* Make the picture taking the size of it's parent */
  width: 100%; /* This if for the object-fit */
  height: 100%; /* This if for the object-fit */
  object-fit: cover; /* Equivalent of the background-size: cover; of a background-image */
  object-position: center;
}



</style>