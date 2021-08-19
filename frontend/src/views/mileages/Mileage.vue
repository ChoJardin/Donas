<template>
  <div>

    <ComponentNav @on-arrow="$router.back()" title="마일리지"></ComponentNav>

    <div class="mileage-header">
      <div v-if="isLoggedIn" class="flex-container">
        <div class="profile-wrap">
          <img v-if="loginUser.picture" class="profile-image" :src="loginUser.picture" alt="">
          <img v-else class="profile-image" src="../../assets/donut_profile.png" alt="">
          <div class="profile-info">
            <div class="user-mileage">
              <div><span class="mileage-username">{{loginUser.nickname}} </span>님의 마일리지</div>
              <div>
                <span class="mileage-username">{{currencyString}} </span>마일리지
                <router-link to="/mileage/usage" class="inline-button"><div id="mileage-receipt"><span id="mileage-receipt-button">사용 내역</span></div></router-link>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div v-else class="flex-container">
        <div class="profile-wrap">
          <img class="profile-image" src="../../assets/donut_profile.png" alt="">
          <div class="profile-info">
            <h1 class="username">함께 하시려면</h1>
            <div class="mileage-total"><router-link :to="{name: 'Login', params: {history: this.$route.fullPath}}">로그인</router-link> 을 해주세요!</div>
          </div>
        </div>
      </div>

      <div id="alert">
        <i class="fas fa-star" style="color: #fcb131"></i>
        <a id='title' href="">2021 1분기 기부 내역</a>
      </div>
      <div class="branch">
        <router-link to="/user/mileage" class="button" replace>기부</router-link>
        <router-link v-if="isLoggedIn" to="/user/mileage/cashout" class="button" replace>출금</router-link>
      </div>

    </div>


    <router-view class="router-view"/>
  </div>
</template>

<script>
// import CashOut from "@/components/mileages/CashOut";
// import Donation from "@/components/mileages/Donation";
import {mapGetters, mapState} from "vuex";
import UserApi from "../../api/UserApi";
import MileagesApi from "../../api/MileagesApi";
import ComponentNav from "@/components/common/ComponentNav";
export default {
  name: 'Mileage',
  components: {
    ComponentNav
    // CashOut,
    // Donation
  },
  // props
  // data
  // methods
  // computed
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
      mileage: state => state.user.loginUser.mileage
    }),
    ...mapGetters(['isLoggedIn']),
    currencyString: function (){
      const amount = this.mileage
      return amount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    }
  },
  created() {
      MileagesApi.requestCharityList(
        res => {
          this.$store.dispatch('setCharityList', res)
        },
        err => {
          console.log(err)
        }
    )
    },
}
</script>

<style scoped>
.mileage-header {
  background-color: #ffffff;
  position: fixed;
  width: calc(100% - 30px);
  max-width: 395px;
  top: 110px;
  left: 15px;
  right: 15px;
  margin: 0 auto;
  z-index: 2;
}


.flex-container {
  margin: 10px 0;
  display: flex;
  flex-direction: column;
  text-align: start;
}

.profile-wrap {
  display: flex;
}

.profile-image {
  width: 80px;
  height: 80px;
  object-fit: cover;
}

.profile-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  line-height: 1.9em;
  flex: 2 2 0;
  padding-left: 15px;
}

.user-mileage {
  display: flex;
  flex-direction: column;
  font-family: GongGothicLight;
}

.mileage-username {
  font-family: GongGothicBold;
  font-size: 1.1em;
}

#mileage-receipt{
  text-align: right;
  font-size: 0.7em;
  line-height: 1.2em;
  display: inline-block;
}

.inline-button {
  float: right;
  color: #183a1d;
}


#mileage-receipt-button {
  border: #183a1d solid 1px;
  padding: 2px 5px;
  border-radius: 8px;
}
#mileage-receipt-button


.mileage-total{
  font-size: larger;
  font-weight: bold;
}

#alert{
  padding:3px 0 3px 10px;
  background-color: #ffffff;
  border-top: solid #05a05b 2px;
  border-bottom: solid #05a05b 2px;
  font-family: GongGothicLight;
  text-align: left;
}

#title{
  padding-left: 10px;
  text-decoration: none;
  color:black;
  font-size: 0.8em;
}
.branch {
  height: 30px;
  background-color: #ffffff;
  margin: 10px 0 5px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  font-size: 1em;

}

.branch a.router-link-exact-active {
  border-bottom: #f0a04b solid;
}

.button {
  width: 90%;
  margin-left: 1%;
  margin-right: 1%;
  border-bottom: lightgray solid 1px;
  text-decoration: none;
  color: black;
  text-align: center;
  font-size: 1.0em;
}

.router-view {
  position: absolute;
  top: 230px;
  width: calc(100% - 30px);
  max-width: 395px;
  left: 15px;
  right: 15px;
  margin: 0 auto;

}


</style>