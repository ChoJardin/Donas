<template>
  <div>
    <div id="single-article-main">
      <div id="single-article-wrap">

        <!--게시글 작성 정보-->
        <div id="single-article-profile">
          <router-link :to="`/user/profile/${article.makerName}`" id="single-article-profile-image">
            <img v-if="article.makerImage" :src="article.makerImage" alt="">
            <img v-else src="@/assets/donut_profile.png" alt="">
          </router-link>
          <div id="single-article-nickname-date">
            <router-link :to="`/user/profile/${article.makerName}`" id="single-article-profile-nickname">{{article.makerName}}</router-link>
            <!--<div id="single-article-date">{{article.createdAt}}</div>-->
            <div id="single-article-date">{{dateFormatted}}</div>
          </div>
        </div>
        <!--게시글 작성 정보-->

        <router-link :to="`/article/${article.id}`" style="text-decoration: none">
          <div id="single-article-picture">
            <!--인증 사진-->
            <img v-if="article.image" :src="article.image" alt="">
            <img v-else src="https://donas.s3.ap-northeast-2.amazonaws.com/donuts/donut_flag.png" alt="">
          </div>
<!--        <router-link :to="`/quest/${article.questId}`" id="single-article-quest">참여 퀘스트: {{article.questTitle}}</router-link>-->
        <a id="single-article-quest" @click.prevent=setQuestId(article.questId)>
          <span v-if="article.type === 'P'">개인</span>
          <span v-else-if="article.type === 'G'">공동</span>
          <span v-else >릴레이</span>
          {{article.questTitle}}
        </a>
          <div v-html="parsedDescription" id="single-article-article-content"/>

        </router-link>

          <!--좋아요/ 댓글-->
          <div id="single-article-article-detail">
            <!--<likeBtn :article="article"></likeBtn>-->
            <button @click="onLike" class="single-article-details">
              <i v-if="article.like" class="material-icons red">favorite</i>
              <i v-else class="material-icons">favorite_border</i>
              &nbsp;{{article.heartCnt}}
            </button>
            <router-link :to="`/article/${article.id}`" :key="`ca${article.id}`" class="single-article-details">
            <i class="material-icons-outlined">comment</i>
              &nbsp;<span>{{article.commentCnt}}</span>
            </router-link>
          </div>
          <!--좋아요/ 댓글-->
        <!--<router-link to="/main"></router-link>-->

      </div><!--single-article-wrap ends-->
    </div><!--single-article-main ends-->

  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import moment from "moment";
import ArticlesApi from "@/api/ArticlesApi";
// import likeBtn from "@/components/articles/likeBtn";

import ('@/assets/style/articles/SingleArticle.css')

export default {
  name: "SingleArticle",
  // components
  components: {
    // likeBtn,
  },
  // props
  props: {
    article: Object
  },
  // methods
  methods: {
    // 좋아요
    onLike() {
      const data = {articleId: this.article.id, userId: this.loginUser.id}
      ArticlesApi.requestLike(
          !this.article.like,
          data,
          res => {
            if (res.data === 'OK') {
              data['isLike'] = this.article.like
              this.$store.dispatch('setLike', data)
            } else this.$router.push('/404')
          },
          err => {
            console.log('here')
            this.$router.push('/error')
          }
      )

    },
    ...mapActions(["setQuestId"])
  },
  // computed
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser
    }),
    dateFormatted: function (){
      return moment(String(this.article.createdAt)).format('YYYY/MM/DD hh:mm')
    },
    parsedDescription() {
      return this.article.content.replace(/\n/g, '<br/>')
    }
  },
  // watch
  // lifecycle hook
  //navigation guard
}
</script>

<style scoped>

</style>