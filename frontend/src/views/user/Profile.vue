<template>
  <div>
    <!--profile start-->
    <div id="flex-container">

      <!--기본 프로필 start-->
      <div id="profile-wrap">
        <img id="profile-image" src="@/assets/도넛1.png" alt="">
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
        <div id="follower">
          팔로워 {{ follower }}
        </div>

        <!--팔로잉-->
        <div id="following">
          팔로잉 {{ following }}
        </div>

        <!--isLoggedIn start-->
        <div v-if="this.isLoggedIn" id="profile-button">
          <!--본인 프로필-->
          <button v-if="isMine">
            정보수정
          </button>
          <!--다른 유저 프로필-->
          <button v-else>
            <span>
              팔로우
            </span>
            <span>
              팔로우 취소
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
  data() {
    return {
    }
  },
  // methods
  // computed
  computed: {
    ...mapState({
        nickname: state => state.user.loginUser.nickname,
        articles: state => state.articles.feeds
    }),
    ...mapGetters(['isLoggedIn']),
    isMine() {
      return this.nickname === this.$route.params.nickname
    }
  },
  // watch
  // lifecycle hook
  created() {
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
  font-size: 1.3em;
  font-weight: bold;
}

#follower {
  flex: 1 1 0;
  background-color: #cd4e3e;
}

#following {
  flex: 1 1 0;

  background-color: #f1a64b;
}

#profile-button {
  flex: 1 1 0;
}

#profile-button button {
  width: 100%;
  height: 100%;
  float: right;
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