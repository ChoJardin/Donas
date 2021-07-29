<template>
  <div>
    <div class="error-404">
      <p class="text">404</p>
      <p>
        Page Not Found
        <br>
        요청하신 페이지를
        <br>
        찾을 수 없습니다.
      </p>
    </div>

    <button @click="goBack" class="btn-bottom go-back">돌아가기</button>
  </div>
</template>

<script>

export default {
  methods: {
    goBack() {
      this.$router.go(-1);
    }
  }
}
</script>

<style>
  .error-404 {
    display: block;
    box-sizing: border-box;
    padding: 20px;
    margin-top: 10vh;
    font-size: 2rem;
    font-weight: bold;
  }
  .go-back {
    background: #3893A8;
    color: #292929;
  }
  .error-404.text {
    font-size: 3.5rem;
  }

</style>