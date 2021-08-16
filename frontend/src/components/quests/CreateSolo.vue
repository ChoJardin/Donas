<template>
  <div class="create-solo-main">
    <div>
      <div class="create-quest-questions">
        <div class="create-question-title">퀘스트 이름을 입력해 주세요
        <span class="create-question-subtext">(10자 이내)</span></div>
        <input autofocus class="create-quest-input" v-model="title" type="text" maxlength="10" placeholder="예) 미라클 모닝">
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title">간단한 설명을 입력해 주세요
        <span class="create-question-subtext">(14자 이내)</span></div>
        <input class="create-quest-input" v-model="description" type="text" maxlength="100" placeholder="예) 매일 30분 독서하기">
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title">시작일을 지정해 주세요</div>
        <input class="create-quest-input" v-model="startAt" type="date" maxlength="100" placeholder="예) 매일 30분 독서하기">
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title">종료일을 지정해 주세요</div>
        <input class="create-quest-input" v-model="finishAt" type="date" maxlength="100" placeholder="예) 매일 30분 독서하기">
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title" >인증 방법을 입력하세요</div>
        <textarea class="create-quest-textarea" v-model="certification" type="text" placeholder="예) 시간이 나오고 책 페이지가 나오게 사진 찍기">
        </textarea>
      </div>

    <div class="solo-img-input" style="width: 80%; margin-left: 15px">
      <AwsImageUploader
          id="image-input" ref="aws"
          @preview="onPreview" @on-error="onError"/>
    </div>
      <div class="solo-quest-image">
  <!--      <img v-if="isUpdate" :src="quest.picture" alt="">-->
        <img v-if="preview" :src="preview" style="width: 200px; height: 200px; padding-top:20px" alt="">
        <div v-else class="solo-upload-info">
          등록한 이미지는 수정이 불가합니다.
          <br/>신중히 선택해 주세요.
          <br/><br/>
          5MB 이내의 .jpg/ .jpeg/ .png 파일만 <br/>등록 가능합니다.
        </div>
        <div id="image-error" v-html="error" v-if="error"></div>

      </div>

      <button class="button" @click="onClick">생 성 하 기</button>
      </div>
    </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";
import QuestApi from "../../api/QuestApi";
import AwsImageUploader from "@/components/common/AwsImageUploader";

export default {
  name: "CreateSingle",
  components: {
    AwsImageUploader,
  },
  data: () => {
    return {
      userId: '',
      title: '',
      description: '',
      startAt: '',
      finishAt: '',
      picture: '',
      certification: '',
      mileage: 2000,
      //image
      selectedFile: '',
      preview: '',
      error: '',
      isSaved: false,
    }
  },
  //computed
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
    })
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
      this.picture = await this.$refs.aws.uploadFile()
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
      // console.log(data)
      // let path
      QuestApi.createPersonalQuest(
          data,
          res => {
            if(res === "NO_CONTENT") {
              alert('입력확인')
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

  }
</script>

<style scoped>
.create-quest-questions {
  margin: 0px 15px 15px 15px;
  text-align: left;
}

.create-question-title {
  margin-bottom: 7px;
  font-size: 1.0em;

}

.create-question-subtext {
  font-size: 0.6em;
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
}

.create-quest-textarea {
  border: black solid 1px;
  margin-top: 5px;
  margin-bottom: 10px;
  width: 100%;
  font-size: 0.8em;
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
/*.solo-img-input{*/
/*  display: flex;*/
/*  flex-direction: column;*/
/*  justify-content: center;*/
/*  align-items: center;*/
/*}*/
.solo-quest-image{
  width: 250px;
  height: 250px;
  /*margin-left: 50px;*/
  margin: 20px auto;
  border: #292929 1px solid;
  border-radius: 25px;

}

.solo-upload-info{
  padding-top: 50px;
}

</style>