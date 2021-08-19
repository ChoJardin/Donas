<template>
  <div>
    <div id="success-mileage">
      <div>성공</div>
      <div v-if="type === 'R'">+200</div>
      <div v-else-if="type === 'G'">+150</div>
      <div v-else>+100</div>
    </div>
    <div id="burst-8">

    </div>
  </div>
</template>

<script>
export default {
  name: "Success",
  props: {
    type: {
      type: String,
      default: 'P'
    }
  }
}
</script>

<style scoped>
    #burst-8 {
      background: #183a1d;
      width: 40px;
      height: 40px;
      position: relative;
      text-align: center;
      transform: rotate(20deg);
    }
    #burst-8:before {
      content: "";
      position: absolute;
      top: 0;
      left: 0;
      height: 40px;
      width: 40px;
      background: #183a1d;
      transform: rotate(135deg);
    }
    #success-mileage {
      color: #e1eedd;
      position: absolute;
      z-index: 4;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      width: 40px;
      height: 40px;
      font-size: 0.8em;
      font-family: GongGothicLight;
    }
</style>