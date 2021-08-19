<template>
  <div>
    <Message></Message>

    <Success></Success>
  </div>
</template>

<script>
import Message from "@/views/common/MessageRoom";
import Success from "@/components/common/Success";

export default {
  name: "Test",
  components: {
    Message,
    Success
  },
  data() {
    return {
    }
  },
  methods: {
  }
}
</script>

<style scoped>
.test {
  border: 1px solid #292929;
}
</style>