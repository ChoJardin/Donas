<template>
  <div>
    <input
      type="file" id="image-input" ref="imgInput"
      accept="image/jpeg, image/png" capture="camera"
      @change="handleFile"
      >

  </div>
</template>

<script>
import AWS from 'aws-sdk'

export default {
  name: "AwsImageUploader",
  props: ['preview', 'imageFile'],
  data() {
    return {
      file: null,
      error: '',
      url: '',
      // Aws
      albumBucketName: 'donas',
      bucketRegion: 'ap-northeast-2',
      IdentityPoolId: 'ap-northeast-2:018d5eb6-9c1a-4823-8d25-97a4797b1b7d',
    }
  },
  methods:{
    // 이미지 입력창 실행
    onOpen() {
      this.$refs.imgInput.click()
    },
    handleFile() {
      this.file = this.$refs.imgInput.files[0]

      // 이미지 확장자/ 크기 확인
      let extension = this.file.name.substring(
          this.file.name.lastIndexOf('.')+1
      ).toLowerCase()
      // 이미지 파일이 아닌 경우
      if (!['jpg', 'jpeg', 'png'].includes(extension)) {
        this.error = '이미지 파일을 선택해 주세요.'
      }
      // 파일의 크기가 너무 큰 경우
      else if (this.file.size > 1048576) {
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
          this.$emit('preview', file.target.result)
        }
        read.readAsDataURL(this.file)

        // 마지막 수정 날짜
        this.$emit('modified-date', this.file.lastModifiedDate)
      }

    },
    async uploadFile() {
      const image = this.file
      let imageKey = this.file.name
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId,
        })
      });
      const s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: {Bucket: this.albumBucketName}
      });
      try {
        const result = await s3.upload({
          Key: imageKey,
          Body: image,
          ContentType: image.type,
          ACL: 'public-read'
        }).promise()
        return result.Location
      } catch (err) {
        console.log(err)
      }
    }
  }
}
</script>

<style scoped>

</style>