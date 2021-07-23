<template>
  <div>
    <div>
      <h1>마일리지</h1>
      <router-link to="/user/mileage">기부</router-link> |
      <router-link to="/user/mileage/cashout">인출</router-link>
    </div>

    <router-view/>
  </div>
</template>

<script>
// import CashOut from "@/components/mileages/CashOut";
// import Donation from "@/components/mileages/Donation";

export default {
  name: 'Mileage',
  components: {
    // CashOut,
    // Donation
  }
}
</script>

<style scoped>

</style>