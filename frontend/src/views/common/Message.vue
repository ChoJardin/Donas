<template>
  <div>
    <h1>Message</h1>


    <input type="text" class="message-input">
    <button class="message-send-text">
      전송
    </button>



  </div>
</template>

<script>
export default {
  name: "Message"
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
</style>