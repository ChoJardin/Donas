import Vue from 'vue'
import VueRouter from 'vue-router'

import Main from "@/views/Main";

import Quest from "@/views/quests/Quest";
import CreateQuest from "@/views/quests/CreateQuest";
import SoloQuest from "@/components/quests/SoloQuest"
import RelayQuest from "@/components/quests/RelayQuest"
import GroupQuest from "@/components/quests/GroupQuest"
import AllQuest from "@/components/quests/AllQuest";
import QuestDetail from "@/views/quests/QuestDetail";

import Feed from "@/views/articles/Feed";
import VerticalFeed from "@/views/articles/VerticalFeed";

import Mileage from "@/views/mileages/Mileage";
import Donation from "@/components/mileages/Donation";
import CashOut from "@/components/mileages/CashOut";

import Profile from "@/views/user/Profile";
import Login from "@/views/user/Login";
import Signup from "@/views/user/Signup";
import Alert from "@/views/user/Alert";
import AlertAll from "@/components/accounts/AlertAll";
import MessageAll from "@/components/accounts/MessageAll";

import Search from "@/views/common/Search";



Vue.use(VueRouter)

const routes = [
  // 메인
  {path: '/', name: 'Main', component: Main},

  // 전체 퀘스트
  {path: '/quests', component: Quest,
    children: [
      {path: '', name: 'AllQuest', component: AllQuest},
      {path: 'solo', name: 'SoloQuest', component: SoloQuest},
      {path: 'group', name: 'GroupQuest', component: GroupQuest},
      {path: 'relay', name: 'RelayQuest', component: RelayQuest},
    ]
  },
  //퀘스트 생성
  {path: '/quests/create', name: 'CreateQuest', component: CreateQuest},
  //퀘스트 상세
  {path: '/quests/:questId', name: 'QuestDetail', component: QuestDetail},

  // 피드
  {path: '/feed', name: 'Feed', component: Feed},
  {path: '/feed/v', name: 'VerticalFeed', component: VerticalFeed},

  // 마일리지
  {
    path: '/user/mileage', component: Mileage,
    children: [
      {path: '', name: 'Donation', component: Donation},
      {path: 'cashout', name: 'CashOut', component: CashOut}
    ]
  },

  // 프로필
  {path: '/user/profile/:nickname', name: 'Profile', component: Profile},
  // 로그인
  {path: '/login', name: 'Login', component: Login},
  // 회원가입
  {path: '/signup', name: 'Signup', component: Signup},
  // 알림
  {path: '/notification/:nickname', component: Alert,
  children: [
      {path: '', name: 'Alert', component: AlertAll},
      {path: 'messages', name: 'Messages', component: MessageAll}
    ]
  },

  //검색
  {path: '/search', name: 'Search', component: Search},

]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  // 새 페이지 로딩할 때 항상 스크롤 맨 위로
  // scrollBehavior(to, from) {
  //   return {x: 0, y:0}
  // }
})

export default router
