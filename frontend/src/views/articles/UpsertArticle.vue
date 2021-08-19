<template>
  <div id="upsert-article">

    <ComponentNav
        @on-arrow="$router.back()"
        title="퀘스트 인증"/>

    <div id="upsert-article-content">

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
      <img v-if="isUpdate" :src="selectedArticle.image" alt="">
      <img v-else-if="preview" :src="preview" alt="">
      <div v-else class="upload-info">
        유의사항<br/><br/>
        1. 등록한 이미지는 수정이 불가합니다.
        <br/>신중히 선택해 주세요.
        <br/><br/>
        2. 퀘스트 인증 당일에 촬영한 사진만 <br/>등록 가능합니다.
        <br/><br/>
        3. 5MB 이내의 .jpg/ .jpeg/ .png 파일만 <br/>등록 가능합니다.
      </div>
      <div id="image-error" v-html="error" v-if="error"></div>

    </div>
    <AwsImageUploader
        v-show="!isUpdate" id="image-input" ref="aws"
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


    <div v-if="quest.type === 'R' && quest.targetCnt !== quest.nowCnt && !isUpdate">

      <div class="element-wrap">
        <div class="title">
          다음 주자 지목
        </div>
        <div class="info">
          필수 등록값 입니다
        </div>
      </div>


      <span v-if="!nextError && participant" class="name-tag inside-input">{{participant.nickname}}</span>
      <input v-if="nextError" @click="isModal = true" class="create-quest-input select-user" type="text" placeholder="유효한 주자를 선택해주세요" readonly>
      <input v-else-if="participant" type="text"
             @click="isModal = true" class="create-quest-input select-user"
             placeholder="">
      <input v-else-if="!participant" @click="isModal = true" class="create-quest-input select-user" type="text" placeholder="다음 주자를 선택해주세요" readonly>


      <MidModal v-if="isModal" @close="isModal = false">
        <div slot="header" style="width: 100%; text-align: center">다음 주자 지목</div>
        <SelectNextParticipant slot="opt1"
                               :user.sync="participant" @error="onNextError"
                               @on-friend-select="onFriendSelect" ref="friends"></SelectNextParticipant>
      </MidModal>
    </div>

    <ButtonBig
        class="submit-button" value="작 성 완 료"
        @click.native="onClick" :class="{disabled: disabled}"
    />
    </div>







  </div>
</template>

<script>
import {mapActions, mapGetters, mapState} from "vuex";
import ArticlesApi from "@/api/ArticlesApi";

import ComponentNav from "@/components/common/ComponentNav";
import AwsImageUploader from "@/components/common/AwsImageUploader";
import ButtonBig from "@/components/common/ButtonBig";
import MidModal from "@/components/common/MidModal";
import SelectNextParticipant from "@/components/quests/SelectNextParticipant";
import UserApi from "@/api/UserApi";
import QuestApi from "@/api/QuestApi";

import('@/assets/style/articles/UpsertArticle.css')

