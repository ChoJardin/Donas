<template>
  <div id="message-input">


    <div class="comment-input-button-wrap">
    <input
        class="comment-input" v-model="input"
        type="text" placeholder="메세지를 입력해주세요" @keypress.enter="onSend">
    <button @click="onSend" class="comment-input-button" :disabled="!input">전송</button>
    </div>
  </div>
</template>

<script>
import CommonApi from "@/api/CommonApi";
import {mapState} from "vuex";

export default {
  name: "Message",
  props: ['receiveId'],
  data() {
    return {
      input: '',
    }
  },
  methods: {
    onSend() {
      // 여기서 이제 메세지 보내는 함수 적어줍니다.
      const data = {
        sendId: this.loginUser.id,
        receiveId: this.receiveId,
        content: this.input
      }
      CommonApi.sendMessage(
          data,
          res => {
            if (res.data === 'OK') {
              // 전체 채팅 목록 다시 불러오기
              console.log('ok')
            }
          }, err => {
            this.$router.push('/error')
          }
      )
      console.log(this.input)
      this.input = ''
      console.log('reset')
    }
  },
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser
    })
  }
}
</script>

<style scoped>
.message-input {
  border: 1px solid #292929;
  position: fixed;
  bottom: 60px;
  width: 100%;
  max-width: 350px;
  left: 0;
  right:0;
  margin: 0 auto 0 0;
}

.message-send-text {
  position: sticky;
  bottom: 60px;
}

.comment-input-button-wrap {
  display: flex;
  position: relative;
  height: 40px;
}

.comment-input {
  border: 1px solid #292929;
  /*border-top: 1px solid #292929;*/
  /*border-bottom: 1px solid #292929;*/
  /*border-right: 1px solid #292929;*/
  width: 83%;
  height: 100%;
  padding: 0 15px;
  font-family: GongGothicLight;
  font-size: 0.8em;
  position: relative;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
  /*box-shadow: ;*/
}

.comment-input-button {
  border: 1px solid #292929;
  /*position: absolute;*/
  right: 0;
  width: 17%;
  height: 100%;
  padding: 0 5px;
  font-size: 0.8em;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: #e1eedd;
  color: #183a1d;
}

.comment-input-button:disabled {
  opacity: 0.5;
}

.comment-input-info {
  display: flex;
  justify-content: space-between;
}

.comment-input-info span {
  margin: 3px 6px;
  font-family: GongGothicLight;
  font-size: 0.8em;
  color: #a0a0a0;
}

</style>