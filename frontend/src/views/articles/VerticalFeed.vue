<template>
  <div id="vertical-feed-wrap">
    <!--<h1>VerticalFeed</h1>-->

      <SingleArticle
          v-for="article in articles" :key="article.articleId"
          :id="`a${article.articleId}`"
          :article="article"/>

    <transition-page>
      <router-view>
      </router-view>
    </transition-page>


  </div>
</template>

<script>
import {mapState} from "vuex";

import SingleArticle from "@/components/articles/SingleArticle";
import TransitionPage from "@/components/common/TransitionPage";

export default {
  name: "VerticalFeed",
  components: {
    // InfiniteLoading,
    SingleArticle,
    TransitionPage,
  },
  computed: {
    ...mapState({
      articles: state => state.articles.feeds
    })
  },
  mounted() {
    this.$nextTick(function () {
      // 누른 위치로 스크롤
      document.getElementById(`a${this.$route.query.id}`).scrollIntoView();
    })
  }

}
</script>

<style scoped>
#vertical-feed-wrap {
  display: flex;
  flex-direction: column;
  /*height: 100%;*/
  /*align-items: center;*/
}
</style>