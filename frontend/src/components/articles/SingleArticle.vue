<template>
  <div>
    <div id="single-article-main">
      <div id="single-article-wrap">

        <!--게시글 작성 정보-->
        <div id="single-article-profile">
          <router-link :to="`프로필 페이지`" id="single-article-profile-image">프로필 사진</router-link>
          <div id="single-article-nickname-date">
            <router-link :to="`프로필 페이지`" id="single-article-profile-nickname">닉네임</router-link>
            <div id="single-article-date">작성일시</div>
          </div>
        </div>
        <!--게시글 작성 정보-->

        <router-link :to="`퀘스트 detail로 이동`" id="single-article-quest">퀘스트</router-link>

        <router-link :to="`/article/${article.id}`">
          <div id="single-article-picture">인증 사진</div>
          <div id="single-article-article-content">작성 내용</div>
        </router-link>

          <!--좋아요/ 댓글-->
          <div id="single-article-article-detail">
            <div class="single-article-details">
              <i class="material-icons">favorite_border</i>
              좋아요
            </div>
            <router-link :to="`/article/${article.id}`" :key="`ca${article.id}`" class="single-article-details">
            <i class="material-icons-outlined">comment</i>
              댓글
            </router-link>
          </div>
          <!--좋아요/ 댓글-->
        <router-link to="/main"></router-link>

      <!--<router-view></router-view>-->
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