export default {
  name: "UpsertArticle",
  // components
  components: {
    ComponentNav,
    AwsImageUploader,
    ButtonBig,
    MidModal,
    SelectNextParticipant

  },
  // props
  // data
  data() {
    return {
      // 수정인가?
      isUpdate: false,
      // 저장완료?
      isSaved: false,
      picture: '',
      preview: '',
      error: '',
      content: '',
      savedArticle: {},
      // 버튼 눌렸을 경우
      isSubmit: false,
      // 다음 참가자 선택
      isModal: false,
      users: [],
      participant: '',
      nextError: ''
    }
  },
  // methods
  methods: {
    onNextError(isError) {
      this.nextError = isError
    },

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
    // 버튼 눌렀을 때,
    onClick() {
      this.isSubmit = true
      // 수정하는 경우
      if (this.isUpdate) {
        this.onEdit()
      }
      else
        // 생성
        this.onCreate()
    },
    // 새로 글 생성하는 경우
    // aws 요청 보내고 기다리기
    async onCreate() {
      this.picture = await this.$refs.aws.uploadFile()
      this.onSubmit()
    },
    // 서버로 요청 보내기
    async onSubmit () {
      const data = {
        userId: this.loginUser.id,
        questId: this.quest.id,
        image: this.picture,
        content: this.content,
        type: this.quest.type,
      }
      await ArticlesApi.createArticle(
          data,
          async res => {
            if (res.data !== 'NOT_FOUND') {
              // 릴레이인 경우 다음주자 선정
              if (this.quest.type === 'R') {
                const data = {
                  questId: this.quest.id,
                  userId: this.loginUser.id,
                  nextId: this.participant.id
                }
                await QuestApi.requestNextRelay(
                    data,
                    res => {
                      this.$store.dispatch('setQuestId', this.quest.id)
                      this.isSubmit = false
                      console.log(res)
                    },
                    err => {
                      console.log(err)
                    }
                )
              }
              // 릴레이 아닌 경우 퀘스트 디테일로 바로 보내기
              else {
                this.$store.dispatch('setQuestId', this.quest.id)
                this.isSubmit = false
              }
            } else this.$router.push('/404')
          },
          err => this.$router.push('/error')
      )
    },
    // 수정
    onEdit() {
      const data = {
        articleId: this.selectedArticle.id,
        content: this.content
      }
      ArticlesApi.editArticle(
          data,
          res => {
            if (res.data === 'OK') {
              this.savedArticle = this.selectedArticle
              this.savedArticle['content'] = this.content
              this.$store.dispatch('replaceOldArticle', this.savedArticle)
              this.$store.dispatch('setSelectedId', 0)

              this.isSubmit = false

            } else this.$router.push('/404')
          },
          err => this.$router.push('/error')
      )
    },
    onFriendSelect() {
      // 릴레이 친구 선택 끝난 경우
    }

  },
  // computed
  computed: {
    ...mapState({
      quest: state => state.quests.questDetail,
      loginUser: state => state.user.loginUser,
      selectedArticle: state => state.articles.selectedArticle
    }),
    // isUserSelect() {
    //   return this.quest.type === 'R' &&
    // }

    // 버튼 비활성화
    disabled() {
      if (this.isUpdate)
        return this.content === this.selectedArticle.content || !this.content || this.isSubmit
      else if (this.quest.type === 'R')
        return !(this.preview && this.content && this.participant && !this.error && !this.nextError) || this.isSubmit
      else
        return !(this.preview && this.content && !this.error) || this.isSubmit
    },
    isArticleSelected() {
      return this.$store.getters.isArticleSelected
    }
  },
  // watch
  watch: {
    isArticleSelected(v) {
        this.$router.push({path: '/article', query: {id: this.savedArticle.id}})
    }
  },
  // lifecycle hook
  // created() {
    // 릴레이인 경우 미리 참가자 목록 불러오기
    // if (!this.isUpdate && this.quest.type === 'R') {
    //   QuestApi.requestGroupFriends(
    //     this.loginUser.id,
    //       res => {
    //         if (res.data !== 'NOT_FOUND') {
    //           this.users = this.res.data
    //         }
    //       },
    //       err => console.log(err)
          // err => this.$router.push('/error')
      // )
    // }
  // },
  // navigation guard
  beforeRouteEnter: (to, from, next) => {
    if (from.name === 'QuestDetail')
      next()
    else if (from.name === 'ArticleDetail')
      next(vm => {
        vm.isUpdate = true
        vm.error = '사진은 수정하실 수 없습니다.'
        vm.content = vm.selectedArticle.content
      })
    else
      next('/404')
  },
  // beforeRouteLeave: (to, from, next) => {
    // 릴레이 퀘스트인 경우에는
    // if (to.name === 'QuestDetail' && this.quest.type === 'R') {
    //   this.isModal = true

    // }
  // }
}
</script>

<style scoped>
.name-tag.inside-input {
  border-radius: 10px;
  position: absolute;
  left: 36px;
  margin-top: 9px;
}
</style>