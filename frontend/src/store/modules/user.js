import cookies from 'vue-cookies'
// import axios from 'axios'
// import router from '@/router'

const state ={
  loginUser: '',
  // selectedProfile: {
  //   id: '',
  //   nickname:'',
  //   picture: '', // 이미지 경로
  //   description: '',
  //   quests: [
  //     {
  //        // 진행중인 퀘스트
  //     }
  //   ],
  //   follower: '',  // 팔로워 수
  //   following: '',  // 팔로잉 수
  //   isFollowing: false,  // 팔로워인지 여부
  // },

  // test
  selectedProfile: {
    id: '2',
    nickname: 'cho',
    picture: 'https://s3.us-west-2.amazonaws.com/secure.notion-static.com/24ee1645-83ec-4152-98e5-72f1b78b649b/profile.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210729%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210729T144104Z&X-Amz-Expires=86400&X-Amz-Signature=77ffc26afd4c7af8fd935fdd31438eddf7435321ae5e5e6eb05f599f2427f771&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22profile.png%22', // 이미지 경로
    description: '안녕',
    quests: [
      {
        // 진행중인 퀘스트
      }
    ],
    follower: '3',  // 팔로워 수
    following: '4',  // 팔로잉 수
    isFollowing: false,  // 팔로워인지 여부
  },
  followers: [{}, {}, {}],
  followings: ''
}


const getters = {
  isLoggedIn(state) {
    return !!state.loginUser
  }
}

const mutations = {
  // 로그인 유저 정보 저장
  SET_USER(state, loginUser) {
    state.loginUser = loginUser
  },
  // 로그아웃
  LOGOUT(state) {
    state.loginUser = ''
    cookies.remove('login-user')
  },
  // 선택된 유저의 프로필 정보 저장
  SET_USER_PROFILE(state, profile) {
    state.selectedProfile = profile
  },
  // 팔로워 리스트
  SET_FOLLOWERS(state, followers) {
    state.followers = followers
  },
  // 팔로잉 리스트
  SET_FOLLOWINGS(state, followings) {
    state.followings = followings
  },

}


const actions = {
  // 로그인된 유저 정보 저장
  login({commit, dispatch}, {id, nickname, questCnt}) {
    let user = {id: id, nickname: nickname, questCnt: questCnt}
    commit('SET_USER', user)
    cookies.set('login-user', user, '2d')
  },
  // 로그아웃
  logout({commit}) {
    commit('LOGOUT')
  },
  // 선택된 유저의 프로필 정보 저장
  setUserProfile({commit}, profile) {
    delete profile.articles
    commit('SET_USER_PROFILE', profile)
  },
  // 팔로워 리스트
  setFollowers({commit}, followers) {
    commit('SET_FOLLOWERS', followers)
  },
  // 팔로잉 리스트
  setFollowings({commit}, followings) {
    commit('SET_FOLLOWINGS', followings)
  }


}



export default {
  state, getters, mutations, actions
}