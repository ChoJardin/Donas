// import cookies from 'vue-cookies'
// import axios from 'axios'
import router from '../../router'

const state ={
    questId: '',
    // 메인페이지 퀘스트관련 초기 정보
    mainQuestInfo: {
        // 개설되어 있는 모든 퀘스트
        questCnt: 0,
        // 개인 퀘스트
        questP: [
          {
            id: 0,
            picture: '',
            title: '',
            description: ''
          }
        ],
        // 공동 퀘스트
        questG: [
          {
            id: 0,
            picture: '',
            title: '',
            description: ''
          }
        ],
        // 릴레이 퀘스트
        questR: [
          {
            id: 0,
            picture: '',
            title: '',
            description: ''
          }
        ]
    },
  mutuals:[{
    id : '',
    nickname: '',
    picture: '',
    description: '',
  }],

  questsList: [{
    id : 0,
    type: '',
    title: '',
    picture: '',
    description: '',
    startAt: '',
    finishAt: '',
    mileage: 0,
    percent: "",
  }],

  questDetail: {
    id: 0,
    title: "",
    description: "",
    picture: "",
    type: "",
    startAt: "",
    finishAt: "",
    mileage: 0,
    percent: 0.0,
    certification: "",
    success: 0,
    users: [
        {
            id: 0,
            nickname: "",
            picture: "",
            description: "",
        }
    ],
    articles: [
        {
            id: 0,
            image: "",
            content: "",
            createdAt: "",
            updatedAt: "",
            makerName: "",
            makerImage:""
        }
    ],
    targetCnt: 0,
    nowCnt: 0
},

}

const getters = {
  questId(state) {
    return state.questId
  },
  singleQuest: state => {
    return state.questsList.filter(quest => quest.type === 'P')
  },
  groupQuest: state => {
    return state.questsList.filter(quest => quest.type === 'G')
  },
  relayQuest: state => {
    return state.questsList.filter(quest => quest.type === 'R')
  },
}

const mutations = {
    SET_QUESTID: (state, questId) => state.questId = questId,
    // 메인 페이지 퀘스트관련 초기 정보
    SET_QUEST_INFO(state, questInfo) {
        state.mainQuestInfo = questInfo
    },
    // 상호 팔로워 정보
    SET_MUTUALS(state, mutuals) {
      state.mutuals = mutuals
    },
    SET_QUESTS(state, quests) {
      state.questsList = quests
    },
    SET_QUESTDETAIL(state, questDetail) {
      state.questDetail = questDetail
    },

}

const actions = {
    setQuestId({commit}, questId){
        commit('SET_QUESTID', questId)
        router.push({name:'QuestDetail', params:{questId:state.questId}})
    },

    // 메인 페이지 퀘스트관련 초기 정보
    setMainQuestInfo({commit}, questInfo) {
        commit('SET_QUEST_INFO', questInfo)
    },

  // 상호 팔로워 정보
  setMutuals({commit}, mutuals){
      commit('SET_MUTUALS',mutuals)
  },

  // 전체 퀘스트 정보
  setQuest({commit}, quests){
      // console.log(quests)
      commit('SET_QUESTS', quests)
  },

  async setQuestDetail({commit}, questDetail){
      // console.log(questDetail)
    await commit('SET_QUESTDETAIL', questDetail)
  },


}

export default {
    state, getters, mutations, actions
}