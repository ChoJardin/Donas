import cookies from 'vue-cookies'
import UserApi from "@/api/UserApi";
// import axios from 'axios'
// import router from '@/router'

const state ={
  loginUser: {
    id: '',
    nickname: '',
    picture: '',
    description: '',
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
  alarms:[{
    id : '',
    receiveId: '',
    contents: '',
    sendTime: '',
    confirm: '',
    articleId: '',
    sendName: '',
  }]
  // alarms: [{
  //   id: '1',
  //   receiver_id: '3',
  //   content: "ssafy님이 팔로우 했습니다.",
  //   sendTime: "2021-08-04T03:06:43",
  //   confirm: '1'
  // },
  //   {
  //     id: '2',
  //     receiver_id: '3',
  //     content: "admin님이 팔로우 했습니다.",
  //     sendTime: "2021-08-09T03:06:43",
  //     confirm: '0'
  //   }
  // ]
}


const getters = {
  isLoggedIn(state) {
    return !!state.loginUser.nickname
  }
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
    cookies.remove('login-user')
  },
  // 선택된 유저의 프로필 정보 저장
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

  SET_ALARMS(state, alarms) {
    state.alarms = alarms
  },
}


const actions = {
  // 로그인된 유저 정보 저장
  requestLoginUserProfile({dispatch}, {id}) {
    UserApi.requestLoginUser(
      id,
      res => {
        // 일치하는 유저 아이디 없음
        if (res.data === 'NOT_FOUND') {
          this.$router.push('/error')
        } else {
          // 정상적으로 유저 정보 가져온 경우
          res.data.id = id
          dispatch('setLoginUser', res.data)
        }
      },
      err => {
        console.log(err)
        // this.$router.push('/error')
      }
    )
  },
  // 로그인된 유저 정보 저장
  setLoginUser({commit}, profile) {
    commit('SET_LOGIN_USER', profile)
    cookies.set('login-user', profile, '2d')
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
  // 프로필 정보 수정
  // setLoginUser({commit}, profile) {
  //   commit('SET_LOGIN_USER', profile)
  // },

  // 알림 리스트
  setAlarms({commit}, alarms) {
    commit('SET_ALARMS', alarms)
  },

}



export default {
  state, getters, mutations, actions
}