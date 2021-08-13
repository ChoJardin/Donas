<template>
  <div class="overlay">
    <div id="article-detail">

      <!--nav-->
      <component-nav @on-arrow="$router.back()" title="인증 게시글"/>
      <button v-if="isMine" @click="onClick" class="material-icons-round more">more_horiz</button>
      <div v-if="openButton">
        <button>
          수정
        </button>
        <button>
          삭제
        </button>
      </div>
      <!--nav-->

      <!--article-->
      <router-link :to="`/user/profile/${selectedArticle.makerName}`" class="profile-router">
        <div class="profile-wrap">
          <img v-if="selectedArticle.makerImage" :src="selectedArticle.makerImage" alt="" class="article-detail-profile-image">
          <img v-else src="@/assets/donut_profile.png" alt="" class="article-detail-profile-image">
          <div class="info-wrap">
            <div class="nickname">
              {{selectedArticle.makerName}}
            </div>  <!--nickname end -->
            <div class="article-date">
              <!--수정됐으면 수정된 날짜만 보이도록 했습니다. 원래 날짜도 같이 보이는게 좋은가여? -->
              <p v-if="selectedArticle.updatedAt">{{dateFormatted(selectedArticle.updatedAt)}} <span>수정</span></p>
              <p v-else>{{dateFormatted(selectedArticle.createdAt)}}</p>
            </div>  <!--article-date end-->
          </div>  <!--info-wrap end-->
        </div>
      </router-link>

      <div class="main-image">
        <img v-if="selectedArticle.image" :src="selectedArticle.image" alt="">
        <img v-else src="@/assets/donut_flag.png" alt="">
      </div>

      <!--퀘스트-->
      <router-link :to="`/quest/${selectedArticle.questId}`" class="quest-router">
        <span v-if="selectedArticle.type==='G'" class="quest-type">공동</span>
        <span v-else-if="selectedArticle.type==='P'" class="quest-type">개인</span>
        <span v-else class="quest-type">릴레이</span>
        <span class="quest-title">{{selectedArticle.questTitle}}</span>
      </router-link>

      <div v-html="parsedContent" id="article-content" />

      <!--좋아요/ 댓글-->
      <div class="heart-comment">
        <button @click="onLike" class="heart-cnt">
          <i v-if="selectedArticle.like" class="material-icons red">favorite</i>
          <i v-else class="material-icons">favorite_border</i>
          &nbsp;{{selectedArticle.heartCnt}}
        </button>
        <button @click="onHeartList">
          <span v-if="selectedArticle.heartCnt >= 2">
            {{likeUser}} 님 외 <span class="link">{{selectedArticle.heartCnt-1}}명</span>이 이 게시글을 좋아합니다.
          </span>
          <span v-else-if="selectedArticle.heartCnt === 1">
            {{likeUser}} 님이 이 게시글을 좋아합니다.
          </span>
          <span v-else>첫번째로 이 게시글을 좋아해주세요!</span>
        </button>

        <transition name="pop-up">
          <HeartList v-if="isHeartList" @on-click="onHeartList" />
        </transition>

      </div>
      <!--article-->

      <!--comment-->
      <CommentSet id="comment-set"/>




    </div>
  </div>
</template>

<script>
import moment from "moment";
import {mapGetters, mapState} from "vuex";

import ComponentNav from "../common/ComponentNav";
import HeartList from "@/components/articles/HeartList";
import ArticlesApi from "@/api/ArticlesApi";
import CommentSet from "@/components/articles/CommentSet";

import('@/assets/style/articles/ArticleDetail.css')

export default {
  name: "ArticleDetail",
  // components
  components: {
    ComponentNav,
    HeartList,
    CommentSet
  },
  // props
  // data
  data() {
    return {
      openButton: false,
      isHeartList: false,
    }
  },
  // methods
  methods: {
    // 수정/ 삭제 버튼
    onClick() {
      this.openButton = !this.openButton
    },
    // 날짜 수정
    dateFormatted(date) {
      return moment(String(date)).format('YYYY/MM/DD hh:mm')
    },
    // 좋아요
    onLike() {
      const data = {articleId: this.selectedArticle.id, userId: this.loginUser.id}
      ArticlesApi.requestLike(
          !this.selectedArticle.like,
          data,
          res => {
            if (res.data === 'OK') {
              data['isLike'] = this.selectedArticle.like
              this.$store.dispatch('setLike', data)
            } else this.$router.push('/404')
          },
          err => {
            this.$router.push('/error')
          }
      )
    },
    // 좋아요 목록 열기
    onHeartList() {
      this.isHeartList = !this.isHeartList
    },
  },
  // computed
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
      // likeList: state => state.articles.likeList,
      commentList: state => state.articles.commentList
    }),
    ...mapGetters(['selectedArticle', 'likeUser']),
    // 줄바꿈 설정
    parsedContent() {
      return this.selectedArticle.content.replace(/\n/g, '<br/>')
    },
    // 내 게시글인가?
    isMine() {
      return this.loginUser.nickname === this.selectedArticle.makerName
    }

  },
  // watch
  created() {
    const articleId = this.$route.params.id
    this.$store.dispatch('setSelectedId', articleId)
    // 좋아요 유저/ 댓글 불러오기
    const defaultSetting = async () => {
      const detailData = await ArticlesApi.requestArticleDetailInfo(articleId)
      this.$store.dispatch('setArticleDetail', detailData)
    }
    defaultSetting()
  }
  // lifecycle hook
}
</script>

<style scoped>
/*//.overlay {*/
/*//  position: fixed;*/
/*//  top: 60px;*/
/*//  right: 15px;*/
/*//  bottom: 0;*/
/*//  left: 15px;*/
/*//  overflow: scroll;*/
/*//  background: white;*/
/*//}*/

</style>