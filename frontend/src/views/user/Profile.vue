<template>
  <div>
    <h1>{{this.$route.params.nickname}}의 프로필</h1>
    <div>
      <img class="picture" src="@/assets/도넛1.png" alt="">
    </div>
    

    <div v-if="nickname === this.$route.params.nickname">
      내 프로필

      <button>
        정보수정
      </button>

    </div>
    <div v-else>
      남의 프로필
    </div>

    <ArticleImage v-for="n in 21" :key="n"/>



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
.picture {
  background-color: #2c3e50;
}

</style>