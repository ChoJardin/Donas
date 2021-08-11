<template>
  <div>
    <div class="comment-input-button-wrap">
    <input
        class="comment-input" v-model="input" @input="onType($event)"
        type="text" :placeholder="placeholder" @keyup.enter="$emit('on-click', input)">
    <button @click="$emit('on-click', input)" class="comment-input-button" :disabled="!input || !isLoggedIn">등록</button>
    </div>
    <div class="comment-input-info">
      <span>최대 xx자 까지 입력 가능합니다.</span>
      <span>{{}}/xx</span>
    </div>
  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import ArticlesApi from "@/api/ArticlesApi";

export default {
  name: "CommentInput",
  props: {
    content: {
      type: String,
      default: '',
    }
  },
  data() {
    return {
      input: '',
    }
  },
  methods: {
    // onClick() {
    //   const createComment = async(data) => {
    //     const result = await ArticlesApi.createComment(data)
    //     if (result === 'OK') {
    //       const commentList = await ArticlesApi.requestCommentList(data.articleId)
    //       this.$store.dispatch('setCommentList', commentList)
    //     } else {
    //       this.$router.push('/404')
    //     }
    //   }
    //   const data = {
    //     articleId: this.articleId,
    //     content: this.input,
    //     userId: this.loginUser.id
    //   }
    //   createComment(data)
    // },
    onType() {
      this.$emit('update:input', this.input)
    },
    onReset() {
      this.input = ''
    }
  },
  computed: {
    ...mapState({
      articleId: state => state.articles.selectedId,
      loginUser: state => state.user.loginUser
    }),
    ...mapGetters(['isLoggedIn']),
    placeholder() {
      if (this.isLoggedIn) {
        return "댓글로 친구에게 힘이 되어 주세요!"
      } else {
        return "댓글을 등록하기 위해서는 로그인이 필요합니다."
      }
    }
  },
  created() {
    this.input = this.content
  }
}
</script>

<style scoped>
.comment-input-button-wrap {
  position: relative;
  height: 25px;
}

.comment-input {
  border: 1px solid #292929;
  width: 100%;
  height: 100%;
  padding: 0 5px;
  font-family: GongGothicLight;
  font-size: 0.8em;
  position: relative;
  border-radius: 10px;
  /*box-shadow: ;*/
}

.comment-input-button {
  border: 1px solid #292929;
  position: absolute;
  right: 0;
  height: 100%;
  padding: 0 5px;
  font-size: 0.8em;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: #e1eedd;
  color: #183a1d;
}

.comment-input-button:disabled {
  opacity: 0.5;
}

.comment-input-info {
  display: flex;
  justify-content: space-between;
  margin: 3px 6px;
  font-size: 0.7em;
  font-family: GongGothicLight;
  color: #a0a0a0;

}
</style>
