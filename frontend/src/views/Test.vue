<template>
  <div>
    <h1>Test</h1>
    <image-input class="test" :preview.sync="preview" :image-file.sync="imageFile"
                 :modified-date.sync="modifiedDate"/>
    <br><br>

    <img :src="preview" alt="">
    {{imageFile}}
  </div>
</template>

<script>
import ImageInput from "@/components/common/ImageInput";

export default {
  name: "Test",
  components: {
    ImageInput
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