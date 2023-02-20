import axios from 'axios'

class MovieService {
  getMovieForMainUpComing(adult) {
    return axios.get(`/movie/main/upcoming/${adult}`)
  }
}

export default new MovieService()
