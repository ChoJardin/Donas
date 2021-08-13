<template>
  <div>

    <AwsImageUploader/>



    <h1>Test</h1>
    <image-input class="test" :preview.sync="preview" :image-file.sync="imageFile"
                 :modified-date.sync="modifiedDate"/>
    <br><br>

    <CommentInput></CommentInput>

  </div>
</template>

<script>
import ImageInput from "@/components/common/ImageInput";
import CommentInput from "@/components/articles/CommentInput";
import AwsImageUploader from "@/components/common/AwsImageUploader";

export default {
  name: "Test",
  components: {
    ImageInput,
    CommentInput,
    AwsImageUploader
  },
  data() {
    return {
      preview: '',
      imageFile: '',
      modifiedDate: '',
    }
  },
  methods: {
    setPreview(preview) {
      this.preview = preview
    }
  }
}
</script>

<style scoped>
.test {
  border: 1px solid #292929;
}
</style>