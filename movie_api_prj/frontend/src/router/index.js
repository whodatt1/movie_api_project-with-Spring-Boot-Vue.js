import { createRouter, createWebHistory } from 'vue-router'
import CommunityVue from '../views/Community.vue'
import LoginVue from '../views/Login.vue'
import Main from '../views/Main.vue'
import MovieVue from '../views/Movie.vue'
import SignUpVue from '../views/SignUp.vue'

const routes = [
  {
    path: '/',
    name: 'main',
    component: Main,
    meta: { requiredAuth: true }
  },
  {
    path: '/login',
    name: 'login',
    component: LoginVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/signup',
    name: 'signup',
    component: SignUpVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/movie',
    name: 'movie',
    component: MovieVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/community',
    name: 'community',
    component: CommunityVue,
    meta: { requiredAuth: false }
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
