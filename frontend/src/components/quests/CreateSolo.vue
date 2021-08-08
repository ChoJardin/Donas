<template>
  <div>
    <div>
      <div class="create-quest-questions">
        <div class="create-question-title">퀘스트 이름을 입력해 주세요
        <span class="create-question-subtext">(10자 이내)</span></div>
        <input autofocus class="create-quest-input" :input.sync="title" type="text" maxlength="10" placeholder="예) 미라클 모닝">
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title">간단한 설명을 입력해 주세요
        <span class="create-question-subtext">(14자 이내)</span></div>
        <input class="create-quest-input" :input.sync="description" type="text" maxlength="100" placeholder="예) 매일 30분 독서하기">
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title">시작일을 지정해 주세요</div>
        <input class="create-quest-input" :input.sync="startAt" type="date" maxlength="100" placeholder="예) 매일 30분 독서하기">
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title">종료일을 지정해 주세요</div>
        <input class="create-quest-input" :input.sync="finishAt" type="date" maxlength="100" placeholder="예) 매일 30분 독서하기">
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title" >인증 방법을 입력하세요</div>
        <textarea class="create-quest-textarea" :input.sync="certification" type="text" placeholder="예) 시간이 나오고 책 페이지가 나오게 사진 찍기">
        </textarea>
      </div>

      <div class="create-quest-questions">
        <div class="create-question-title" >퀘스트 대표 사진을 올려주세요</div>
        <input class="create-quest-img" type="file" accept="image/*; capture=camera" :input.sync="picture" >
      </div>
    </div>
  </div>
</template>

<script>
import PV from "password-validator";

export default {
  name: "CreateSingle",
  data: () => {
    return {
      type:'Personal',
      title: '',
      description: '',
      startAt:'',
      finishAt: '',
      created_by:'',
      picture:'../../assets/donut_flag.png',
      certification: '',
      mileage:2000,
    }
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

.create-quest-img {
  width: 100%;
}

</style>