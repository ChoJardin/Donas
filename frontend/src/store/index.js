import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

import user from "@/store/modules/user";
import articles from "@/store/modules/articles";
import mileages from "@/store/modules/mileages";
import quests from "@/store/modules/quests";


Vue.use(Vuex)

// const store = new Vuex.Store({
//   modules: {user, articles, mileages, quests}
// })

export default new Vuex.Store({
  modules: {user, articles, mileages, quests, },
  plugins: [
    createPersistedState({
      path: ['articles'],
    })
  ]
})

// export default store