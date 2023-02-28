import { instance } from './url'

class GenreService {
  getGenreCd() {
    return instance.get('/genre/cd')
  }
}

export default new GenreService()
