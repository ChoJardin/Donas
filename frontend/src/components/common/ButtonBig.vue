<template>
  <div class="component">
    <input
        type="button" :value="value" :style="`background-color: ${color}; color: ${text}`"
        @click="$emit('on-click')" :class="`{disabled: ${disabled}}`"
    >
  </div>
</template>

<script>
export default {
  name: "ButtonBig",
  props: {
    value: {
      type: String,
      default: 'Click'
    },
    color: {
      type: String,
      default: '#f1a64b'
    },
    text: {
      type: String,
      default: '#292929'
    },
    disabled: {
      type: Boolean,
      default: false
    }
  },
}
</script>

<style scoped>
.component {
  height: 50px;
  margin-bottom: 25px;
}

input {
  border: none;
  outline:none;
  display:inline-block;
  height:50px;
  border-radius:25px;
  width:100%;
  box-sizing:border-box;
  box-shadow: 0 0 15px -9px rgba(0, 0, 0, 0.55);
  font-size: 1em;
  font-weight: bold;
  color: #292929;
  cursor: pointer;
}

</style>