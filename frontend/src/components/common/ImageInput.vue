<template>
  <div>
    <input type="file" @change="onFileUploaded" accept="image/jpeg,png" capture="camera">
  </div>
</template>

<script>
export default {
  name: "ImageInput",
  data() {
    return {
      selectedFile: ''
    }
  },
  // methods: {
  //   onFileSelected()
  // }


}
</script>

<style scoped>

</style>