<template>
  <div>
    <h1>공동</h1>
    <br>
    <div>
      <div>
        <div class="create-quest-questions">
          <div class="create-question-title">퀘스트 이름을 입력해 주세요
          <span class="create-question-subtext">(10자 이내)</span></div>
          <input class="create-quest-input" type="text" maxlength="10" placeholder="예) 미라클 모닝">
        </div>

        <div class="create-quest-questions">
          <div class="create-question-title">어떤 주제의 퀘스트인가요?</div>
          <input type="checkbox" id="subject1">
          <label for="subject1">건강</label>

          <input type="checkbox" id="subject2">
          <label for="subject2">생활</label>

          <input type="checkbox" id="subject3">
          <label for="subject3">취미</label>

          <input type="checkbox" id="subject4">
          <label for="subject4">공부</label>

          <input type="checkbox" id="subject5">
          <label for="subject5">정서</label>

          <input type="checkbox" id="subject6">
          <label for="subject6">자산</label>

          <input type="checkbox" id="subject7">
          <label for="subject7">기타</label>
        </div>

        <div class="create-quest-questions">
          <div class="create-question-title">간단한 설명을 입력해 주세요
          <span class="create-question-subtext">(14자 이내)</span></div>
          <input class="create-quest-input" type="text" maxlength="100" placeholder="예) 매일 30분 독서하기">
        </div>

        <div class="create-quest-questions">
          <div class="create-question-title" >인증 방법을 입력하세요</div>
          <textarea class="create-quest-textarea" type="text" placeholder="예) 시간이 나오고 책 페이지가 나오게 사진 찍기">
          </textarea>
        </div>

        <div class="create-quest-questions">
          <div class="create-question-title" >퀘스트 대표 사진을 올려주세요</div>
          <input class="create-quest-img" type="file" accept="image/*; capture=camera">
        </div>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CreateGroup"
}
</script>

<style scoped>
.create-quest-questions {
  margin: 0px 20px 20px 20px;
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
.create-quest-img {

}
label {
  font-size: 0.8em;
  margin-right: 5px;
}
</style>