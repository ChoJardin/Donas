<template>
  <div>
    <CommentInput @on-click="onClick" ref="input"/>

    <CommentList v-for="comment in commentList" :key="comment.commentId" :comment="comment"/>

  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import CommentInput from "@/components/articles/CommentInput";
import CommentList from "@/components/articles/CommentList";
import ArticlesApi from "@/api/ArticlesApi";

export default {
  name: "CommentSet",
  components: {
    CommentInput,
    CommentList,
  },
  methods: {
    // 등록
    onClick(input) {
      const createComment = async(data) => {
        const result = await ArticlesApi.createComment(data)
        if (result === 'OK') {
          const commentList = await ArticlesApi.requestCommentList(data.articleId)
          await this.$store.dispatch('setCommentList', commentList)
          this.$refs.input.onReset()
        } else {
          this.$router.push('/404')
        }
      }
      const data = {
        articleId: this.articleId,
        content: input,
        userId: this.loginUser.id
      }
      createComment(data)
    },
  },
  computed: {
    ...mapState({
      commentList: state => state.articles.commentList,
      loginUser: state => state.user.loginUser,
      articleId: state => state.articles.selectedId
    }),
    ...mapGetters(['isLoggedIn'])
  },
}
</script>

<style scoped>

</style>