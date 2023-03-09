import { instanceAuth, instance } from './url'

class CommentService {
  createComment(comment) {
    return instanceAuth.post('/comment/create', comment)
  }

  getCommentList(commId) {
    return instance.get(`/comment/pub/list/${commId}`)
  }

  deleteComment(delParam) {
    return instanceAuth.post('/comment/del', delParam)
  }
}

export default new CommentService()
