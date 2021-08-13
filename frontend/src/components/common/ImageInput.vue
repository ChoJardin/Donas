<template>
  <div>
    <input type="file" accept="image/jpeg, image/png" capture="camera"
           @change="onImageUploaded">

    <img :src="preview" alt="">
  </div>
</template>

<script>
export default {
  name: "ImageInput",
  props: ['preview', 'imageFile', 'modifiedDate'],
  data() {
    return {
      selectedFile: null,
      error: '',
    }
  },
  methods: {
    onImageUploaded(event) {
      this.selectedFile = event.target.files[0]
      // 이미지 확장자/ 크기 확인
      let extension = this.selectedFile.name.substring(
          this.selectedFile.name.lastIndexOf('.')+1
      ).toLowerCase()

      // 이미지 파일이 아닌 경우
      if (!['jpg', 'jpeg', 'png'].includes(extension)) {
        this.error = '이미지 파일을 선택해 주세요.'
      }
      // 파일의 크기가 너무 큰 경우
      else if (this.selectedFile.size > 1048576) {
        this.error = '1MB 이내의 파일만 선택 가능합니다.'
      }

      // 에러 발생하면 에러메세지 emit
      if (this.error) {
        this.$emit('on-error', this.error)
      }
      // 오류 없는 경우 미리보기와 formData emit
      else {
        // 미리보기
        const read = new FileReader()
        read.onload = file => {
          this.$emit('update:preview', file.target.result)
        }
        read.readAsDataURL(this.selectedFile)

        // 서버 전송 데이터
        const imageFile = new FormData()
        imageFile.append('image', this.selectedFile)
        this.$emit('update:image-file', imageFile)

        // 마지막 수정 날짜
        this.$emit('update:modified-date', this.selectedFile.lastModifiedDate)
      }
    }
  },
}
</script>

<style scoped>

</style>