import axios from "axios";
// import store from '@/store/modules/user'

// const URL = 'http://localhost:8081'
const URL = 'https://i5a603.p.ssafy.io:8081'
const ROUTES = {
  createCashOutRequest: '/mileage/cash',
  createDonationRequest: '/mileage/donation',
  requestCharityList: '/charity',
  MileageUsageList: '/user/mileage',
}


// 출금하기
const createCashOutRequest = (data, callback, errorCallback) => {
  const CashOutRequestPath = URL + ROUTES.createCashOutRequest
  axios.post(CashOutRequestPath, data)
    .then(res => {
      // console.log(res)
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
      // console.log(res)
      callback(res.data)
    })
    .catch(error => {
      errorCallback(error.response.data)
    })
}

// const requestCharityList = async () => {
//   const requestCharityListPath = URL + ROUTES.requestCharityList
//   let result
//   result = axios.get(requestCharityListPath)
//     .then(res => res.data)
//     .catch(err => 'error')
//   return result
// }

const requestCharityList = (callback, errorCallback) => {
  const requestCharityListPath = URL + ROUTES.requestCharityList
    axios.get(requestCharityListPath)
      .then(res => callback(res.data))
      .catch(error => errorCallback(error))
}

const requestMileageUsage = (data, callback, errorCallback) => {
  const requestMileagePath = URL + ROUTES.MileageUsageList
  axios.get(requestMileagePath, {params: data})
    .then(res => callback(res.data))
    .catch(res=> callback(res.data))
}

const UserApi = {
  URL,
  ROUTES,
  createCashOutRequest: (data, callback, errorCallback) => createCashOutRequest(data, callback, errorCallback),
  createDonationRequest: (data, callback, errorCallback) => createDonationRequest(data, callback, errorCallback),
  requestCharityList: (callback, errorCallback) => requestCharityList(callback, errorCallback),
  requestMileageUsage: (data, callback, errorCallback) => requestMileageUsage(data, callback,errorCallback),

}



export default UserApi