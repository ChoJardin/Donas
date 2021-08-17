<template>
  <div>
    <div class="create-quest-info">
      생성된 퀘스트는 수정 및 삭제가 <span style="color: #cd4e3e">불가</span>합니다. <br/> 신중한 생성바랍니다.
    </div>

    <!--대표 이미지 등록-->
    <div class="create-quest-questions">
      <div class="create-question-title">퀘스트 대표이미지를 등록하세요</div>
      <div class="create-quest-image">
        <img v-if="preview" :src="preview" alt="">
        <img v-else src="https://donas.s3.ap-northeast-2.amazonaws.com/donuts/donut_flag.png" alt="">
        <div v-if="error.picture" class="error-text" >
          <div v-html="error.picture"></div>
          <button @click="$refs.aws.onReset()" class="cancel-select">선택취소</button>
        </div>
      </div>
    </div>
    <div class="create-quest-img-input">
      <AwsImageUploader
          id="image-input" ref="aws"
          @preview="onPreview" @on-error="onError"/>
    </div>

    <div class="create-quest-upload-info">
      <span style="font-family: GongGothicMedium">5MB</span> 이내의
      <span style="font-family: GongGothicMedium">.jpg/ .jpeg/ .png</span> 파일만 <br/>등록 가능합니다.
    </div>
    <!--대표 이미지 등록-->


    <div>
      <div class="create-quest-questions">
        <div class="create-question-title">
          <div>
            퀘스트 이름을 입력해 주세요
            <span class="create-question-subtext">(14자 이내)</span>
          </div>
          <div class="create-question-needed">
            필수
          </div>
        </div>
        <input autofocus class="create-quest-input" v-model="title" type="text" maxlength="14" placeholder="예) 미라클 모닝">
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title">
          <div>
            간단한 설명을 입력해 주세요
            <span class="create-question-subtext">(25자 이내)</span>
          </div>
          <div class="create-question-needed">
            필수
          </div>
          </div>
        <input class="create-quest-input" v-model="description" type="text" maxlength="25" placeholder="예) 매일 30분 독서하기">
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title">
          <div>
            함께 할 동료를 선택해 주세요
            <span class="create-question-subtext">(최소 1명)</span>
          </div>
          <div class="create-question-needed">필수</div>
        </div>
          <input @click="isModal = true" class="create-quest-input" type="text" placeholder="친구를 검색해보세요" readonly>
          <MidModal v-if="isModal" @close="isModal = false">
              <div slot="header" style="width: 100%; text-align: center">친구 찾기</div>
              <CreateGroupFriend slot="opt1"
                                 :friends.sync="participants"
                                 :participants="participants"
                                 @on-friend-select="onFriendSelect" ref="friends"></CreateGroupFriend>
          </MidModal>
          <div style="margin: 5px 0 10px">
            <span
                @click="onDelete(`${friend}`)"
                v-for="(friend, idx) in participants" :key="idx"
                class="name-tag"
            >
              {{friend}}&nbsp;&nbsp;X
            </span>
          </div>
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title">
          <div style="width: 100%">
          시작일을 지정해 주세요
          <span class="create-question-needed" style="float: right">필수</span>
        <div class="create-question-subtext">(생성일 기준 이틀 후의 날짜부터 선택 가능합니다)</div>
          </div>
        </div>
        <input class="create-quest-input" v-model="startAt" type="date" :min="startDate">
        <div v-if="error.startAt" class="create-quest-error">종료일 이전의 날짜만 선택 가능합니다</div>
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title">
          종료일을 지정해 주세요
          <div class="create-question-needed">필수</div>
        </div>
        <input class="create-quest-input" v-model="finishAt" type="date" :min="endDate">
        <div v-if="error.finishAt">시작일 이후의 날짜만 선택 가능합니다</div>
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title">
          <div>
            인증빈도를 설정해 주세요
            <span class="create-question-subtext">(몇일에 한번 인증)</span>
          </div>
          <div class="create-question-needed">필수</div>
        </div>
        <input class="create-quest-input" v-model="frequency" type="number" name="members" min="1"
        placeholder="인증 빈도를 정해주세요">
        <div v-if="error.frequency" class="create-quest-error">퀘스트 기간에 맞는 빈도수를 기재해야 합니다</div>
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title" >
          인증 방법을 입력하세요
          <div class="create-question-needed">필수</div>
        </div>
        <textarea class="create-quest-textarea" v-model="certification" type="text" placeholder="예) 시간이 나오고 책 페이지가 나오게 사진 찍기">
        </textarea>
      </div>


      <button class="create-quest-submit" :disabled="buttonDisabled" @click="onClick">생 성 하 기</button>
      </div>

  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import moment from "moment";
