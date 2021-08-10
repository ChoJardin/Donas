<template>
  <div>
    <ul>
      <li v-for="(mutual, idx) in mutuals" :key="idx">
        <input type="checkbox" v-model="friends" @click="onChange(`${mutual.id}`)" :id="`${mutual.id}`" :value="`${mutual.id}`">
        <label :for="`${mutual.id}`">{{mutual.nickname}}</label>
      </li>
    </ul>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "CreateRelayFriends",
  data() {
    return{
      friends: []
    }
  },
  methods: {
    onChange(justAdded) {
      let idx = this.friends.indexOf(justAdded)
      let changed
      if (idx !== -1) {
        changed = this.friends.splice(idx, 1)
      } else {
        changed = [...this.friends, justAdded]
      }
      this.$emit('participants', changed)
      console.log(justAdded)
    }
  },
  computed: {
    ...mapState({
      mutuals: state => state.quests.mutuals,
    })
  },


}
</script>

<style scoped>

</style>