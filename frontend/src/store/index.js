import Vue from 'vue'
import Vuex from 'vuex'

import user from "@/store/modules/user";
import articles from "@/store/modules/articles";
import mileages from "@/store/modules/mileages";
import quests from "@/store/modules/quests";


Vue.use(Vuex)

export default new Vuex.Store({
  modules: {user, articles, mileages, quests, },

})