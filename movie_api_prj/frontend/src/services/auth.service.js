import TokenService from './token.service'
import { instance, instanceAuth } from './url'

class AuthService {
  login(user) {
    return instance
      .post('/auth/pub/login', {
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
    return instanceAuth
      .post('/auth/logout')
      .then((res) => {
        TokenService.removeUser()
        return res.data
      })
  }

  signup(user) {
    return instance
      .post('/auth/pub/signup', {
        userId: user.userId,
        userNickNm: user.userNickNm,
        userPw: user.userPw,
        userEmail: user.userEmail,
        adult: user.adult
      }).then((res) => {
        return res.data
      })
  }

  update(user) {
    return instanceAuth
      .post('/auth/update', {
        userId: user.userId,
        userNickNm: user.userNickNm,
        userPw: user.userPw,
        userEmail: user.userEmail,
        adult: user.adult
      })
  }

  delete(user) {
    return instanceAuth
      .post('/auth/delete', {
        userId: user.userId,
        userPw: user.userPw
      })
  }

  getUserList() {
    return instanceAuth
      .get('/auth/list')
  }
}

export default new AuthService()
