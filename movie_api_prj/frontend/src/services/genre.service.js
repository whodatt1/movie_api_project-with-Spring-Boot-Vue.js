import { instance } from './url'

class GenreService {
  getGenreCd(genreIds) {
    return instance.get(`/genre/cd/${genreIds}`)
  }

  getGenreCdAll() {
    return instance.get('/genre/cd/')
  }
}

export default new GenreService()
