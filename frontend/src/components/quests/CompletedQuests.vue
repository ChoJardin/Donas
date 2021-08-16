<template>
  <div>

    <MyQuestInfo @click.native="setQuestId(quest.id)"
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
  name: "CompletedQuests",
  components: {
    MyQuestInfo
  },
  data() {
    return {
      quests: [
          {
            id: 0,
            title: '',
            description: '',
            picture: '',
            type: "P",
            startAt: moment(),
            finishAt: moment(),
            mileage: 0
          }
      ],
    }
  },
  methods: {
    ...mapActions(['setQuestId'])
  },
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser
    })
  },
  created() {
    const data = {
      userId: this.loginUser.id,
      status: 'c'
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

</style>