<template>
  <div>
    <div class="error">
      <p class="main">ERROR</p>
      <p>
        요청하신 페이지에
        <br>
        문제가 발생하였습니다.
      </p>
    </div>

    <button @click="goHome" class="btn-bottom go-home">홈으로 이동</button>
  </div>
</template>

<script>

export default {
  name: "Error",
  methods: {
    goHome() {
      this.$router.push('/');
    }
  }
}
</script>

<style>
  .error {
    display: block;
    box-sizing: border-box;
    padding: 20px;
    margin-top: 10vh;
    font-size: 2rem;
    font-weight: bold;
  }
  .go-home {
    background: #3893A8;
    color: #292929;
  }
  .error-404 .main {
    font-size: 3.5rem;
  }
</style>