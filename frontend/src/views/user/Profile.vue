<template>
  <div>
    <div id="flex-container">
      <div id="profile-wrap">
        <img id="profile-image" src="@/assets/도넛1.png" alt="">
        <div id="profile-info">
          <h1 id="username">{{this.$route.params.nickname}}</h1>
          <div id="badges">뱃지</div>
        </div>

        <div v-if="nickname === this.$route.params.nickname">
          <button>
            정보수정
          </button>
        </div>
      </div>

      <div id="description">
        소개글
      </div>

      <div id="on-going-quests">
        진행 중 퀘스트
      </div>

    </div>
    <!--profile end-->

    <!--article start-->
    <div id="article-wrap">
      <div class="article-image" v-for="article in articles" :key="article.id">
        <ArticleImage class="inner" :article="article"/>
      </div>
    </div>
    <!--article end-->

  </div>
</template>

<script>
import {mapState} from "vuex";

import ArticleImage from "@/components/articles/ArticleImage";

export default {
  name: "Profile",
  // components
  components: {
    ArticleImage
  },
  // props
  // data
  // methods
  // computed
  computed: {
    ...mapState({
        nickname: state => state.user.loginUser.nickname,
        articles: state => state.articles.feeds
    })
  },
  // watch
  // lifecycle hook
}
</script>

<style scoped>
#flex-container {
  display: flex;
  flex-direction: column;
  text-align: start;
  margin: 15px;
}

#profile-wrap {
  display: flex;
}

#profile-image {
  width: 100px;
  flex: 1 1 0;
}

#profile-info {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  flex: 2 2 0;
}

#description {
  height: 50px;
  display: flex;
  align-items: center;
}

#on-going-quests {
  height: 50px;
  display: flex;
  align-items: center;
}

#username {

}

#badges {

}

#article-wrap {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}

.article-image {
  border: 1px solid olivedrab;
  width: 33.3333%;
  position: relative;
}

.article-image:after {
  content: "";
  display: block;
  padding-bottom: 100%;
}

.article-image .inner {
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