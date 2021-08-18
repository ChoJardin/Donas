<template>
  <div id="vertical-feed-wrap">

    <ComponentNav @on-arrow="$router.back()" title="친구 게시글 보기"></ComponentNav>

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
import ComponentNav from "@/components/common/ComponentNav";

export default {
  name: "VerticalFeed",
  components: {
    SingleArticle,
    TransitionPage,
    ComponentNav
  },
  data() {
    return {
      articles: [
          {
            id: 0,
            createdAt: "",
            updatedAt: null,
            image: "",
            content: "",
            type: "",
            like: false,
            heartCnt: 0,
            commentCnt: 0,
            makerImage: null,
            makerName: "",
            questId: 0,
            questTitle: "",
          },
      ],
    }
  },
  created() {
    this.articles = this.$store.state.articles.feeds
  },
  mounted() {
    this.$nextTick(function () {
      // 누른 위치로 스크롤
      document.getElementById(`a${this.$route.query.id}`).scrollIntoView();
    })
  },
  // navigation guard
  beforeRouteEnter(to, from, next) {
    if (from.name === 'UpsertArticle') {
      console.log('여기는 왔냐?')
      next(vm => vm.$router.push(`/article/${vm.$route.query.id}`))
    } else next()
  }
}
</script>

<style scoped>
#vertical-feed-wrap {
  display: flex;
  flex-direction: column;
  margin-top: 50px;
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