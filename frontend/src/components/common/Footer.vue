<template>
  <div>
    <div id="flex-container">
      <a href="/">메인</a>
      <a href="/quests">퀘스트</a>
      <a href="/user/feed">피드</a>
      <a href="/user/mileage">마일리지</a>
      <a href="/user/profile">마이페이지</a>
    </div>
  </div>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped>
#flex-container {
  height: 100%;
  display: flex;
  align-items: center;
}

#flex-container a {
  flex: auto;
}

</style>