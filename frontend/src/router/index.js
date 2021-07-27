import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from "@/views/user/Login";
import Signup from "@/views/user/Signup";
import Main from "@/views/Main";
import Quest from "@/views/quests/Quest";
import CreateQuest from "@/views/quests/CreateQuest";
import QuestDetail from "@/views/quests/QuestDetail";
import Feed from "@/views/articles/Feed";
import Mileage from "@/views/mileages/Mileage";
import Profile from "@/views/user/Profile";

import Donation from "@/components/mileages/Donation";
import CashOut from "@/components/mileages/CashOut";

import SoloQuest from "@/components/quests/SoloQuest"
import RelayQuest from "@/components/quests/RelayQuest"
import GroupQuest from "@/components/quests/GroupQuest"
import AllQuest from "@/components/quests/AllQuest";


Vue.use(VueRouter)

const routes = [
  // 로그인
  {path: '/login', name: 'Login', component: Login},
  // 회원가입
  {path: '/signup', name: 'Signup', component: Signup},

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
  {path: '/quests/create', name: 'CreateQuest', component: CreateQuest},
  {path: '/quests/:questID', name: 'QuestDetail', component: QuestDetail},

  // 피드
  {path: '/user/feed', name: 'Feed', component: Feed},
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
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
