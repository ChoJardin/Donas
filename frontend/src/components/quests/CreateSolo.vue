<template>
  <div class="create-solo-main">

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
          시작일을 지정해 주세요
          <div class="create-question-needed">필수</div>
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
    </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import moment from "moment";
import QuestApi from "../../api/QuestApi";
import AwsImageUploader from "@/components/common/AwsImageUploader";
import ButtonBig from "@/components/common/ButtonBig";

export default {
  name: "CreateSingle",
  components: {
    AwsImageUploader,
    // ButtonBig
  },
  data: () => {
    return {
      userId: '',
      title: '',
      description: '',
      startAt: moment().format('YYYY-MM-DD'),
      finishAt: '',
      picture: '',
      certification: '',
      mileage: 2000,
      //image
      selectedFile: '',
      preview: '',
      error: {
        startAt: false,
        finishAt: false,
        picture: false
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
    startDate() {
      return moment().format('YYYY-MM-DD')
    },
    endDate() {
      return this.startAt
    },
    buttonDisabled() {
      return !(this.title && this.description && this.startAt && this.finishAt && this.certification) ||
          Object.keys(this.error).some(key => this.error[key]) || this.isSubmit
    }
  },
  //methods
  methods: {
    onPreview(preview) {
      this.preview = preview
    },
    onError(error) {
      this.error.picture = error
    },
    async onClick() {
      this.isSubmit = true

      if (this.preview) {
        this.picture = await this.$refs.aws.uploadFile()
      }

      this.onSubmit()
    },
    onSubmit() {
      let data = {
        userId: this.loginUser.id,
        title: this.title,
        description: this.description,
        startAt: this.startAt,
        finishAt: this.finishAt,
        picture: this.picture,
        certification: this.certification,
        mileage: this.mileage
        }
      // let path
      QuestApi.createPersonalQuest(
          data,
          res => {
            if(res === "NO_CONTENT") {
              alert('입력확인')
              this.isSubmit = false
            }
            else{
              this.$router.push('/quests/solo')
            }
          },
          err => {
            console.log(err)
          })
      },
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
    }
  }

  }
</script>

<style scoped>
.create-quest-questions {
  margin: 0px 15px 15px 15px;
  text-align: left;
}

.create-quest-info {
  /*font-family: GongGothicBold;*/
  margin: 10px 0 30px;
}

.create-question-title {
  margin: 10px 0 5px;
  border-bottom: 1px solid #f6c453;
  padding-bottom: 5px;
  font-size: 0.8em;
  display: flex;
  justify-content: space-between;
  /*margin-bottom: 7px;*/
  /*font-family: GongGothicLight;*/
}

.create-question-subtext {
  font-size: 0.6em;
  font-family: GongGothicLight;
}

.create-question-needed {
  font-family: GongGothicLight;
  color: #cd4e3e;
}

.create-quest-input {
  border: black solid 1px;
  margin-top: 5px;
  margin-bottom: 10px;
  width: 100%;
  font-size: 0.8em;
  padding-left: 10px;
  border-radius: 10px;
  height: 30px;
  padding-top: 5px;
  padding-bottom: 5px;
  font-family: GongGothicLight;
}

.create-quest-error {
  /*font-family: GongGothicLight;*/
  font-size: 0.8em;
  color: #cd4e3e;
}

.create-quest-textarea {
  border: black solid 1px;
  margin-top: 5px;
  margin-bottom: 10px;
  width: 100%;
  font-size: 0.8em;
  font-family: GongGothicLight;
  padding-left: 10px;
  border-radius: 10px;
  height: 70px;
  padding-top: 5px;
  padding-bottom: 5px;
}
.create-solo-questions .create-quest-img {
  /*height: 10px;*/
}
label {
  font-size: 0.8em;
  margin-right: 5px;
}

.button {
  width: 90%;
  height: 50px;
  border-radius:25px;
  box-shadow: 0 0 15px -8px rgba(0, 0, 0, 0.55);
  font-size: 1em;
  /*font-weight: bold;*/
  background-color: #183a1d;
  color: #e1eedd;
  cursor: pointer;
}

.button:disabled {
  opacity: 0.6;
}

.solo-img-input{
  font-family: GongGothicLight;
  font-size: 0.9em;
  margin-left: 15px;
  width: calc(100% - 150px);
  /*display: flex;*/
  /*flex-direction: column;*/
  /*justify-content: center;*/
  /*align-items: center;*/
}


.solo-quest-image-input-set {
  display: flex;
}

.solo-quest-image{
  position: relative;
  width: 90%;
  height: 90%;
  /*border-radius: 50%;*/
  object-fit: cover;
  padding: 2px;
  /*width: 250px;*/
  /*height: 250px;*/
  /*margin-left: 50px;*/
  margin: 10px auto;
  display: flex;
  justify-content: center;
  /*border: #183a1d 1px solid;*/
  /*border-radius: 25px;*/
}

.solo-quest-image .error-text {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.7);
  top: 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: #cd4e3e;
  font-size: 0.8em;
}

.error-text .cancel-select {
  margin-top: 10px;
  /*border: 1px solid #292929;*/
  border-radius: 8px;
  background-color: #cd4e3e;
  /*background-color: rgba(255, 255, 255, 0.7);*/
  color: #ffffff;
  padding: 2px 3px;
}

.solo-quest-image img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
  padding: 2px;
  border: 1px solid #183a1d;
}

.solo-upload-info{
  margin: 10px 20px 20px;
  font-family: GongGothicLight;
  font-size: 0.8em;
  text-align: left;

}

</style>