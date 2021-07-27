// import cookies from 'vue-cookies'
// import axios from 'axios'
// import router from '@/router'

const state ={
    // feeds: [{
    //     id: '',
    //     image: '',
    //     content: ''
    // }]
    // 테스트용
    feeds: [
      {
        id: '1',
        image: '',
        content: '123'
      },
      {
        id: '2',
        image: '',
        content: '456'
      },
      {
        id: '3',
        image: '',
        content: '789'
      },
    ],
    selectedArticle: {}
}

const getters = {
  // getArticle(state) {
  //   if (this.$route.)
  // }
}

const mutations = {
}

const actions = {
}

export default {
    state, getters, mutations, actions
}