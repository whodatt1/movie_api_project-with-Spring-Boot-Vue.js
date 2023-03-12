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

  getMovieListAll(pageNo, adult) {
    return instance.get(`/movie/list/${pageNo}/${adult}`)
  }

  getMovieListAllForResult(pageNo, genre, sortBy, adult) {
    return instance.get(`/movie/list/${pageNo}/${genre}/${sortBy}/${adult}`)
  }

  getMovieListAllForResultTitle(pageNo, title, adult) {
    return instance.get(`/movie/list/${pageNo}/${title}/${adult}`)
  }

  getCreditForMovieId(movieId) {
    return instance.get(`https://api.themoviedb.org/3/movie/${movieId}/credits?api_key=${process.env.VUE_APP_TMDB}`)
  }
}

export default new MovieService()
