import axios from 'axios'
import TokenService from './token.service'

class AuthService {
  login(user) {
    return axios
      .post('/auth/login', {
        userId: user.userId,
        userPw: user.userPw
      })
      .then((res) => {
        if (res.data.token) {
          TokenService.setUser(res.data)
        }

        return res.data
      })
  }

  logout() {
    TokenService.removeUser()
  }

  signup(user) {
    return axios
      .post('/auth/signup', {
        userId: user.userId,
        userNickNm: user.userNickNm,
        userPw: user.userPw,
        userEmail: user.userEmail,
        adult: user.adult
      })
  }
}

export default new AuthService()
