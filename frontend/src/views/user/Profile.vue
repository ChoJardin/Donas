<template>
  <div>
    <!--profile start-->
    <div class="profile-flex-container" id="profile">

      <!--기본 프로필 start-->
      <div id="profile-wrap">
        <img v-if="profile.picture" class="profile-image" :src="profile.picture" alt="">
        <img v-else class="profile-image" src="@/assets/donut_profile.png" alt="">
        <div id="profile-info">
          <div id="profile-nickname">
            {{this.$route.params.nickname}}
          </div>
          <div id="profile-badges">뱃지</div>
        </div>
        <!--메시지 전송 아이콘?-->
        <div v-if="!isMine">
          메세지 전송
        </div>
      </div>
      <!--기본 프로필 end-->

      <div v-if="profile.description" v-html="parsedDescription" id="profile-description" ></div>
      <div v-else-if="isMine" class="info-say-hi">프로필 수정 페이지에서 인사말을 작성해 보세요!</div>

      <!--팔로우/ 팔로잉/ 정보수정 start-->
      <div class="profile-follow-wrap">
        <!--팔로워-->
        <router-link :to="`/follow/${profile.nickname}/follower`" class="profile-follow">
          <span>
            팔로워&nbsp;&nbsp;
          <!--</span>-->
          <!--<span>-->
            {{ profile.follower }}
          </span>
        </router-link>

        <!--팔로잉-->
        <router-link :to="{path: `/follow/${profile.nickname}/following`, params: {nickname: profile.nickname, id: profile.id}}" class="profile-follow">
          <span>
            팔로잉&nbsp;&nbsp;
          <!--</span>-->
          <!--<span>-->
            {{ profile.following }}
          </span>
        </router-link>

        <!--isLoggedIn start-->
        <!--isLoggedIn start-->
        <div v-if="this.isLoggedIn" id="profile-button">
          <!--본인 프로필-->
          <router-link to="/user/profile/edit" v-if="isMine" class="button">
            프로필 수정
          </router-link>
          <!--다른 유저 프로필-->
          <button v-else class="button" @click="onFollow">
            <span v-if="profile.isFollowing">
              팔로우 취소
            </span>
            <span v-else>
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
    <div id="profile-on-going-quests">
    <MyOnGoingQuests></MyOnGoingQuests>
    </div>

    <div id="profile-article-wrap">
      <div class="profile-article-image" v-for=" article in articles" :key="article.id">
        <ArticleImage class="inner" :article="article"/>
      </div>
    </div>
    <!--article end-->

    <transition name="slide-right">
      <router-view>
      </router-view>
    </transition>

  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import axios from "axios";

import UserApi from "@/api/UserApi";

import ArticleImage from "@/components/articles/ArticleImage";
import MyOnGoingQuests from "@/components/quests/MyOnGoingQuests";

import('@/assets/style/user/Profile.css')

export default {
  name: "Profile",
  // components
  components: {
    ArticleImage,
    MyOnGoingQuests
  },
  // props
  // data
  data() {
    return {
      params: '',
    }
  },
  // methods
  methods: {
    // 팔로우
    onFollow() {
      UserApi.requestFollow(
          !this.profile.isFollowing,
          // follower = 나 followee = 프로필 주인
          {followerId: this.loginUser.id, followeeId: this.profile.id},
          res => {
            // 요청 성공
            if (res.data === 'OK') {
              this.$store.dispatch('followFunction')
            } else {
              // 찾지 못한 경우
              this.$router.push('/404')
            }
          },
          err => {
            console.log(err)
            // this.$router.push('/error')
          }
      )
    },
    setProfile() {
      let myid = 0
      if (this.isLoggedIn) {
        myid = this.loginUser.id
      }
      let params = {myid: myid}
      const profile_owner = this.$route.params.nickname
      UserApi.requestProfileInfo(
          profile_owner,
          params,
          res => {
            if (res.data === 'NOT_FOUND') {
              this.$router.push('/404')
            } else {
              // const util = require('util')
              // console.log(util.inspect(res.data, {showHidden: false, depth: null}))
              const data = res.data
              let articles = data.articles
              articles.forEach(article => {
                article['makerName'] = data.nickname
                article['makerImage'] = data.picture
              }, articles)
              // articles = articles.map(article => {
              //   console.log(article)
              // })
              // console.log(articles)
              this.$store.dispatch('setUserProfile', res.data)
              this.$store.dispatch('setFeeds', articles)
            }
          },
          // 요청 실패하는 경우 -> 에러 페이지로 연결
          err => {
            // console.log(err)
            this.$router.push('/error')
          }
      )
    }
  },
  // computed
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
      articles: state => state.articles.feeds,
      profile: state => state.user.selectedProfile,
    }),
    ...mapGetters(['isLoggedIn']),
    // 내 프로필 페이지인지 확인
    isMine() {
      return this.loginUser.nickname === this.$route.params.nickname
    },
    parsedDescription() {
      return this.profile.description.replace(/\n/g, '<br/>')
    },

    // parsedDescription() {
    //   return this.profile.description.replace(/\n/g, '<br/>')
    // }
  },
  // watch
  watch: {
    // 로그인 상태가 바뀌는 경우
    // 페이지 다시 마운트
    'isLoggedIn'(v) {
      this.$mount()
    },
    '$route.params.nickname'(v) {
      this.setProfile()
    }
  },
  // lifecycle hook
  created() {
    if (!this.isLoggedIn && this.$route.params.nickname === 'undefined') {
      this.$router.push({name: 'Login', params: {history: this.$route.fullPath}})
      return
    }
    // 페이지 로딩시 초기 정보 요청
    // 비회원의 경우 0으로 요청 --> 백 확인 필요
    this.setProfile()
    console.log('생성인가 ')
  },
  // navigation guard
  beforeRouteEnter(to, from, next) {
    if (to.params.nickname === 'profileundefined') {
      next(vm => vm.$router.push(`/user/profile${vm.$store.state.user.loginUser.nickname}`))
    } else {
      next()
    }
  }
}
</script>

<style scoped>

/* 오른쪽 슬라이드 */
.slide-right-enter-active,
.slide-right-leave-active {
  transition-duration: 0.5s;
  transition-property: height, opacity, transform;
  transition-timing-function: cubic-bezier(0.55, 0, 0.1, 1);
  overflow: hidden;
}

.slide-right-leave-active,
.slide-right-enter {
  opacity: 1;
  transform: translate(100%, 0);
}


</style>
