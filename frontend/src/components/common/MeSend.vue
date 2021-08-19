<template>
  <div id="message-wrap">
      <span id="chat-date">{{dateParsed}}</span>
    <span id="my-message">{{message.content}}</span>
  </div>
</template>

<script>
import moment from "moment";

export default {
  name: "MeSend",
  props: ['message'],
  computed: {
    dateParsed() {
      return moment(String(this.message.time)).format('HH:mm')
    }
  }
}
</script>

<style scoped>
#message-wrap {
  width: 100%;
  display: flex;
  justify-content: flex-end;
}
#my-message {
  /*border: 1px solid #292929;*/
  margin: 3px  0;
  padding: 5px 15px;
  border-radius: 12px;
  float: right;
  background-color: #F9DA94;
  font-family: GongGothicLight;
  font-size: 0.9em;
  color: #292929;
  text-align: left;
}

#chat-date {
  font-family: Arial;
  font-size: 0.4em;
  color: #cccccc;
  height: 100%;
  align-self: flex-end;
  margin-right: 3px;
}

</style>