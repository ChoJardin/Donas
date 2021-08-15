<template>
  <div class="overlay">
    <component-nav @on-arrow="$router.back()"/>

    <div v-if="$route.params.cashout === '1'" class="confirm-text">
      <div>기부금을 제외한 <span style="color: red">{{$route.params.amount}}</span>원이 </div>
      <div>영업일 기준 5일 이내에</div>
      <div>{{$route.params.bank}} {{$route.params.accountNum}}로 입금 될 예정입니다</div>
<!--      <div>{{$route.params.amount}} {{$route.params.accountNum}}로 입금 될 예정입니다</div>-->
    </div>

    <div v-if="$route.params.cashout === '0'" class="confirm-text">
      <div><span style="color: red">{{$route.params.amount}}</span>마일리지가 </div>
      <div>{{$route.params.charityName}}으로</div>
      <div>후원되었습니다. </div>
    </div>

    <button class="button" @click="goBack">확인</button>
  </div>
</template>

<script>
import ComponentNav from "../common/ComponentNav";

export default {
  name: "CashOutResult",
  // components
  components: {
    ComponentNav
  },
  // props
  // data
  // methods
  methods:{
    goBack() {
      this.$router.push({name: 'Main'})
    }
  }
  // computed
  // watch
  // lifecycle hook
}
</script>

<style scoped>
.overlay {
  position: fixed;
  top: 70px;
  right: 15px;
  bottom: 50px;
  left: 15px;
  overflow: scroll;
  background: white;
}

.confirm-text {
  font-size: 1.3em;
  line-height: 1.7em;
  margin-top: 50%;
}

.button {
  width: 90%;
  margin-left: 5%;
  margin-top: 25px;
  height: 50px;
  background-color: #183a1d;
  color: #e1eedd;
  border-radius: 25px;
  box-shadow: 0 0 15px -8px rgba(0, 0, 0, 0.55);
  font-size: 1em;
  cursor: pointer;
}

</style>