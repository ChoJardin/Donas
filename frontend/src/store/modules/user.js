import cookies from 'vue-cookies'
import UserApi from "@/api/UserApi";
// import axios from 'axios'
import router from '@/router'
import moment from "moment";
import CommonApi from "@/api/CommonApi";

const state ={
  loginUser: {
    id: 0,
    nickname: undefined,
    picture: '',
    description: '',
    mileage:'',
  },
  // selectedProfile: {
  //   id: '',  // 유저 아이디
  //   nickname:'',  // 닉네임
  //   picture: '',  // 이미지 경로
  //   description: '',  // 인사말
  //   quests: [
  //     {
  //        // 진행 중 퀘스트
  //     }
  //   ],
  //   questCnt: '',  // 퀘스트 개수
  //   questPercent: '',  // 퀘스트 진행률
  //   follower: '',  // 팔로워 수
  //   following: '',  // 팔로잉 수
  //   isFollowing: false,  // 팔로워인지 여부
  // },

  // test
  selectedProfile: {
    id: 1,
    nickname: 'test',
    picture: 'https://s3.us-west-2.amazonaws.com/secure.notion-static.com/24ee1645-83ec-4152-98e5-72f1b78b649b/profile.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210731%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210731T061020Z&X-Amz-Expires=86400&X-Amz-Signature=f7db16140993cee27c8ee688a26b42aaf5bb67030633e7afd6288f8a3d40b875&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22profile.png%22',
    description: '안녕',
    quests: [
      {
        // 진행중인 퀘스트
      }
    ],
    follower: 3,  // 팔로워 수
    following: 4,  // 팔로잉 수
    isFollowing: false,  // 팔로워인지 여부
  },
  // 팔로우 페이지 정보
  follow: {
    followerList: [{nickname: 1, picture: null}, {nickname: 2, picture: null}, {nickname: 3, picture: null}],
    followingList: '',
  },

  //알람 리스트
  commonAlarms:[{
    id : '',
    receiveId: '',
    contents: '',
    sendTime: '',
    confirm: '',
    sendName: '',
  }],

  questAlarms:[{
    id : '',
    receiveId: '',
    contents: '',
    sendTime: '',
    questId: '',
    sendName: '',
    confirm: '',
  }],
  userMileage: 0,

  recentSearch: [{
    id: '',
    nickname: undefined,
    picture: '',
    description: '',
  }],

  searchResult: [{
    id: '',
    nickname: undefined,
    picture: '',
    description: '',
  }],

  autoResult: [{
    id: '',
    nickname: undefined,
    picture: '',
    description: '',
  }],

  payload: {},

  // 메세지
  messageList: [
    {
      id: '',
      otherId: '',
      otherName: '',
      otherPicture: '',
      content: '',
      lastTime: '',
    }
  ],
  // 채팅 목록
  chatHistory: {
    id: '',
    otherName: '',
    otherPicture: '',
    messages: [
        {
            id: '',
            content: '',
            own: 0,
            time: ''
        },
    ]
}

}


const getters = {
  isLoggedIn(state) {
    return !!state.loginUser.nickname
  },
  messageCnt(state) {
    return state.chatHistory.messages.length
  }
  // newQuestAlarm(state) {
  //   return state.questAlarms.some(function(element){ if(element.confirm === '1') {return true}})
  // },
  // newCommonAlarm: state => {
  //    return state.commonAlarms.some(function(element){ if(element.confirm === '1') {return true}})
  // }

}

const mutations = {
  // 로그인 유저 정보 저장
  SET_LOGIN_USER(state, loginUser) {
    state.loginUser = loginUser
  },
  // 로그아웃
  LOGOUT(state) {
    state.loginUser = ''
    state.userId = ''
    cookies.remove('auto-login')
    cookies.remove('login-user')
    cookies.remove('alarm-token')
  },
  // 선택된 유저의 프로필 정보 저장 -> 프로필 페이지
  SET_SELECTED_USER_PROFILE(state, profile) {
    state.selectedProfile = profile
  },
  // 팔로워 리스트
  SET_FOLLOWERS(state, followers) {
    state.follow.followerList = followers
  },
  // 팔로잉 리스트
  SET_FOLLOWINGS(state, followings) {
    state.follow.followingList = followings
  },
  // 팔로우
  // 프로필 페이지에 보이는 정보이기 때문에 cnt/ 상태만 업뎃하면 됩니다
  ADD_FOLLOWER(state) {
    state.selectedProfile.follower += 1
    state.selectedProfile.isFollowing = true
  },
  // 언팔로우
  DELETE_FOLLOWER(state) {
    state.selectedProfile.follower -= 1
    state.selectedProfile.isFollowing = false
  },

  SET_COMMON_ALARMS(state, common) {
    state.commonAlarms = common
  },
  SET_QUEST_ALARMS(state, quest) {
    state.questAlarms = quest
  },

  SET_MILEAGE(state, mileage) {
    state.userMileage = mileage
  },

  SET_RECENT_SEARCH(state, recent) {
    state.recentSearch = recent
  },

  SET_SEARCH_RESULT(state, result) {
    state.searchResult = result
  },

  SET_AUTO_RESULT(state, result) {
    state.autoResult = result
  },

  SET_PAYLOAD(state, payload) {
    state.payload = payload
  },

  // 메세지
  // 메세지 리스트 불러오기
  SET_MESSAGE_LIST(state, list) {
    state.messageList = list
  },
  SET_CHAT(state, data) {
    state.chatHistory = data
  }
}


