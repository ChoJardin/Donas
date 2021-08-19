<template>
  <div id="message-wrap">
    <span id="my-message">{{message.content}}</span>
    <span id="chat-date">{{dateParsed}}</span>
  </div>
</template>

<script>
import moment from "moment";

export default {
  name: "YouSend",
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
  justify-content: flex-start;
}

#my-message {
  /*border: 1px solid #292929;*/
  padding: 5px 15px;
  margin: 3px 0;
  border-radius: 12px;
  float: left;
  background-color: #e4e6e9;
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
  margin-left: 3px;
}


</style>