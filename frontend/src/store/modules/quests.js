// import cookies from 'vue-cookies'
// import axios from 'axios'
import router from '../../router'

const state ={
    questID: '',
}

const getters = {
    questId(state) {
    return state.questId
  },
}

const mutations = {
    SET_QUESTID: (state, questId) => state.questId = questId,
}

const actions = {
    setQuestId({commit}, questId){
        commit('SET_QUESTID', questId)
        router.push({name:'QuestDetail', params:{questId:state.questId}})
    },
}

export default {
    state, getters, mutations, actions
}