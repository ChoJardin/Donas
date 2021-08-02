<template>
  <div>
    <div id="single-article-main">
      <div id="single-article-wrap">

        <!--게시글 작성 정보-->
        <div id="single-article-profile">
          <div id="single-article-profile-image">프로필 사진</div>
          <div id="single-article-nickname-date">
            <div id="single-article-profile-nickname">닉네임</div>
            <div id="single-article-date">작성일시</div>
          </div>
        </div>
        <!--게시글 작성 정보-->

        <router-link :to="`퀘스트 detail로 이동`" id="single-article-quest">퀘스트</router-link>

        <div id="single-article-picture">인증 사진</div>

        <div id="single-article-article-content">작성 내용</div>

        <!--좋아요/ 댓글-->
        <div id="single-article-article-detail">
          <div class="single-article-details">
            <i class="material-icons">favorite_border</i>
            좋아요
          </div>
          <div class="single-article-details">
          <i class="material-icons-outlined">comment</i>
            댓글
          </div>
        </div>
        <!--좋아요/ 댓글-->

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