import QuestApi from "../../api/QuestApi";
import UserApi from "../../api/QuestApi";
import CreateGroupFriend from "./CreateGroupFriend";
import AwsImageUploader from "../common/AwsImageUploader";
import MidModal from "@/components/common/MidModal";

import('@/assets/style/quests/create-quests.css')


export default {
  name: "CreateGroup",
  components: {
    CreateGroupFriend,
    AwsImageUploader,
    MidModal
  },
  data: () => {
    return {
      userId: '',
      title: '',
      description: '',
      startAt: moment().add(2, 'days').format('YYYY-MM-DD'),
      finishAt: '',
      picture: '',
      certification: '',
      mileage:2000,
      participants: [],
      //image
      selectedFile: '',
      preview: '',
      error: {
        startAt: false,
        finishAt: false,
        picture: false,
        frequency: false
      },
      isSaved: false,
      isSubmit: false,
      isModal: false,
      frequency: 0,
    }
  },
  //computed
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
    }),
    startDate() {
      return moment().add(2, 'days').format('YYYY-MM-DD')
    },
    endDate() {
      return this.startAt
    },
    buttonDisabled() {
      return !(this.title && this.description && this.startAt && this.finishAt && this.certification && !!this.participants.length) ||
          Object.keys(this.error).some(key => this.error[key]) || this.isSubmit
    },
    articleNeededTotal(){
      const start = moment(this.startAt, "YYYY-MM-DD")
      const end = moment(this.finishAt, "YYYY-MM-DD")
      return moment.duration(end.diff(start)).asDays();
    },

    minArticle() {
      return Math.floor(this.articleNeededTotal / parseInt(this.frequency))
    }
  },
  //methods
  methods: {
    onFriendSelect() {
      this.participants = this.$refs.friends.friends
    },
    onDelete(name) {
      this.participants = this.participants.filter(participant => participant !== name)
    },
    onPreview(preview) {
      this.preview = preview
    },
    onError(error) {
      this.error = error
    },
    async onClick() {
      this.isSubmit = true
      this.picture = await this.$refs.aws.uploadFile()
      this.onSubmit()
    },
    onSubmit(){
      let data = {
        userId: this.loginUser.id,
        title: this.title,
        description: this.description,
        startAt: this.startAt,
        finishAt: this.finishAt,
        picture: this.picture,
        certification: this.certification,
        mileage: this.mileage,
        participants: this.participants,
        minArticleCount: this.minArticle
        }
      console.log(data)
      let path
      QuestApi.createGroupQuest(
          data,
          res => {
            console.log(res)
            if(res === "NO_CONTENT") {
              alert('입력확인')
              this.isSubmit = false
            }
            else{
              this.$router.push('/quests/group')
            }
          },
          err => {
            console.log(err)
            alert('입력확인')
          })
      },
    onSelect(friends) {
      this.participants = friends
    }
  },
  watch: {
    finishAt: function(v) {
      if (moment(v).isAfter(this.startAt)) {
        this.error.startAt = false
      }
    },
    startAt: function(v) {
      if(moment(v).isAfter(this.finishAt)) {
        this.error.startAt = true
        this.startAt = moment().format('YYYY-MM-DD')
        this.finishAt = ''
      } else {
        // this.error.startAt = false
      }
    },
    frequency: function(v) {
      if (parseInt(this.frequency) > this.articleNeededTotal) {
        this.error.frequency = true
      } else if ( 1 > parseInt(this.frequency)) {
        this.frequency = ''
        this.error.frequency = true
      } else {
        this.error.frequency = false
      }
    }
  },
  created() {
    console.log('friends fetched')
    UserApi.requestGroupFriends(
        this.loginUser.id,
        res => {
          console.log(res)
          this.$store.dispatch('setMutuals', res.data)
        },
        err => {
          console.log(err)
        }
    )
  },
}
</script>

<style scoped>

</style>