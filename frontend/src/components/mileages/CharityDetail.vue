<template>
  <div>
    <h1>{{charity.name}}</h1>
<!--    <a :href="link"></a>-->
  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex";

export default {
  name: "CharityDetail",
  // data() {
  //   return {
  //     charity:
  //   }
  // },
  computed: {
    ...mapState({
      charityId: state => state.mileages.charityId,
    }),
    charity: function () {
      return this.$store.state.charityList.filter(charity => charity.id === this.charityId)
      }
    },
  }



</script>

<style scoped>

</style>