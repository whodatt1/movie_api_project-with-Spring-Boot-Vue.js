import { createRouter, createWebHistory } from 'vue-router'
import CommunityVue from '../views/Community.vue'
import LoginVue from '../views/Login.vue'
import Main from '../views/Main.vue'
import MovieVue from '../views/Movie.vue'
import SignUpVue from '../views/SignUp.vue'
import MovieDetailVue from '../views/MovieDetail.vue'
import IconShopVue from '../views/IconShop.vue'

const routes = [
  {
    path: '/',
    name: 'main',
    component: Main
  },
  {
    path: '/login',
    name: 'login',
    component: LoginVue
  },
  {
    path: '/signup',
    name: 'signup',
    component: SignUpVue
  },
  {
    path: '/movie',
    name: 'movie',
    component: MovieVue
  },
  {
    path: '/moviedetail/:id',
    name: 'moviedetail',
    component: MovieDetailVue
  },
  {
    path: '/community',
    name: 'community',
    component: CommunityVue
  },
  {
    path: '/iconshop',
    name: 'iconshop',
    component: IconShopVue
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
