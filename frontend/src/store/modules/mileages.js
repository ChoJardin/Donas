// import cookies from 'vue-cookies'
// import axios from 'axios'
// import router from '@/router'

const state ={
    // 메인 페이지 기부관련 초기 정보
    mainDonationInfo: {
        donation: 0,
        quarter: '',
    },

    charityList: [{
        id: 0,
        name: '',
        quarter: '',
        total: '',
        description: '',
        homepage: '',
        picture: '',
        tag: '',
    }]
}

const getters = {
}

const mutations = {
    // 메인 페이지 기부관련 초기 정보
    SET_MAIN_DONATION_INFO(state, donationInfo) {
        state.mainDonationInfo = donationInfo
    },

    SET_CHARITY_LIST(state, charityList) {
        state.charityList = charityList
    }
}

const actions = {
    // 메인 페이지 기부관련 초기 정보
    setMainDonationInfo({commit}, donationInfo) {
        commit('SET_MAIN_DONATION_INFO', donationInfo)
    },

    setCharityList({commit}, charityList) {
        commit('SET_CHARITY_LIST', charityList)
    },
}

export default {
    state, getters, mutations, actions
}