<template>
  <div>
    <div id="component-nav">
      <a href="#" @click.prevent="$emit('on-arrow')">
        <i class="material-icons color292929">arrow_back</i>
      </a>
      <div>
        {{ title }}
      </div>
      <button v-if="button" @click="$emit('on-button')" :disabled="disabled">
        {{ button }}
      </button>
      <div v-else id="button-alternative"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ComponentNav",
  props: {
    title: {
      type: String,
      default: '',
    },
    button: {
      type: String,
      default: ''
    },
    disabled: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<style>

#component-nav {
  height: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 15px;
  width: 100%;
  color: #183a1d;
  position: fixed;
  top: 60px;
  left: 0;
  right: 0;
  margin: 0 auto;
  z-index: 3;
  background: white;
  border-bottom: 1px solid #e4e6e9;
}

  @media screen and (min-width: 425px) {
    #component-nav {
      width: 425px;
    }
}


.material-icons.color292929 {
  color: #183a1d;
}

#component-nav a,
#component-nav button,
#component-nav #button-alternative {
  width: 60px;
}

#component-nav a {
  text-align: start;
}

/* 프로필 정보 수정 제목 */
#component-nav div {
  font-size: 1em;
  font-family: GongGothicBold;
  text-align: center;
}

/* 수정완료 버튼 */
#component-nav button {
  color: #183a1d;
  font-size: 0.8em;
  border: 1px solid #183a1d;
  padding: 2px 0;
  border-radius: 7px;
  box-shadow: 0 0 15px -9px rgba(0, 0, 0, 0.55);
}

#component-nav button:disabled {
  opacity: 0.5;
}

</style>