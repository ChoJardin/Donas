<template>
  <div>
    <div id="article-detail">

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

      <!--article-->
      <router-link :to="`/user/profile/${selectedArticle.makerName}`" class="profile-router">
        <div class="profile-wrap">
          <img v-if="selectedArticle.makerImage" :src="selectedArticle.makerImage" alt="" class="article-detail-profile-image">
          <img v-else src="@/assets/donut_profile.png" alt="" class="article-detail-profile-image">
          <div class="info-wrap">
            <div class="nickname">
              여기 이름 자리
              {{selectedArticle.makerName}}
            </div>  <!--nickname end -->
            <div class="article-date">
              <!--<p>{{dateFormatted(selectedArticle.createdAt)}}</p>-->
              <p>{{dateFormatted(selectedArticle.updatedAt)}} <span>수정</span></p>
            <!--<span v-if="selectedArticle.updatedAt">{{dateFormatted(selectedArticle.updatedAt)}}</span>-->
            </div>  <!--article-date end-->
          </div>  <!--info-wrap end-->
        </div>
      </router-link>

      <div class="main-image">
      <!--<img :src="selectedArticle.image" alt="">-->
        <img src="@/assets/donut_flag.png" alt="">
      </div>

      <!--퀘스트-->
      <router-link :to="`/quest/${selectedArticle.questId}`" class="quest-router">
        <span v-if="selectedArticle.type==='G'" class="quest-type">공동</span>
        <span v-else-if="selectedArticle.type==='P'" class="quest-type">개인</span>
        <span v-else class="quest-type">릴레이</span>
        <span class="quest-title">퀘스트 이름이 옵니다{{selectedArticle.questTitle}}</span>
      </router-link>

      <textarea name="content" id="article-content" cols="30" rows="3"
                    v-model="selectedArticle.content" readonly/>

      <!--좋아요/ 댓글-->
      <div class="heart-comment">
        <span>.. 님이 이 게시글을 좋아합니다.</span>
        <i v-if="selectedArticle.isLike" class="material-icons red">favorite</i>
        <i class="material-icons">favorite_border</i>
        &nbsp;{{selectedArticle.heartCnt}}
        <i class="material-icons-outlined">comment</i>
        &nbsp;<span>{{selectedArticle.commentCnt}}</span>
      </div>




      <!--article-->
      <br>
      {{selectedArticle}}






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
      // console.log(this.article)
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

  },
  // watch
  created() {
    this.$store.dispatch('setSelectedId', this.$route.params.id)
  }
  // lifecycle hook
}
</script>

<style lang="scss">
.overlay {
  position: fixed;
  top: 60px;
  right: 15px;
  bottom: 0;
  left: 15px;
  overflow: scroll;
  background: white;
}

</style>