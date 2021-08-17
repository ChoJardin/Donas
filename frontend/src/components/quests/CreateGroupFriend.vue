<template>
    <div id="staggered-list">
      <input v-model="query" placeholder="친구를 검색해보세요"
          class="create-quest-input" type="text" >

      <div style="margin: 5px 0 10px">
        <span
            @click="onDelete(`${friend}`)"
            v-for="(friend, idx) in friends" :key="idx"
            class="name-tag"
        >
          {{friend}}&nbsp;&nbsp;X
        </span>
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
              type="checkbox" v-model="friends" @change="($emit('update:friends', friends))"
              :id="`${mutual.nickname}`" :value="`${mutual.nickname}`" style="display: none">
          <label :for="`${mutual.nickname}`" class="checkbox">
            <!--<span class="material-icons-round">radio_button_unchecked</span>-->
            <!--@click="onChange(`${mutual.nickname}`)"-->

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
  name: "CreateGroupFriends",
  components: {
    UserProfileCard
  },
  props: {
    participants: {
      type: Array,
    }
  },
  data() {
    return{
      friends: [],
      query: '',
    }
  },
  methods: {
    onDelete(name) {
      this.friends = this.friends.filter(friend => friend !== name)
      this.$emit('update:friends', this.friends)
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
  created() {
    this.friends = this.participants
  }

}
</script>

<style scoped>

</style>