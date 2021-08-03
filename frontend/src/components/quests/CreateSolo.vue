<template>
  <div>
    <h1>개인</h1>
    <br>
    <div>
      <h4>어떤 주제의 퀘스트인가요?</h4>
      <input type="checkbox" id="subject1">
      <label for="subject1">건강</label>

      <input type="checkbox" id="subject2">
      <label for="subject2">생활</label>

      <input type="checkbox" id="subject3">
      <label for="subject3">취미</label>

      <input type="checkbox" id="subject4">
      <label for="subject4">공부</label>
      <br>

      <input type="checkbox" id="subject5">
      <label for="subject5">정서</label>

      <input type="checkbox" id="subject6">
      <label for="subject6">자산</label>

      <input type="checkbox" id="subject7">
      <label for="subject7">기타</label>
    </div>
    <br>
    <div>
      <h4>제목을 입력하세요</h4>
      <input type="text" maxlength="100" placeholder="예) 매일 30분 독서하기">
    </div>
    <br>
    <div>
      <h4>인증 방법을 입력하세요</h4>
      <textarea type="text" placeholder="예) 시간이 나오고 책 페이지가 나오게 사진 찍기">
      </textarea>
    </div>
  </div>
</template>

<script>
export default {
  name: "CreateSingle"
}
</script>

<style scoped>

</style>