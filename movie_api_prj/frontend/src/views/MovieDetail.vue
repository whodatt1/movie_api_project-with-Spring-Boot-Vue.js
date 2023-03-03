<template>
  <div class="movie-info mb-3">
    <div class="container">
        <div class="row">
          <div class="col-3">
            <img :src="'https://image.tmdb.org/t/p/original' + this.movie.posterPath" class="card-img-top">
          </div>
          <div class="col-9 description">
            <h3>{{ movie.title }}</h3>
            <div class="movie-realease mb-3">
              {{ movie.releaseDate }} (KR)
              <span v-for="(item, idx) in genreCd" :key="idx">
                {{ item.name }}<span v-if="idx != Object.keys(genreCd).length - 1">, </span>
              </span>
            </div>
            <h3>TMDB 회원 점수</h3>
            <div class="movie-realease mb-3">10 / {{ movie.voteAverage }}</div>
            <h3>개요</h3>
            <div class="movie-realease mb-3">{{ movie.overview }}</div>
            <button class="bookmark"><i class="fa-regular fa-heart"></i></button>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <h3>주요 출연진</h3>
      <div class="mb-3"></div>
      <h3>리뷰</h3>
      <div></div>
    </div>
</template>
<script>
import MovieService from '@/services/movie.service'
import GenreService from '@/services/genre.service'

export default {
  components: {},
  data() {
    return {
      id: '',
      movie: {},
      genreCd: []
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.id = this.$route.params.id
    this.getMovieDetail(this.id)
  },
  unmounted() { },
  methods: {
    getMovieDetail(id) {
      MovieService.getMovieDetail(id)
        .then((result) => {
          this.movie = result.data
          this.getGenreCd(result.data.genreIds)
        }).catch((err) => {
          console.log(err)
        })
    },
    getGenreCd(genreIds) {
      GenreService.getGenreCd(genreIds)
        .then((result) => {
          this.genreCd = result.data
        }).catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>
<style scoped>
.bookmark {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  border: none;
  background-color: bisque;
}
.movie-realease {
  font-size: 15px;
}
.description {
  padding: 3rem 0 3rem 0;
}
.movie-info{
  position: relative;
  padding: 3rem 0 3rem 0;
  background: no-repeat center center;
  background-color: #6c757d;
  background-size: cover;
  background-attachment: scroll;
  background-color: beige;
}
</style>
