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
      <ArticleImage class="article-image" v-for="n in 21" :key="n"/>
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
        nickname: state => state.user.userNickname,
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
  margin: 10px;
  display: flex;
  flex-wrap: wrap;
}

#article-wrap .article-image {
  width: 33%;
}

</style>