<template>
  <div>
    <div id="single-article-main">
      <div id="single-article-wrap">

        <!--게시글 작성 정보-->
        <div id="single-article-profile">
          <router-link :to="{name: 'Profile'}" id="single-article-profile-image">프로필 사진</router-link>
          <div id="single-article-nickname-date">
            <router-link :to="`프로필 페이지`" id="single-article-profile-nickname">닉네임</router-link>
            <div id="single-article-date">{{article.createdAt}}</div>
          </div>
        </div>
        <!--게시글 작성 정보-->

        <router-link :to="`/quest/${article.questId}`" id="single-article-quest">퀘스트</router-link>

        <router-link :to="`/article/${article.id}`">
          <div id="single-article-picture">
            인증 사진
            <img :src="article.image" alt="">
          </div>
          <div id="single-article-article-content">{{article.content}}</div>
        </router-link>

          <!--좋아요/ 댓글-->
          <div id="single-article-article-detail">
            <div class="single-article-details">
              <i class="material-icons">favorite_border</i>
              {{article.likeCnt}}
            </div>
            <router-link :to="`/article/${article.id}`" :key="`ca${article.id}`" class="single-article-details">
            <i class="material-icons-outlined">comment</i>
              {{article.commentCnt}}
            </router-link>
          </div>
          <!--좋아요/ 댓글-->
        <router-link to="/main"></router-link>

      </div><!--single-article-wrap ends-->
    </div><!--single-article-main ends-->

  </div>
</template>

<script>
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
  // watch
  // lifecycle hook
}
</script>

<style scoped>

</style>