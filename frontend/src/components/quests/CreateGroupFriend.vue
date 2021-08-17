<template>
    <div id="staggered-list">
      <input v-model="query" style="border: 1px solid #292929" placeholder="친구를 검색해보세요">

      <div v-if="friends">
        <span v-for="(friend, idx) in friends" :key="idx">{{friend.nickname}}</span>
      </div>

      <transition-group
        name="staggered-fade"
        tag="ul"
        :css="false"
        @before-enter="beforeEnter"
        @enter="enter"
        @leave="leave"
      >
        <li
          v-for="(mutual, index) in searchResult"
          v-bind:key="mutual.nickname"
          v-bind:data-index="index"
        >
          <input
              type="checkbox" v-model="friends" @click="onChange(`${mutual.nickname}`)"
                 :id="`${mutual.nickname}`" :value="`${mutual.nickname}`" style="display: none">
          <label :for="`${mutual.nickname}`" class="checkbox">
            <!--<span class="material-icons-round">radio_button_unchecked</span>-->

          </label>
          <label :for="`${mutual.nickname}`">
            <UserProfileCard class="" :profile="mutual" :click="true" ></UserProfileCard>

          </label>
        </li>
      </transition-group>
    </div>

</template>

<script>
import {mapState} from "vuex";
import Velocity from 'velocity-animate'
import UserProfileCard from "@/components/user/UserProfileCard";

export default {
  name: "CreateRelayFriends",
  components: {
    UserProfileCard
  },
  data() {
    return{
      friends: [],
      query: '',
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
    },

    beforeEnter: function (el) {
      el.style.opacity = 0
      el.style.height = 0
    },
    enter: function (el, done) {
      const delay = el.dataset.index * 150
      setTimeout(function () {
        Velocity(
          el,
          { opacity: 1, height: '60px' },
          { complete: done }
        )
      }, delay)
    },
    leave: function (el, done) {
      const delay = el.dataset.index * 150
      setTimeout(function () {
        Velocity(
          el,
          { opacity: 0, height: 0 },
          { complete: done }
        )
      }, delay)
    }

  },
  computed: {
    ...mapState({
      mutuals: state => state.quests.mutuals,
    }),
    // 검색 결과 반환
    searchResult() {
      return this.mutuals.filter((item) => {
        return item.nickname.toLowerCase().indexOf(this.query.toLowerCase()) !== -1
      })
    }
  },

}
</script>

<style scoped>
li label.checkbox::before {

}
</style>