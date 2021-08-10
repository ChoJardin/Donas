import axios from "axios";
// import store from '@/store/modules/user'

// const URL = 'http://localhost:8081'
const URL = 'https://i5a603.p.ssafy.io:8081'
const ROUTES = {
  createPersonalQuest: '/quest/personal',
  createRelayQuest: '/quest/relay',
  createGroupQuest: '/quest/group',
  requestGroupMutuals:'/search/friends/'
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


const UserApi = {
  URL,
  ROUTES,
  createPersonalQuest:(data, callback, errorCallback) => createPersonalQuest(data,callback, errorCallback),
  createRelayQuest:(data, callback, errorCallback) => createRelayQuest(data,callback, errorCallback),
  createGroupQuest:(data, callback, errorCallback) => createGroupQuest(data,callback, errorCallback),
  requestGroupFriends:(id, callback, errorCallback) => requestGroupFriends(id,callback, errorCallback),


}



export default UserApi