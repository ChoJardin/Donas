<template>
  <div class="donation">
    <h3>기부하실 단체를 선택해 주세요</h3>

      <div id="org-list" v-for="charity in charityList" :key="charity.id">
          <OrgList class="org-single"  :charity= "charity" @click.native=setCharityId(charity.id) />
      </div>

  </div>
</template>

<script>
import OrgList from "../../components/mileages/OrgList";
import {mapActions, mapGetters, mapState} from "vuex";

export default {
  name: "Donation.vue",
  components: {
    OrgList
  },
  methods: {
    ...mapActions(["setCharityId"])
  },
  computed: {
    ...mapState({
      charityList: state => state.mileages.charityList,
    }),
    ...mapGetters(['isLoggedIn']),
  },
  created() {
    this.charity = this.$store.getters.charityDetail;
    // if (!this.isLoggedIn) {
    //   this.$router.push({name: 'Login', params: {history: this.$route.fullPath}})
    //   return
    // }
  }
}
</script>

<style scoped>
.donation {
  margin-top: 20px;
}

.org-single {
  text-decoration: none;
  color: black;
}

</style>