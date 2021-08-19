<template>
    <div id="staggered-list">
      <input v-model="query" placeholder="다음 주자를 검색해보세요"
          class="create-quest-input" type="text" @change="onSearch">

      <div style="margin: 5px 0 10px">
        <span class="search-result-error" v-if="error">{{error}}</span>

        <span v-else-if="user"
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
              type="radio" v-model="user" @change="$emit('update:user', user)"
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
      error: '',
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
    onChange() {
      if (!this.error) {
        this.$emit('update:user', this.user)
      }
    }
  },
  computed: {
    ...mapState({
      alreadyIn: state => state.quests.questDetail.users
    }),
  //   ...mapState({
  //     mutuals: state => state.quests.mutuals,
  //   }),
  },
  watch: {
    query: function(v) {
      this.onSearch()
    },
    user: function(v) {
      // 이미 참여한 사람인 경우
      if (this.alreadyIn.some(user => user.nickname === v.nickname)) {
        this.error = '이미 해당 퀘스트에 참여한 유저입니다!'
        this.$emit('error', true)
      } else {
        this.error = false
        this.$emit('error', false)
      }
    }
  },
  created() {
    this.onSearch()
  }
}
</script>

<style scoped>
.search-result-error {
  color: #cd4e3e;
  font-size: 0.8em;
}

</style>