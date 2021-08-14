<template>
  <div>

    <AwsImageUploader/>



    <h1>Test</h1>
    <image-input class="test" :preview.sync="preview" :image-file.sync="imageFile"
                 :modified-date.sync="modifiedDate"/>
    <br><br>

    <CommentInput></CommentInput>


    <hr>
    <br>

    <button id="show-modal" @click="showModal = !showModal" >모달 열기</button>

    <Modal v-if="showModal" @close="showModal = !showModal">
      <button slot="opt1" @click="onModalOpt1">수정하기</button>
      <button slot="opt2" @click="onModalOpt2">삭제하기</button>
    </Modal>

    <button @click="kakaoLogin">카카오 요청 보내기 </button>
  </div>
</template>

<script>
import ImageInput from "@/components/common/ImageInput";
import CommentInput from "@/components/articles/CommentInput";
import AwsImageUploader from "@/components/common/AwsImageUploader";
import Modal from "@/components/common/Modal";
import UserApi from "@/api/UserApi";

export default {
  name: "Test",
  components: {
    ImageInput,
    CommentInput,
    AwsImageUploader,
    Modal,
  },
  data() {
    return {
      preview: '',
      imageFile: '',
      modifiedDate: '',
    //  모달
      showModal: false,
    }
  },
  methods: {
    setPreview(preview) {
      this.preview = preview
    },
  //  모달
    onModalOpt1() {
      console.log('수정할거다아아')
    },

    // 카카로 로그인
    kakaoLogin() {
      UserApi.kakaoLogin(this.$route.query.code)
    }
  }
}
</script>

<style scoped>
.test {
  border: 1px solid #292929;
}
</style>