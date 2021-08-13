<template>
  <div id="upsert-article">

    <ComponentNav
        @on-arrow="$router.back()"
        title="퀘스트 인증"/>

    <div class="element-wrap">
      <div class="title">
        퀘스트
      </div>
      <div class="quest-name">
        <span v-if="quest.type === 'G'">공동</span>
        <span v-else-if="quest.type === 'P'">개인</span>
        <span v-else>릴레이</span>
        {{quest.title}}
      </div>
    </div>

    <div class="element-wrap">
      <div class="title">
        인증사진 등록
      </div>
      <div class="info">
        필수 등록값 입니다
      </div>
    </div>

    <div class="article-image">
      <img v-if="preview" :src="preview" alt="">
      <div v-else class="upload-info">
        유의사항<br/><br/>
        1. 등록한 이미지는 수정이 불가합니다.
        <br/>신중히 선택해 주세요.
        <br/><br/>
        2. 퀘스트 인증 당일에 촬영한 사진만 <br/>등록 가능합니다.
        <br/><br/>
        3. 1MB 이내의 .jpg/ .jpeg/ .png 파일만 <br/>등록 가능합니다.
      </div>
      <div id="image-error" v-html="error" v-if="error"></div>

    </div>
    <AwsImageUploader
        id="image-input" ref="aws"
        @preview="onPreview" @on-error="onError" @modified-date="dateCheck"/>

    <div class="element-wrap">
      <div class="title">
        내용
      </div>
      <div class="info">
        필수 등록값 입니다
      </div>
    </div>
    <textarea v-model="content" name="content" id="content" cols="28" rows="5"></textarea>

    <ButtonBig
        class="submit-button" value="작 성 완 료"
        @click.native="onClick" :class="{disabled: disabled}"
    />

  </div>
</template>

<script>
import ComponentNav from "@/components/common/ComponentNav";
import AwsImageUploader from "@/components/common/AwsImageUploader";
import ButtonBig from "@/components/common/ButtonBig";
import {mapGetters, mapState} from "vuex";
import ArticlesApi from "@/api/ArticlesApi";

import('@/assets/style/articles/UpsertArticle.css')

export default {
  name: "UpsertArticle",
  // components
  components: {
    ComponentNav,
    AwsImageUploader,
    ButtonBig
  },
  // props
  // data
  data() {
    return {
      // 수정인가?
      isUpdate:'',
      picture: '',
      preview: '',
      error: '',
      content: '',
      savedArticle: {},
    }
  },
  // methods
  methods: {
    onPreview(preview) {
      this.preview = preview
    },
    onError(error) {
      this.error = error
    },
    // 사진 촬영 날짜 확인
    dateCheck(date) {
      // 오늘 날짜
      const today = String(new Date()).split(' ', 4)
      // 업로드 된 사진의 최근 수정일
      const _date = String(date).split(' ', 4)
      // console.log(JSON.stringify(today) === JSON.stringify(_date))
      if (JSON.stringify(today) !== JSON.stringify(_date)) {
        this.error = '인증일 당일에 촬영한 사진만<br/>등록 가능합니다!'
      } else {
        this.error = ''
      }
    },
    // aws 요청 보내고 기다리기
    async onClick() {
      this.picture = await this.$refs.aws.uploadFile()

      this.onSubmit()
    },
    onSubmit () {
      const data = {
        userId: this.loginUser.id,
        questId: this.quest.id,
        image: this.picture,
        content: this.content,
        type: this.quest.type,
      }
      ArticlesApi.createArticle(
          data,
          res => {
            if (res.data !== 'NOT_FOUND') {
              this.savedArticle = res.data
              this.savedArticle['makerName'] = this.loginUser.nickname
              this.savedArticle.makerImage = this.loginUser.picture
              this.savedArticle.isLike = false
              this.savedArticle.heartCnt = 0
              this.savedArticle.commentCnt = 0
              console.log('아직 데이터 저장 중')
              console.log(this.savedArticle)
              this.$store.dispatch('setSelectedArticle', this.savedArticle)
            } else this.$router.push('/404')
          },
          err => this.$router.push('/error')
      )
    },
  },
  // computed
  computed: {
    ...mapState({
      quest: state => state.quests.questDetail,
      loginUser: state => state.user.loginUser
    }),
    // 버튼 비활성화
    disabled() {
      return !(this.preview && this.content && !this.error)
    },
    isArticleSelected() {
      return this.$store.getters.isArticleSelected
    }
  },
  // watch
  watch: {
    // watcher on computed
    isArticleSelected(v)  {
      // 게시글을 저장했기 때문에 피드로 보내주겠습니다.
      // 그 전에 먼저 피드에 내 새로운 게시물을 넣어주겠어요.
      const addArticle  = async () => {
        await this.$store.dispatch('addNewArticle', this.savedArticle)
      }
      addArticle().then(this.$router.push(`/article?id=${this.savedArticle.id}`))
      // this.$router.push(`/articles/${this.savedArticle.id}`)
    }

  },
  // lifecycle hook
  created() {
    // 혹시 있을지도 모르는 selectedArticle 값을 초기화
    if (this.isArticleSelected) {
      const article =
          {
            id: 0,
            createdAt: "",
            updatedAt: null,
            image: "",
            content: "",
            type: "",
            isLike: false,
            heartCnt: 0,
            commentCnt: 0,
            makerImage: null,
            makerName: "",
            questId: 0,
            questTitle: "",
          }
      this.$store.dispatch('setSelectedArticle', article)
    }
  },
  // navigation guard
  beforeRouteEnter: (to, from, next) => {
    if (from.name === 'QuestDetail')
      next()
    else
      next('/404')
  }
}
</script>

<style scoped>

</style>