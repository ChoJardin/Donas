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
      default: '#183a1d'
    },
    text: {
      type: String,
      default: '#e1eedd'
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
  box-shadow: 0 0 16px -9px rgba(0, 0, 0, 0.55);
  font-size: 1em;
  cursor: pointer;
}

input .disabled {
  opacity: 0.6;
  cursor: default;
}

</style>