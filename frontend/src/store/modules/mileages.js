// import cookies from 'vue-cookies'
// import axios from 'axios'
// import router from '@/router'

import router from "../../router";

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
        tag2:'',
        bgPicture:'',
    }],

    selectedCharity: {
        id: 0,
        name: '',
        quarter: '',
        total: '',
        description: '',
        homepage: '',
        picture: '',
        tag: '',
        tag2:'',
        bgPicture:'',
    },

    charityId:0
}

const getters = {
    charityDetail: state => {
        // state.selectedCharity = state.charityList.find(charity => charity.id === id )
        return  state.charityList.find(charity => charity.id === state.charityId )
    }
}

const mutations = {
    // 메인 페이지 기부관련 초기 정보
    SET_MAIN_DONATION_INFO(state, donationInfo) {
        state.mainDonationInfo = donationInfo
    },
    SET_CHARITY_ID(state, charityId) {
        state.charityId = charityId
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

    setCharityId({commit}, charityId) {
        commit('SET_CHARITY_ID', charityId)
        router.push({name:'CharityDetail', params:{id:state.charityId}})
    },

    setCharityList({commit}, charityList) {
        console.log(charityList)
        commit('SET_CHARITY_LIST', charityList)
    },
}

export default {
    state, getters, mutations, actions
}