<template>
  <div id="follow-user-profile">

    <router-link :to="`/user/profile/${comment.nickname}`" id="follow-profile-wrap">
      <img class="profile-image card" src="@/assets/profile_test.jpeg" alt="">
    </router-link>
    <div id="comment-text-wrap">
      <div class="comment-text-line">
        <div id="comment-nickname">{{comment.nickname}}</div>
        <button @click="onEdit" v-if="ismine">
          <span v-if="isEdit">취소</span>
          <span v-else>수정</span>
        </button>
      </div>
      <div class="comment-text-line">
        <div id="comment-time">{{comment.updatedAt}}</div>
        <button @click="onDelete" v-if="ismine">삭제</button>
      </div>
      <CommentInput v-if="isEdit" :input.sync="content" :content="content" @on-click="onSubmit"/>
      <div v-else id="comment-content">{{comment.content}}</div>
    </div>




  </div>
</template>

<script>
import {mapState} from "vuex";
import CommentInput from "@/components/articles/CommentInput";
import ArticlesApi from "@/api/ArticlesApi";

export default {
  name: "CommentList",
  components: {
    CommentInput
  },
  props: {
    comment: Object
  },
  data() {
    return {
      isEdit: false,
      content: '',
    }
  },
  methods:{
    // 수정 댓글 등록
    onSubmit() {
      const editComment = async(data) => {
        const result = await ArticlesApi.updateComment(data)
        if (result === 'OK') {
          const commentList = await ArticlesApi.requestCommentList(this.articleId)
          await this.$store.dispatch('setCommentList', commentList)
          this.isEdit = false
        } else {
          this.$router.push('/404')
        }
      }
      const data = {
        commentId: this.comment.commentId,
        content: this.content
      }
      editComment(data)
    },
    onEdit() {
      this.content = this.comment.content
      this.isEdit = !this.isEdit
    },
    onDelete() {
      const deleteComment = async(data) => {
        const result = await ArticlesApi.deleteComment(data)
        if (result === 'OK') {
          const commentList = await ArticlesApi.requestCommentList(this.articleId)
          await this.$store.dispatch('setCommentList', commentList)
          this.$mount()
        } else {
          this.$router.push('/404')
        }
      }
      const data = {
        commentId: this.comment.commentId
      }
      deleteComment(data)
    }
  },
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
      articleId: state => state.articles.selectedArticle.id
    }),
    ismine() {
      return this.loginUser.id === this.comment.userId
    }
  }
}
</script>

<style scoped>
#follow-user-profile {
  padding: 5px;
  margin-bottom: 5px;
  border-bottom: 1px solid #e4e6e9;
  display: flex;
}

.profile-image.card {
  width: 40px;
  height: 40px;
}

#comment-text-wrap {
  width: 100%;
  margin-left: 15px;
}

.comment-text-line {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
  font-family: GongGothicLight;
  font-size: 0.6em;
}

.comment-text-line #comment-nickname {
  align-self: center;
  font-size: 0.9em ;
  font-family: GongGothicMedium;
  color: #292929;
}

#comment-content {
  font-size: 0.8em;
  text-align: start;
  font-family: GongGothicLight;
}
</style>
