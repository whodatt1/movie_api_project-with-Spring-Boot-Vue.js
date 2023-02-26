import TokenService from './token.service'

export function setupInterceptors(instance) {
  instance.interceptors.request.use(
    (config) => {
      const accessToken = TokenService.getLocalAccessToken()
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
      if (err.response.status === 401) {
        instance.post('/auth/refreshtoken')
          .then((res) => {
            TokenService.updateLocalAccessToken(res.data.accessToken)
            return Promise.resolve(res)
          })
          .catch((err) => {
            return Promise.reject(err)
          })
      }
    }
  )
  return instance
}
