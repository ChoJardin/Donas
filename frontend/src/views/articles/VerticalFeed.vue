<template>
  <div>
    <h1>VerticalFeed</h1>

    <SingleArticle
        v-for="article in articles" :key="article.id"
        :id="`a${article.id}`"
        :article="article"/>
    <!--<infinite-loading @infinite="infiniteHandler" spinner="circles"/>-->

  </div>
</template>

<script>
import {mapState} from "vuex";
// import InfiniteLoading from 'vue-infinite-loading'

import SingleArticle from "@/components/articles/SingleArticle";

export default {
  name: "VerticalFeed",
  components: {
    // InfiniteLoading,
    SingleArticle,
  },
  data() {
    return {

    }
  },
  computed: {
    ...mapState({
      articles: state => state.articles.feeds
    })
  },
  watch: {
    '$route.query.id'(v) {
      console.log(v)
    }
  },
  mounted() {
    this.$nextTick(function () {
      // 누른 위치로 스크롤
      let location = document.querySelector(`#a${this.$route.query.id}`).offsetTop
      window.scrollTo({top:location})
    })
  }

}
</script>

<style scoped>

</style>