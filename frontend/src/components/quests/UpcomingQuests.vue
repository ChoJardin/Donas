<template>
  <div>

    <div v-if="quests.length === 0" class="no-quests">
      <br/>
        예정된 퀘스트가 없습니다. <br/>
      <router-link to="/quests">
        새로운 퀘스트를 시작해보세요!
      </router-link>
    </div>


    <MyQuestInfo v-else @click.native="setQuestId(quest.id)"
        v-for="quest in quests" :key="quest.id" :quest="quest">
    </MyQuestInfo>

  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import moment from "moment";
import QuestApi from "@/api/QuestApi";
import MyQuestInfo from "@/components/quests/MyQuestInfo";

export default {
  name: "UpcomingQuests",
  components: {
    MyQuestInfo
  },
  data() {
    return {
      quests: ''
    }
  },
  methods: {
    ...mapActions(['setQuestId'])
  },
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser,
      selectedProfile: state => state.user.selectedProfile
    })
  },
  created() {
    const data = {
      userId: this.selectedProfile.id,
      status: 'b'
    }
    QuestApi.requestMyQuests(
        data,
        res => {
          if (res.data === 'NOT_FOUND') {
            this.$router.push('/404')
          } else
            this.quests = res.data
        },
        err => console.log('/error')
    )
  }
}
</script>

<style scoped>

.no-quests {
  font-family: GongGothicLight;
  line-height: 1.5em;
}
.no-quests a {
  color: #292929;
}
</style>