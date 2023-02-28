<template>
  <div>Movie{{ id }}{{ movie }}</div>
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
    this.getGenreCd()
  },
  unmounted() {},
  methods: {
    getMovieDetail(id) {
      MovieService.getMovieDetail(id)
        .then((result) => {
          this.movie = result.data
        }).catch((err) => {
          console.log(err)
        })
    },
    getGenreCd() {
      GenreService.getGenreCd()
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
</style>
