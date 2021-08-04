import cookies from 'vue-cookies'
// import axios from 'axios'
// import router from '@/router'

const state ={
  loginUser: '',
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
    followerList: [{}, {}, {}],
    followingList: '',
  },

  // 개인정보 수정
  myProfile: {
    nickname: '',
    picture: '',
    description: '',
  }

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
  // 프로필 정보 수정
  SET_MY_PROFILE(state, profile) {
    state.myProfile = profile
  }

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
  // 프로필 정보 수정
  setMyProfile({commit}, profile) {
    commit('SET_MY_PROFILE', profile)
  }

}



export default {
  state, getters, mutations, actions
}