const actions = {
  // 로그인된 유저 정보 저장
  requestLoginUserProfile({dispatch}, {id}) {
     UserApi.requestLoginUser(
      id,
      res => {
        // 일치하는 유저 아이디 없음
        if (res.data === 'NOT_FOUND') {
          console.log('not found')
          router.push('/404')
        } else {
          // 정상적으로 유저 정보 가져온 경우
          res.data.id = id
          dispatch('setLoginUser', res.data)
        }
      },
      err => {
        // console.log(err)
        router.push('/error')
      }
    )
  },

  // 로그인 이용자 정보 업데이트
  // -> 라우터 새로고침시 매번 로딩
  async updateUserInfo ({dispatch, state}, {id}) {
    await UserApi.requestLoginUser(
      id,
      async (res) => {
        if (res.data !== 'NOT_FOUND') {
          res.data.id = id  // id는 없어서 따로 추가가 필요합니다.
          await dispatch('setLoginUser', res.data)
        }
      },
      (err) => {console.log(err)}
    )
  },


  // 로그인된 유저 정보 저장
  setLoginUser({commit}, profile) {
    commit('SET_LOGIN_USER', profile)
    cookies.set('login-user', profile, 0)
  },

  // 로그아웃
  logout({commit}) {
    commit('LOGOUT')
  },
  // 선택된 유저의 프로필 정보 저장
  setUserProfile({commit}, profile) {
    delete profile.articles
    commit('SET_SELECTED_USER_PROFILE', profile)
  },
  // 팔로워 리스트
  setFollowers({commit}, followers) {
    commit('SET_FOLLOWERS', followers)
  },
  // 팔로잉 리스트
  setFollowings({commit}, followings) {
    commit('SET_FOLLOWINGS', followings)
  },
  // 팔로우
  followFunction({commit, state}) {
    // 팔로우 취소
    if (state.selectedProfile.isFollowing) {
      commit('DELETE_FOLLOWER')
    } else {
      commit('ADD_FOLLOWER')
    }
  },
  // 알림 리스트
  setCommonAlarms({commit}, common) {
    // console.log(common)
    commit('SET_COMMON_ALARMS', common)
  },

  updateCommonAlarms ({dispatch, state}, id) {
    UserApi.requestCommonAlert(
      id,
      async (res) => {
        if (res.data !== 'NOT_FOUND') {
          res.data.id = id  // id는 없어서 따로 추가가 필요합니다.
          await dispatch('setCommonAlarms', res.data)
        }
      },
      (err) => {console.log(err)}
    )
  },

  fetchCommonAlarms ({dispatch, state}) {
    UserApi.requestCommonAlert(
      state.loginUser.id,
      async (res) => {
        if (res.data !== 'NOT_FOUND') {
          res.data.id = state.loginUser.id  // id는 없어서 따로 추가가 필요합니다.
          await dispatch('setCommonAlarms', res.data)
        }
      },
      (err) => {console.log(err)}
    )
  },

  setQuestAlarms({commit}, quest) {
    // console.log(quest)
    commit('SET_QUEST_ALARMS', quest)
  },

  async updateQuestAlarms ({dispatch, state}) {
    await UserApi.requestQuestAlert(
      state.loginUser.id,
      async (res) => {
        if (res.data !== 'NOT_FOUND') {
          res.data.id = state.loginUser.id  // id는 없어서 따로 추가가 필요합니다.
          await dispatch('setQuestAlarms', res.data)
        }
      },
      (err) => {console.log(err)}
    )
  },

  setMileage({commit}, mileage) {
    commit('SET_MILEAGE', mileage)
  },

  setRecentSearch({commit}, recent) {
    commit('SET_RECENT_SEARCH', recent)
  },

  setSearchResult({commit}, result) {
    commit('SET_SEARCH_RESULT', result)
  },

  setAutoResult({commit}, auto) {
    commit('SET_AUTO_RESULT', auto)
  },

  setPayload ({commit}, payload){
    // console.log(payload)
    commit('SET_PAYLOAD', payload)
  },

  // 메세지 관련

  setMessageAll ({dispatch, state}) {
    if (router.currentRoute.name === 'MessageRoom') {
      console.log('why')
      const data = {
        userId: state.loginUser.id,
        otherId: router.currentRoute.params.id
      }
      dispatch('setChat', data)
    } else {
      console.log('here')
      dispatch('setMessageList')
    }
  },




  // 메세지 리스트 세팅
  setMessageList ({commit, state}) {
    const data = {
      userId: state.loginUser.id
    }
    CommonApi.requestMessageList(
      data,
      res => {
        // 성공적으로 리스트를 불러왔음
        // 메세지 리스트가 없는 경우 [] 들어옵니다.
        commit('SET_MESSAGE_LIST', res.data)
        console.log(res.data.confirm)
      },
      err => {
        router.push('/error')
      }
    )
  },
  // 채팅방 세팅
  setChat({commit, state}, data) {
    CommonApi.requestEachRoom(
      data,
      res => {
        if (res.data !== 'NOT_FOUND') {
          commit('SET_CHAT', res.data)
        } else {
          // 대화 내역이 없음
          commit('SET_CHAT', null)
        }
      },
        err => {
        router.push('/error')
      }
    )
  }

}



export default {
  state, getters, mutations, actions
}