<template>
  <div class="article-detail">
    <!--nav-->
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
    <!--nav-->

    {{selectedArticle}}
    <br>
    <!--article-->
    <router-link :to="`/user/profile/${selectedArticle.makerName}`" >
      <img v-if="selectedArticle.makerImage" :src="selectedArticle.makerImage" alt="" class="article-detail-profile-image">
      <img v-else src="@/assets/donut_profile.png" alt="" class="article-detail-profile-image">
      {{selectedArticle.makerName}}
    </router-link>
    <!--article-->
    <div id="single-article-date">
      {{dateFormatted(selectedArticle.createdAt)}}
      <!--<span v-if="selectedArticle.updatedAt">{{dateFormatted(selectedArticle.updatedAt)}}</span>-->
    </div>







  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import ComponentNav from "../common/ComponentNav";
import moment from "moment";

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
      console.log(this.article)
      this.openButton = !this.openButton
    },
    dateFormatted(date) {
      return moment(String(date)).format('YYYY/MM/DD hh:mm')
    }
  },
  // computed
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser
    }),
    ...mapGetters(['selectedArticle']),
    // dateFormatted(date) {
    //   return moment(String(date)).format('YYYY/MM/DD hh:mm')
    // }
  },
  // watch
  created() {
    this.$store.dispatch('setSelectedId', this.$route.params.id)
  }
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