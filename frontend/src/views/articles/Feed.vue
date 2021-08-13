<template>
  <div>
    <!--article start-->
    <div id="feed-article-wrap">
      <div class="feed-article-image" v-for="article in articles" :key="article.id">
        <ArticleImage class="inner" :article="article"/>
      </div>
    </div>
    <!--article end-->


  </div>
</template>

<script>

import ArticleImage from "@/components/articles/ArticleImage";
import {mapGetters, mapState} from "vuex";

export default {
  name: 'Feed',
  components: {
    ArticleImage
  },
  computed: {
    ...mapState({
      articles: store => store.articles.feeds
    }),
    ...mapGetters(['isLoggedIn'])
  },
  created() {
    if (!this.isLoggedIn) {
      this.$router.push({name: 'Login', params: {history: this.$route.fullPath}})
    }
  },
}
</script>

<style scoped>
#feed-article-wrap {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}

.feed-article-image {
  /*border: 1px solid olivedrab;*/
  width: 33.3333%;
  position: relative;
}

.feed-article-image:after {
  content: "";
  display: block;
  padding-bottom: 100%;
}

.feed-article-image .inner {
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