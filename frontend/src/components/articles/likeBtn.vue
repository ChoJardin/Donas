<template>
  <div>
    <button @click="onLike" class="heart-cnt">
      <i v-if="article.like" class="material-icons red">favorite</i>
      <i v-else class="material-icons">favorite_border</i>
      &nbsp;{{article.heartCnt}}
      {{article}}
    </button>
  </div>
</template>

<script>
import ArticlesApi from "@/api/ArticlesApi";
import {mapState} from "vuex";

export default {
  name: "likeBtn",
  props: {
    article: Object
  },
  methods: {
    // 좋아요
    onLike() {
      console.log(this.article)
      console.log(this.loginUser)
      const data = {articleId: this.article.id, userId: this.loginUser.id}
      console.log(data)
      ArticlesApi.requestLike(
          !this.article.like,
          data,
          res => {
            console.log(res)
            console.log(res.status)
            if (res.data === 'OK') {
              data['isLike'] = this.selectedArticle.like
              this.$store.dispatch('setLike', data)
            } else this.$router.push('/404')
          },
          err => {
            console.log(err)
            // this.$router.push('/error')
          }
      )
    },
  },
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser
    })
  }
}
</script>

<style scoped>

</style>