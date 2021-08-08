<template>
  <div>
    <img src="@/assets/donut_error.png" alt="">
    <button @click="goHome" class="btn-bottom go-home">홈으로 이동</button>
  </div>
</template>

<script>

export default {
  name: "Error",
  methods: {
    goHome() {
      this.$router.push('/main');
    }
  }
}
</script>

<style scoped>
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

img {
  width: 100%;
  margin-top: 100px;
}

</style>