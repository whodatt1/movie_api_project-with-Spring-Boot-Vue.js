import { instanceAuth, instance } from './url'

class BookmarkService {
  createBookmark(param) {
    return instanceAuth.post('/bookmark/create', param)
  }

  deleteBookmark(param) {
    return instanceAuth.post('/bookmark/delete', param)
  }

  getIsBookmark(params) {
    return instance.get('/bookmark/is', {
      params: {
        movieId: params.movieId,
        userId: params.userId
      }
    })
  }

  getBookmarkListByUserId(userId, pageNo) {
    return instanceAuth.get(`/bookmark/list/${userId}/${pageNo}`)
  }
}

export default new BookmarkService()
