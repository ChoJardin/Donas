<template>
  <div>
    <ComponentNav title="친구 게시글 보기" @on-arrow="$router.back()"></ComponentNav>

    <!--article start-->
    <div id="feed-article-wrap">
      <div v-if="!articles.length" class="info">
        아직 팔로우하는 친구가 없거나, <br/>
        친구가 게시글을 작성하지 않았어요!
      </div>
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
import ArticlesApi from "@/api/ArticlesApi";
import ComponentNav from "@/components/common/ComponentNav";

export default {
  name: 'Feed',
  components: {
    ArticleImage,
    ComponentNav
  },
  computed: {
    ...mapState({
      articles: store => store.articles.feeds
    }),
    ...mapGetters(['isLoggedIn'])
  },
  created() {
    // 로그인하지 않은 경우
    if (!this.isLoggedIn) {
      // this.$router.push({name: 'Login', params: {history: this.$route.fullPath}})
      this.$router.replace('/login')
    } else {
      // 로그인 했으면 내가 팔로우 하고 있는 사람 게시글 보여주기
      const data = {
        userId: this.$store.state.user.loginUser.id,
        type: 'A'
      }
      ArticlesApi.requestFeed(
          data,
          res => {
            console.log(res)
            if (res.data !== 'NOT_FOUND')
              this.$store.dispatch('setFeeds', res.data)
            else this.$router.push('/404')
          },
          err => this.$router.push('/error')
      )
    }
  },
}
</script>

<style scoped>
#feed-article-wrap {
  margin-top: 60px;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}

#feed-article-wrap .info {
  margin: auto;
  font-family: GongGothicLight;
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