import { instance } from './url'

class MovieService {
  getMovieForMainUpComing(adult) {
    return instance.get(`/movie/main/upcoming/${adult}`)
  }

  getMovieForMainLatest(adult) {
    return instance.get(`/movie/main/latest/${adult}`)
  }

  getMovieForMainTop(adult) {
    return instance.get(`/movie/main/top/${adult}`)
  }

  getMovieForMainPopular(adult) {
    return instance.get(`/movie/main/popular/${adult}`)
  }

  getMovieDetail(id) {
    return instance.get(`/movie/detail/${id}`)
  }
}

export default new MovieService()
