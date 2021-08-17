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
            목표인원을 설정해 주세요
            <span class="create-question-subtext">(최대 10명)</span>
          </div>
          <div class="create-question-needed">필수</div>
        </div>
        <input class="create-quest-input" v-model="targetCnt" type="number" name="members" min="2" max="10"
        placeholder="2 ~ 10 사이의 인원을 선택해주세요">
        <div v-if="error.targetCnt" class="create-quest-error">2 ~ 10 사이의 인원을 선택해주세요</div>
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title">
          시작일을 지정해 주세요
          <div class="create-question-needed">필수</div>
        </div>
        <input class="create-quest-input" v-model="startAt" type="date" :min="startDate">
        <div v-if="error.startAt" class="create-quest-error">종료일 이전의 날짜만 선택 가능합니다</div>
      </div>

      <!--<div class="create-quest-questions">-->
      <!--  <div class="create-question-title">-->
      <!--    종료일을 지정해 주세요-->
      <!--    <div class="create-question-needed">필수</div>-->
      <!--  </div>-->
      <!--  <input class="create-quest-input" v-model="finishAt" type="date" :min="endDate">-->
      <!--  <div v-if="error.finishAt">시작일 이후의 날짜만 선택 가능합니다</div>-->
      <!--</div>-->

      <div class="create-quest-questions">
        <div class="create-question-title" >
          인증 방법을 입력하세요
          <div class="create-question-needed">필수</div>
        </div>
        <textarea class="create-quest-textarea" v-model="certification" type="text" placeholder="예) 시간이 나오고 책 페이지가 나오게 사진 찍기">
        </textarea>
      </div>

      <!--<ButtonBig value="생 성 하 기" @click.native="onClick"></ButtonBig>-->
      <button class="create-quest-submit" :disabled="buttonDisabled" @click="onClick">생 성 하 기</button>
      </div>








  <!--  &lt;!&ndash;주희&ndash;&gt;-->
  <!--  <div>-->
  <!--    <div class="create-quest-questions">-->
  <!--      <div class="create-question-title">퀘스트 이름을 입력해 주세요-->
  <!--      <span class="create-question-subtext">(10자 이내)</span></div>-->
  <!--      <input autofocus class="create-quest-input" v-model="title" type="text" maxlength="10" placeholder="예) 미라클 모닝">-->
  <!--    </div>-->

  <!--    <div class="create-quest-questions">-->
  <!--      <div class="create-question-title">간단한 설명을 입력해 주세요-->
  <!--      <span class="create-question-subtext">(14자 이내)</span></div>-->
  <!--      <input class="create-quest-input" v-model="description" type="text" maxlength="100" placeholder="예) 매일 30분 독서하기">-->
  <!--    </div>-->

  <!--    <div class="create-quest-questions">-->
  <!--      <div class="create-question-title">목표인원을 설정해 주세요</div>-->
  <!--      <input class="create-quest-input" v-model="targetCnt" type="number" maxlength="2">-->
  <!--    </div>-->

  <!--    <div class="create-quest-questions">-->
  <!--      <div class="create-question-title">시작일을 지정해 주세요</div>-->
  <!--      <input class="create-quest-input" v-model="startAt" type="date" maxlength="100" placeholder="예) 매일 30분 독서하기">-->
  <!--    </div>-->

  <!--    <div class="create-quest-questions">-->
  <!--      <div class="create-question-title" >인증 방법을 입력하세요</div>-->
  <!--      <textarea class="create-quest-textarea" v-model="certification" type="text" placeholder="예) 시간이 나오고 책 페이지가 나오게 사진 찍기">-->
  <!--      </textarea>-->
  <!--    </div>-->

  <!--    <div class="relay-img-input" style="width: 80%; margin-left: 15px">-->
  <!--    <AwsImageUploader-->
  <!--        id="image-input" ref="aws"-->
  <!--        @preview="onPreview" @on-error="onError"/>-->
  <!--  </div>-->
  <!--    <div class="relay-quest-image">-->
  <!--&lt;!&ndash;      <img v-if="isUpdate" :src="quest.picture" alt="">&ndash;&gt;-->
  <!--      <img v-if="preview" :src="preview" style="width: 200px; height: 200px; padding-top:20px" alt="">-->
  <!--      <div v-else class="relay-upload-info">-->
  <!--        등록한 이미지는 수정이 불가합니다.-->
  <!--        <br/>신중히 선택해 주세요.-->
  <!--        <br/><br/>-->
  <!--        5MB 이내의 .jpg/ .jpeg/ .png 파일만 <br/>등록 가능합니다.-->
  <!--      </div>-->
  <!--      <div id="image-error" v-html="error" v-if="error"></div>-->

  <!--    </div>-->

  <!--    <button class="button" @click="onClick">생 성 하 기</button>-->
  <!--    </div>-->

  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import QuestApi from "../../api/QuestApi";
import AwsImageUploader from "@/components/common/AwsImageUploader";
import moment from "moment";

import('@/assets/style/quests/create-quests.css')

export default {
  name: "CreateRelay",
  components: {
    AwsImageUploader,
  },
  data: () => {
    return {
      userId: '',
      title: '',
      description: '',
      startAt: moment().format('YYYY-MM-DD'),
      picture: '',
      certification: '',
      mileage: 2000,
      targetCnt: 0,
      //image
      selectedFile: '',
      preview: '',
      error: {
        startAt: false,
        picture: false,
        targetCnt: false
      },
      isSaved: false,
      isSubmit: false,
    }
  },
  //computed
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
    }),
    buttonDisabled() {
      return !(this.title && this.description && this.startAt && this.certification) ||
          Object.keys(this.error).some(key => this.error[key]) || this.isSubmit
    },
    startDate() {
      return moment().format('YYYY-MM-DD')
    },
    endDate() {
      const start = moment(this.startAt, "YYYY-MM-DD")
      const duration = parseInt(this.targetCnt) * 2
      return start.add(duration,'d').format( "YYYY-MM-DD")
    }
  },
  //methods
  methods: {
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
        targetCnt: this.targetCnt,
        finishedAt: this.endDate
        }
      console.log(data)
      let path
      QuestApi.createRelayQuest(
          data,
          res => {
            console.log(res)
            if(res === "NO_CONTENT") {
              alert('입력확인')
              this.isSubmit = false
            }
            else{
              this.$router.push('/quests/relay')
            }
          },
          err => {
            console.log(err)
          })
      }
    },
  watch: {
    targetCnt: function(v) {
      if (this.targetCnt == 1) {
        this.error.targetCnt = true
      } else if ( 2 > this.targetCnt || this.targetCnt > 10) {
        this.targetCnt = ''
        this.error.targetCnt = true
      } else {
        this.error.targetCnt = false
      }
}
  }
}
</script>

<style scoped>


</style>