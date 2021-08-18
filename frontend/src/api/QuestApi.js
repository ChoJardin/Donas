import axios from "axios";
// import store from '@/store/modules/user'

// const URL = 'http://localhost:8081'
const URL = 'https://i5a603.p.ssafy.io:8081'
const ROUTES = {
  createPersonalQuest: '/quest/personal',
  createRelayQuest: '/quest/relay',
  createGroupQuest: '/quest/group',
  requestGroupMutuals:'/search/friends/',
  requestAllQuest: '/quest',
  requestQuestDetail: '/quest/detail',
  myQuests: '/quest/participant',
  participate: '/quest/personal/participant',
  nextRelay: '/quest/relay/next'
}


// 개인 퀘스트 생성
const createPersonalQuest = (data, callback, errorCallback) => {
  const createPersonalPath = URL + ROUTES.createPersonalQuest
  axios.post(createPersonalPath, data)
    .then(res => {
      console.log(res)
      callback(res.data)
    })
    .catch(error => {
      errorCallback(error.response.data)
    })
}
// 릴레이 퀘스트 생성
const createRelayQuest = (data, callback, errorCallback) => {
  const createRelayPath = URL + ROUTES.createRelayQuest
  axios.post(createRelayPath, data)
    .then(res => {
      console.log(res)
      callback(res.data)
    })
    .catch(error => {
      errorCallback(error.response.data)
    })
}

const createGroupQuest = (data, callback, errorCallback) => {
  const createGroupPath = URL + ROUTES.createGroupQuest
  axios.post(createGroupPath, data)
    .then(res => {
      console.log(res)
      callback(res.data)
    })
    .catch(error => {
      errorCallback(error.response.data)
    })
}

const requestGroupFriends = (id, callback, errorCallback) => {
  const MutualListPath = URL + ROUTES.requestGroupMutuals + id
  axios.get(MutualListPath)
    .then(res => {
      // console.log(res)
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })

}

const requestAllQuest = (callback, errorCallback) => {
  const requestAllQuestPath = URL + ROUTES.requestAllQuest
  axios.get(requestAllQuestPath)
    .then(res =>{
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}

const requestQuestDetail = (data, callback, errorCallback) => {
  const requestQuestDetailPath = URL + ROUTES.requestQuestDetail
  axios.get(requestQuestDetailPath, {params: data})
    .then(res => {
      callback(res)
    })
    .catch(err => {
      errorCallback(err)
    })
}

// 개인 퀘스트 요청
// c === 완료, p === 진행, b === 예정
const requestMyQuests = (data, callback, errorCallback) => {
  const requestMyQuestsPath = URL + ROUTES.myQuests
  axios.get(requestMyQuestsPath, {params: data})
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

//개인 퀘스트 참여
const participate = (data, callback, errorCallback) => {
  const participatePath = URL + ROUTES.participate
  axios.post(participatePath, data)
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

const requestNextRelay = async (data, callback, errorCallback) => {
  const requestNextRelayPath = URL + ROUTES.nextRelay
  axios.post(requestNextRelayPath, data)
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}


const UserApi = {
  URL,
  ROUTES,
  createPersonalQuest:(data, callback, errorCallback) => createPersonalQuest(data,callback, errorCallback),
  createRelayQuest:(data, callback, errorCallback) => createRelayQuest(data,callback, errorCallback),
  createGroupQuest:(data, callback, errorCallback) => createGroupQuest(data,callback, errorCallback),
  requestGroupFriends:(id, callback, errorCallback) => requestGroupFriends(id,callback, errorCallback),
  requestAllQuest:(callback, errorCallback) => requestAllQuest(callback, errorCallback),
  requestQuestDetail:(data,callback, errorCallback) => requestQuestDetail(data,callback, errorCallback),
  requestMyQuests:(data,callback, errorCallback) => requestMyQuests(data,callback, errorCallback),
  participate:(data,callback, errorCallback) => participate(data,callback, errorCallback),
  requestNextRelay:(data,callback, errorCallback) => requestNextRelay(data,callback, errorCallback),


}



export default UserApi