import TokenService from './token.service'
import axios from 'axios'

const setup = (store) => {
  axios.interceptors.request.use(
    (config) => {
      const token = TokenService.getLocalAccessToken()
      if (token) {
        config.headers.Authorization = 'Bearer ' + token
      }
      return config
    },
    (error) => {
      return Promise.reject(error)
    }
  )

  axios.interceptors.response.use(
    (res) => {
      return res
    },
    async (err) => {
      const originalConfig = err.config

      if (originalConfig.url !== '/auth/login' && err.response) {
        // 액세스토큰이 만료됐을 때
        if (err.response.status === 401 && !originalConfig._retry) {
          originalConfig._retry = true

          try {
            const rs = await axios.post('/auth/refreshtoken', {
              refreshToken: TokenService.getLocalRefreshToken()
            })

            const { token } = rs.data

            store.dispatch('auth/refreshToken', token)
            TokenService.updateLocalAccessToken(token)

            return axios(originalConfig)
          } catch (_error) {
            return Promise.reject(_error)
          }
        }
      }

      return Promise.reject(err)
    }
  )
}

export default setup
