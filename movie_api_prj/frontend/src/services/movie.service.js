import axios from 'axios'

class MovieService {
  getMovieForMainUpComing(adult) {
    return axios.get(`/movie/main/upcoming/${adult}`)
  }

  getMovieForMainLatest(adult) {
    return axios.get(`/movie/main/latest/${adult}`)
  }

  getMovieForMainTop(adult) {
    return axios.get(`/movie/main/top/${adult}`)
  }

  getMovieForMainPopular(adult) {
    return axios.get(`/movie/main/popular/${adult}`)
  }
}

export default new MovieService()
