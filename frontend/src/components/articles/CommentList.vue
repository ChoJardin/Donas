<template>
  <div id="follow-user-profile">

    <router-link :to="`/user/profile/${comment.nickname}`" id="follow-profile-wrap">
      <img v-if="comment.picture" class="profile-image card" :src="comment.picture" alt="">
      <img v-else class="profile-image card" src="@/assets/donut_profile.png" alt="">
    </router-link>
    <div id="comment-text-wrap">
      <div class="comment-text-line">
        <div id="comment-nickname">{{comment.nickname}}</div>
        <button v-if="isMine" @click="showModal = !showModal" class="material-icons-round">more_horiz</button>
        <!--<button @click="onEdit" v-if="isMine">-->
        <button v-if="isEdit" class="cancel-edit" @click="isEdit = false">취소</button>
        <!--  <span v-else>수정</span>-->
        <!--</button>-->

      <Modal v-if="showModal" @close="showModal = !showModal">
        <button slot="opt1" @click="onEdit">수정하기</button>
        <button slot="opt2" @click="onDelete">삭제하기</button>
        <button slot="opt3" @click="showModal = false">닫기</button>
      </Modal>


      </div>
      <div class="comment-text-line">
        <div id="comment-time">{{dateFormatted}}</div>
        <!--<button @click="onDelete" v-if="isMine">삭제</button>-->
      </div>
      <CommentInput v-if="isEdit" :input.sync="content" :content="content" @on-click="onSubmit" @on-enter="onSubmit"/>
      <div v-else id="comment-content">{{comment.content}}</div>
    </div>




  </div>
</template>

<script>
import {mapState} from "vuex";
import CommentInput from "@/components/articles/CommentInput";
import ArticlesApi from "@/api/ArticlesApi";
import Modal from "@/components/common/Modal";
import moment from "moment";

export default {
  name: "CommentList",
  components: {
    CommentInput,
    Modal
  },
  props: {
    comment: Object
  },
  data() {
    return {
      showModal: false,
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
      this.showModal = !this.showModal
    },
    onDelete() {
      const deleteComment = async(data) => {
        const result = await ArticlesApi.deleteComment(data)
        if (result === 'OK') {
          const commentList = await ArticlesApi.requestCommentList(this.articleId)
          await this.$store.dispatch('setCommentList', commentList)
          this.$mount()
          this.$store.dispatch('resetCommentCnt', {isAdd: false, articleId: this.articleId})
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
    isMine() {
      return this.loginUser.id === this.comment.userId
    },
    // 날짜 수정
    dateFormatted() {
      return moment(String(this.comment.updatedAt)).format('YYYY/MM/DD HH:mm')
    },
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

.cancel-edit {
  position: absolute;
  right: 15px;
  /*top: 15px;*/
  margin-top: 5px;
  color: #cd4e3e;
  border: 1px solid #cd4e3e;
  background-color: #fefbe9;
  padding: 3px;
  border-radius: 8px;
  z-index: 2;
}
</style>
