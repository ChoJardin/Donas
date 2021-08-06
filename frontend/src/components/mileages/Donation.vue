<template>
  <div class="donation">
    <h3>기부하실 단체를 선택해 주세요</h3>
    <div id="org-list">
      <OrgList class="org-single" v-for="n in 10" :key="n"/>
    </div>

  </div>
</template>

<script>
import OrgList from "../../components/mileages/OrgList";

export default {
  name: "Donation.vue",
  components: {
    OrgList
  }
}
</script>

<style scoped>
.donation {
  margin-top: 20px;
}

</style>