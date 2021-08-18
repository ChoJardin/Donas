<template>
    <div id="staggered-list">
      <input v-model="query" placeholder="다음 주자를 검색해보세요"
          class="create-quest-input" type="text" @change="onSearch">

      <div style="margin: 5px 0 10px">
        <span v-if="user"
            class="name-tag"
        >{{user.nickname}}
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
          v-for="(individual, index) in userList"
          v-bind:key="individual.nickname"
          v-bind:data-index="index"
        >
          <input
              type="radio" v-model="user" @change="($emit('update:user', user))"
              :id="`${individual.nickname}`" :value="individual" style="display: none">
          <label :for="`${individual.nickname}`" class="checkbox">

          </label>
          <label :for="`${individual.nickname}`">
            <UserProfileCard class="" :profile="individual" :click="true" ></UserProfileCard>

          </label>
        </li>
      </transition-group>
    </div>

</template>

<script>
import {mapState} from "vuex";
import Velocity from 'velocity-animate'
import UserProfileCard from "@/components/user/UserProfileCard";
import UserApi from "@/api/UserApi";

export default {
  name: "SelectNextParticipant",
  components: {
    UserProfileCard
  },
  // props: {
  //   users: {
  //     type: Array,
  //   }
  // },
  data() {
    return{
      user: '',
      query: '',
      userList: [],
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
            {opacity: 1, height: '60px'},
            {complete: done}
        )
      }, delay)
    },
    leave: function (el, done) {
      const delay = el.dataset.index * 150
      setTimeout(function () {
        Velocity(
            el,
            {opacity: 0, height: 0},
            {complete: done}
        )
      }, delay)
    },
    // 검색 결과 반환
    onSearch() {
      const data = {
        nickname: this.query
      }
      UserApi.searchAuto(
          data,
          res => {
            if (res.data !== 'NO_CONTENT') {
              this.userList = res.data
            }
          },
          err => this.$router.push('/error')
      )

    },
  },
  // computed: {
  //   ...mapState({
  //     mutuals: state => state.quests.mutuals,
  //   }),
  // },
  watch: {
    query: function(v) {
      this.onSearch()
    }
  },
  created() {
    this.onSearch()
  }
}
</script>

<style scoped>

</style>