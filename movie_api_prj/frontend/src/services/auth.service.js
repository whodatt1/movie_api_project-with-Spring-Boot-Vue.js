import TokenService from './token.service'
import { instance } from './url'

class AuthService {
  login(user) {
    return instance
      .post('/auth/login', {
        userId: user.userId,
        userPw: user.userPw
      })
      .then((res) => {
        if (res.data.accessToken) {
          TokenService.setUser(res.data)
        }

        return res.data
      })
  }

  logout() {
    return instance
      .post('/auth/logout')
      .then((res) => {
        TokenService.removeUser()
        return res.data
      })
  }

  signup(user) {
    return instance
      .post('/auth/signup', {
        userId: user.userId,
        userNickNm: user.userNickNm,
        userPw: user.userPw,
        userEmail: user.userEmail,
        adult: user.adult
      }).then((res) => {
        return res.data
      })
  }
}

export default new AuthService()
