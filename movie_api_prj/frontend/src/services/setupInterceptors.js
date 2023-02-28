import TokenService from './token.service'
import store from '@/store'
import router from '@/router'

export function setupInterceptors(instance) {
  instance.interceptors.request.use(
    (config) => {
      const accessToken = TokenService.getLocalAccessToken()
      console.log(accessToken)
      if (accessToken) {
        config.headers.Authorization = 'Bearer ' + accessToken
      }
      return config
    },
    (error) => {
      return Promise.reject(error)
    }
  )

  instance.interceptors.response.use(
    (res) => {
      return res
    },
    async (err) => {
      const originalConfig = err.config

      if (err.response) {
        if (err.response.status === 401 && !originalConfig._retry) {
          originalConfig._retry = true

          const { data } = await instance.post('/auth/refreshtoken')

          if (data) {
            if (data.accessToken) {
              TokenService.updateLocalAccessToken(data.accessToken)
              originalConfig.headers.Authorization = 'Bearer ' + data.accessToken
            }

            if (data.statusCode === 403) {
              store.dispatch('auth/logout').then(() => {
                alert('권한이 만료되었습니다. 다시 로그인 해주세요.')
                router.push({ path: '/login' })
              },
              (error) => {
                console.log(error)
              })
            }
          }

          return instance(originalConfig)
        }
        return Promise.reject(err)
      }
    })
  return instance
}
