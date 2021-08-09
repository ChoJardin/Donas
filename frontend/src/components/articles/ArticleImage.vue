<template>
  <!--<router-link :to="{path: `/feed/v/${article.id}`}">-->
  <router-link :to="{path: '/article', query: {id: article.articleId}}">
    <div id="article-image-wrap">
      <img id="article-image-image" :src="article.image" alt="">
    </div>
  </router-link>
</template>

<script>

export default {
  name: "ArticleImage",
  // components
  // props
  props: {
    article: Object
  },
  // data
  // methods
  // computed
  // watch
  // lifecycle hook
}
</script>

<style scoped>
#article-image-wrap {
  height: 100%;
  display: flex;
  align-items: center;
}

#article-image-image {
  position: absolute;
  width: 100%;
  height: auto;
}

</style>