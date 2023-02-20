import AuthService from '../services/auth.service'

const user = JSON.parse(localStorage.getItem('user'))
const initialState = user
  ? { status: { loggedIn: true }, user }
  : { status: { loggedIn: false }, user: null }

export const auth = {
  namespaced: true,
  state: initialState,
  actions: {
    login({ commit }, user) {
      return AuthService.login(user).then(
        user => {
          console.log(user)
          commit('loginSuccess', user)
          return Promise.resolve(user)
        },
        error => {
          commit('loginFailure')
          return Promise.reject(error)
        }
      )
    },
    logout({ commit }) {
      AuthService.logout()
      commit('logout')
    },
    signup({ commit }, user) {
      return AuthService.signup(user).then(
        reponse => {
          commit('signUpSuccess')
          return Promise.reject(reponse.data)
        },
        error => {
          commit('signUpFailure')
          return Promise.reject(error)
        }
      )
    },
    refreshToken({ commit }, token) {
      commit('refreshToken', token)
    }
  },
  mutations: {
    loginSuccess(state, user) {
      state.status.loggedIn = true
      state.user = user
    },
    loginFailure(state) {
      state.status.loggedIn = false
      state.user = null
    },
    logout(state) {
      state.status.loggedIn = false
      state.user = null
    },
    refreshToken(state, token) {
      state.status.loggedIn = true
      state.user = { ...state.user, token: token }
    },
    signUpSuccess(state) {
      state.status.loggedIn = false
    },
    signUpFailure(state) {
      state.status.loggedIn = false
    }
  }
}
