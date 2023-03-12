<template>
  <div class="container" style="margin-top: 3rem;">
    <div>
      <h4 class="movie-card-header">개봉 예정작</h4>
    </div>
    <div>
      <div class="scroll-row mb-5">
        <div class="scroll-card" v-for="(item, idx) in upComingMovies" :key="idx">
          <router-link v-bind:to="`/moviedetail/${item.id}`"><img :src="'https://image.tmdb.org/t/p/original' + item.posterPath" class="card-img-top"></router-link>
          <div class="card-body">
            <h5 class="card-title">{{ item.title }}</h5>
            <p class="card-text"><small class="text-muted">{{ item.releaseDate }}</small></p>
          </div>
        </div>
      </div>
    </div>
    <div>
      <h4 class="movie-card-header">최신 개봉 영화</h4>
    </div>
    <div>
      <div class="scroll-row mb-5">
        <div class="scroll-card" v-for="(item, idx) in latestMovies" :key="idx">
          <router-link v-bind:to="`/moviedetail/${item.id}`"><img :src="'https://image.tmdb.org/t/p/original' + item.posterPath" class="card-img-top"></router-link>
          <div class="card-body">
            <h5 class="card-title">{{ item.title }}</h5>
            <p class="card-text"><small class="text-muted">{{ item.releaseDate }}</small></p>
          </div>
        </div>
      </div>
    </div>
    <div>
      <h4 class="movie-card-header">인기 영화</h4>
    </div>
    <div>
      <div class="scroll-row mb-5">
        <div class="scroll-card" v-for="(item, idx) in popularMovies" :key="idx">
          <router-link v-bind:to="`/moviedetail/${item.id}`"><img :src="'https://image.tmdb.org/t/p/original' + item.posterPath" class="card-img-top"></router-link>
          <div class="card-body">
            <h5 class="card-title">{{ item.title }}</h5>
            <p class="card-text"><small class="text-muted">{{ item.releaseDate }}</small></p>
          </div>
        </div>
      </div>
    </div>
    <div>
      <h4 class="movie-card-header">평점 순 영화</h4>
    </div>
    <div>
      <div class="scroll-row mb-5">
        <div class="scroll-card" v-for="(item, idx) in topMovies" :key="idx">
          <router-link v-bind:to="`/moviedetail/${item.id}`"><img :src="'https://image.tmdb.org/t/p/original' + item.posterPath" class="card-img-top"></router-link>
          <div class="card-body">
            <h5 class="card-title">{{ item.title }}</h5>
            <p class="card-text"><small class="text-muted">{{ item.releaseDate }}</small></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import MovieService from '@/services/movie.service'

export default {
  components: {},
  data() {
    return {
      upComingMovies: [],
      latestMovies: [],
      topMovies: [],
      popularMovies: []
    }
  },
  setup() { },
  created() { },
  computed: {
    auth() {
      return this.$store.state.auth
    }
  },
  mounted() {
    let adult = false

    if (this.auth.user) {
      adult = this.auth.user.adult
    }
    this.getMovieForMainUpComing(adult)
    this.getMovieForMainLatest(adult)
    this.getMovieForMainTop(adult)
    this.getMovieForMainPopular(adult)
  },
  unmounted() { },
  methods: {
    getMovieForMainUpComing(adult) {
      MovieService.getMovieForMainUpComing(adult)
        .then((result) => {
          this.upComingMovies = result.data
        }).catch((err) => {
          console.log(err)
        })
    },

    getMovieForMainLatest(adult) {
      MovieService.getMovieForMainLatest(adult)
        .then((result) => {
          this.latestMovies = result.data
        }).catch((err) => {
          console.log(err)
        })
    },

    getMovieForMainTop(adult) {
      MovieService.getMovieForMainTop(adult)
        .then((result) => {
          this.topMovies = result.data
        }).catch((err) => {
          console.log(err)
        })
    },

    getMovieForMainPopular(adult) {
      MovieService.getMovieForMainPopular(adult)
        .then((result) => {
          this.popularMovies = result.data
        }).catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>
<style scoped>
.movie-card-header {
  text-align: left;
  margin-left: 20px;
}
.scroll-row {
  align-items: stretch;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  overflow-x: auto;
  overflow-y: hidden;
}

.scroll-card {
  max-width: 22.222%;
  padding: .75rem;
  border: 0;
  flex-basis: 22.222%;
  flex-grow: 0;
  flex-shrink: 0;
}

.scroll-row::-webkit-scrollbar {
  width: 6px;
}

.scroll-row::-webkit-scrollbar-thumb {
  height: 17%;
  background-color: black;
  border-radius: 10px;
}

.scroll-row::-webkit-scrollbar-track {
  border-radius: 10px;
  background-color: gray;
}
</style>
