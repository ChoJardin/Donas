<template>
  <div id="upsert-article">

    <ComponentNav
        @on-arrow="$router.back()"
        title="퀘스트 인증"/>

    <div class="element-wrap">
      <div class="title">
        퀘스트
      </div>
      <div class="quest-name">
        <span>공동</span>
        <span>개인</span>
        <span>릴레이</span>
        진짜 퀘스트 이름
      </div>
    </div>

    <div class="element-wrap">
      <div class="title">
        인증사진 등록
      </div>
      <div class="info">
        필수 등록값 입니다
      </div>
    </div>

    <div class="article-image">
      <img v-if="preview" :src="preview" alt="">
      <div v-else class="upload-info">
        유의사항<br/><br/>
        1. 등록한 이미지는 수정이 불가합니다.
        <br/>신중히 선택해 주세요.
        <br/><br/>
        2. 퀘스트 인증 당일에 촬영한 사진만 <br/>등록 가능합니다.
        <br/><br/>
        3. 1MB 이내의 .jpg/ .jpeg/ .png 파일만 <br/>등록 가능합니다.
      </div>
      <div id="image-error" v-html="error" v-if="error"></div>

    </div>
    <AwsImageUploader
        id="image-input" ref="aws"
        @preview="onPreview" @on-error="onError" @modified-date="dateCheck"/>

    <div class="element-wrap">
      <div class="title">
        내용
      </div>
    </div>
    <textarea v-model="content" name="content" id="content" cols="28" rows="5"></textarea>

    <ButtonBig
        class="submit-button" value="작 성 완 료"
        @click.native="onClick" :class="{disabled: disabled}"
    />

  </div>
</template>

<script>
import ComponentNav from "@/components/common/ComponentNav";
import AwsImageUploader from "@/components/common/AwsImageUploader";
import ButtonBig from "@/components/common/ButtonBig";

import('@/assets/style/articles/UpsertArticle.css')

export default {
  name: "UpsertArticle",
  // components
  components: {
    ComponentNav,
    AwsImageUploader,
    ButtonBig
  },
  // props
  // data
  data() {
    return {
      picture: '',
      preview: '',
      error: '',
      content: '',
    }
  },
  // methods
  methods: {
    onPreview(preview) {
      this.preview = preview
    },
    onError(error) {
      this.error = error
    },
    // 사진 촬영 날짜 확인
    dateCheck(date) {
      // 오늘 날짜
      const today = String(new Date()).split(' ', 4)
      // 업로드 된 사진의 최근 수정일
      const _date = String(date).split(' ', 4)
      // console.log(JSON.stringify(today) === JSON.stringify(_date))
      if (JSON.stringify(today) !== JSON.stringify(_date)) {
        this.error = '인증일 당일에 촬영한 사진만<br/>등록 가능합니다!'
      } else {
        this.error = ''
      }
    },
    // aws 요청 보내고 기다리기
    async onClick() {
      this.picture = await this.$refs.aws.uploadFile()
      this.onSubmit()
    },
    onSubmit() {

    }
  },
  // computed
  computed: {
    disabled() {
      return !(this.preview && this.content && !this.error)
    },
  },
  // watch
  // lifecycle hook
  // navigation guard
}
</script>

<style scoped>

</style>