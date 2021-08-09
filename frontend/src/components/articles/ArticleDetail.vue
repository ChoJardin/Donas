<template>
  <div class="article-detail-overlay">
    <component-nav @on-arrow="$router.back()" title="인증 게시글"/>

    <button @click="onClick" class="material-icons-round more">more_horiz</button>
    <div v-if="openButton">
      <button>
        수정
      </button>
      <button>
        삭제
      </button>
    </div>



  </div>
</template>

<script>
import {mapState} from "vuex";
import ComponentNav from "../common/ComponentNav";

import('@/assets/style/articles/ArticleDetail.css')

export default {
  name: "ArticleDetail",
  // components
  components: {
    ComponentNav
  },
  // props
  props: {
    article: Object
  },
  // data
  data() {
    return {
      openButton: false
    }
  },
  // methods
  methods: {
    onClick() {
      this.openButton = !this.openButton
    }
  },
  // computed
  ...mapState({
    loginUser: state => state.user.loginUser

  })
  // watch
  // lifecycle hook
}
</script>

<style scoped>
/*.article-detail-overlay {*/
/*  position: fixed;*/
/*  top: 60px;*/
/*  right: 15px;*/
/*  bottom: 0;*/
/*  left: 15px;*/
/*  overflow: scroll;*/
/*  background: white;*/
/*}*/


</style>