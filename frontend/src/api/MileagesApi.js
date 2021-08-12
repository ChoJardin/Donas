import axios from "axios";
// import store from '@/store/modules/user'

// const URL = 'http://localhost:8081'
const URL = 'https://i5a603.p.ssafy.io:8081'
const ROUTES = {
  createCashOutRequest: '/mileage/cash',
  createDonationRequest: '/mileage/donation',
  requestCharityList: '/charity',
}


// 출금하기
const createCashOutRequest = (data, callback, errorCallback) => {
  const CashOutRequestPath = URL + ROUTES.createCashOutRequest
  axios.post(CashOutRequestPath, data)
    .then(res => {
      console.log(res)
      callback(res.data)
    })
    .catch(error => {
      errorCallback(error.response.data)
    })
}

const createDonationRequest = (data, callback, errorCallback) => {
  const DonationRequestPath = URL + ROUTES.createDonationRequest
  axios.post(DonationRequestPath, data)
    .then(res => {
      console.log(res)
      callback(res.data)
    })
    .catch(error => {
      errorCallback(error.response.data)
    })
}

const requestCharityList = async () => {
  const requestCharityListPath = URL + ROUTES.requestCharityList
  let result
  result = axios.get(requestCharityListPath)
    .then(res => res.data)
    .catch(err => 'error')
  return result
}


const UserApi = {
  URL,
  ROUTES,
  createCashOutRequest: (data, callback, errorCallback) => createCashOutRequest(data, callback, errorCallback),
  createDonationRequest: (data, callback, errorCallback) => createDonationRequest(data, callback, errorCallback),
  requestCharityList:() => requestCharityList()

}



export default UserApi