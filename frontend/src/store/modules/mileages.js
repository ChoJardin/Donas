// import cookies from 'vue-cookies'
// import axios from 'axios'
// import router from '@/router'

const state ={
    // 메인 페이지 기부관련 초기 정보
    mainDonationInfo: {
        donation: 0,
        quarter: '',
    }
}

const getters = {
}

const mutations = {
    // 메인 페이지 기부관련 초기 정보
    SET_MAIN_DONATION_INFO(state, donationInfo) {
        state.mainDonationInfo = donationInfo
    }
}

const actions = {
    // 메인 페이지 기부관련 초기 정보
    setMainDonationInfo({commit}, donationInfo) {
        commit('SET_MAIN_DONATION_INFO', donationInfo)
    }
}

export default {
    state, getters, mutations, actions
}