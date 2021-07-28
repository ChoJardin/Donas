<template>
  <div>
    <div id="flex-container">
      <div id="profile-wrap">
        <img id="profile-image" src="@/assets/도넛1.png" alt="">
        <div id="profile-info">
          <h1 id="username">{{this.$route.params.nickname}}</h1>
          <div id="mileage-total">8,000원</div>
        </div>
      </div>
    </div>

    <div id="alert">
      <i class="fas fa-star" style="color: #f1a64b"></i>
      <a id='title' href="">2021 1분기 기부 내역</a>
    </div>

    <div>
      <router-link to="/user/mileage">기부</router-link> |
      <router-link to="/user/mileage/cashout">인출</router-link>
    </div>

    <router-view class="router-view"/>
  </div>
</template>

<script>
// import CashOut from "@/components/mileages/CashOut";
// import Donation from "@/components/mileages/Donation";
import {mapState} from "vuex";
export default {
  name: 'Mileage',
  components: {
    // CashOut,
    // Donation
  },
  // props
  // data
  // methods
  // computed
  computed: {
    ...mapState({
      nickname: state => state.user.loginUser.nickname,
    })
  }
}
</script>

<style scoped>
#flex-container {
  display: flex;
  flex-direction: column;
  text-align: start;
  margin: 15px;
}

#profile-wrap {
  display: flex;
}

#profile-image {
  width: 100px;
  flex: 1 1 0;
}

#profile-info {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  flex: 2 2 0;
  padding-left: 15px;
}

#mileage-total{
  font-size: larger;
  font-weight: bold;
}

#alert{
  margin-bottom: 10px;
  padding:3px 0px 3px 10px;
  border-top: solid #6cb9a2 2px;
  border-bottom: solid #6cb9a2 2px;
  text-align: left;
}

#title{
  padding-left: 10px;
  text-decoration: none;
}



</style>