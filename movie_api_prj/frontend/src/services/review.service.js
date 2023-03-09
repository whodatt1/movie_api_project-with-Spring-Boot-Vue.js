import { instanceAuth, instance } from './url'

class ReviewService {
  createReivew(review) {
    return instanceAuth.post('/review/create', review)
  }

  getReviewList(movieId) {
    return instance.get(`/review/pub/list/${movieId}`)
  }

  deleteReview(delParam) {
    return instanceAuth.post('/review/del', delParam)
  }

  getRatingsAverage(movieId) {
    return instance.get(`/review/pub/average/${movieId}`)
  }
}

export default new ReviewService()
