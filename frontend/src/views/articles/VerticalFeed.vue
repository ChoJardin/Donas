<template>
  <div id="vertical-feed-wrap">
    <!--<h1>VerticalFeed</h1>-->

      <SingleArticle
          v-for="article in articles" :key="article.id"
          :id="`a${article.id}`"
          :article="article"/>

    <TransitionPage>
      <router-view>
      </router-view>
    </TransitionPage>


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
}

.slide-right-enter {
  opacity:0;
  transform: translateX(100%);
}

.slide-right-enter-active {
  transition: all .5s ease;
}

.slide-right-leave-active {
  transition: all .5s ease;
}

.slide-right-enter-from,
.slide-right-leave-to  {
  opacity: 0;
  transform: translateX(100%);
}

</style>