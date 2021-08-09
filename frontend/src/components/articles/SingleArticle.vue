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


        <router-link :to="`/article/${article.articleId}`">
          <div id="single-article-picture">
            <!--인증 사진-->
            <!--<img :src="article.image" alt="">-->
            <img src="@/assets/IMG_7263.jpeg" alt="">
          </div>
        <router-link :to="`/quest/${article.questId}`" id="single-article-quest">참여 퀘스트: {{article.questTitle}}</router-link>
          <textarea name="content" id="single-article-article-content" cols="30" rows="2"
                    v-model="article.content" readonly/>
          <!--<div id="single-article-article-content">{{article.content}}</div>-->
        </router-link>

          <!--좋아요/ 댓글-->
          <div id="single-article-article-detail">
            <div class="single-article-details">
              <i class="material-icons">favorite_border</i>
              &nbsp;{{article.likeCnt}}
            </div>
            <router-link :to="`/article/${article.articleId}`" :key="`ca${article.articleId}`" class="single-article-details">
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
import moment from "moment";

import ('@/assets/style/articles/SingleArticle.css')

export default {
  name: "SingleArticle",
  // components
  // props
  props: {
    article: Object
  },
  // methods
  // computed
  computed: {
    dateFormatted: function (){
      return moment(String(this.article.createdAt)).format('YYYY/MM/DD hh:mm')
    }
  }
  // watch
  // lifecycle hook
}
</script>

<style scoped>

</style>