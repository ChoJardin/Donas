<template>
  <div class="overlay">
    <div id="article-detail-arrow">
      <a href="#" @click.prevent="$emit('on-click')">
        <i class="material-icons color292929">arrow_back</i>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: "PasswordChange",
  // components
  // props
  // data
  data() {
    return {
      isOn: false
    }
  },
  // methods
  // computed
  // watch
  // lifecycle hook
}
</script>

<style scoped>
.overlay {
  position: fixed;
  top: 60px;
  right: 0;
  bottom: 0;
  left: 0;
  overflow: scroll;
  background: white;
}

#article-detail-arrow {
  display: flex;
  margin: 15px;
  color: #292929;
}

.material-icons.color292929 {
  color: #183a1d;
}

</style>