<template>
  <div>

  </div>
</template>

<script>
export default {
  name: "Signup",
  // components
  // props
  // data
  // methods
  // computed
  // watch
  // lifecycle hook
  created() {
    this.passwordSchema
      // 영문, 숫자 포함 8~16자리
      .is().min(8)
      .is().max(16)
      .has().digits()
      .has().letters()
  }
}
</script>

<style scoped>

</style>