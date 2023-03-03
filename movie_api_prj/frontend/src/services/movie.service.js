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

  getMovieListAll(pageNo) {
    return instance.get(`/movie/list/${pageNo}`)
  }

  getMovieListAllForResult(pageNo, genre, sortBy) {
    return instance.get(`/movie/list/${pageNo}/${genre}/${sortBy}`)
  }

  getMovieListAllForResultTitle(pageNo, title) {
    return instance.get(`/movie/list/${pageNo}/${title}`)
  }
}

export default new MovieService()
