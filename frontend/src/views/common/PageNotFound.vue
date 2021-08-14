<template>
  <div>

    <img class="error-404" src="@/assets/donut_404.png" alt="">
    
    <button @click="$router.push('/main')" class="btn-bottom go-back">홈으로 이동</button>
  </div>
</template>

<script>

export default {
  name: 'PageNotFound',
}
</script>

<style scoped>
  /*.error-404 {*/
  /*  display: block;*/
  /*  box-sizing: border-box;*/
  /*  padding: 20px;*/
  /*  margin-top: 10vh;*/
  /*  font-size: 2rem;*/
  /*  font-weight: bold;*/
  /*}*/
  /*.go-back {*/
  /*  background: #3893A8;*/
  /*  color: #292929;*/
  /*}*/
  /*.error-404.text {*/
  /*  font-size: 3.5rem;*/
  /*}*/

.error-404 {
  width: 100%;
  margin-top: 100px;
}
</style>