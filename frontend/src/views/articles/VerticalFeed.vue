<template>
  <div id="vertical-feed-wrap">
    <!--<h1>VerticalFeed</h1>-->

      <SingleArticle
          v-for="article in articles" :key="article.id"
          :id="`a${article.id}`"
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
  // watch: {
    // '$route.query.id'(v) {
    //   console.log(v)
    // }
  // },
  mounted() {
    this.$nextTick(function () {
      // 누른 위치로 스크롤
      let nav = document.querySelector('#nav').offsetHeight
      let location = document.querySelector(`#a${this.$route.query.id}`).offsetTop
      // let location = document.querySelector(`#a${this.$route.params.id}`).offsetTop
      window.scrollTo({top:location - nav})
    })
  }

}
</script>

<style scoped>
#vertical-feed-wrap {
  display: flex;
  flex-direction: column;
  /*align-items: center;*/
}
</style>