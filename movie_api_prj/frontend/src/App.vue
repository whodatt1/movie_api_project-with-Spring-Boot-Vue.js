<template>
  <Header/>
  <router-view/>
  <Footer/>
</template>

<script>
import '../src/js/scripts.js'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import { useRoute } from 'vue-router'
import { watch } from '@vue/runtime-core'

export default {
  components: { Header, Footer },
  data() {
    return {
      sampleData: ''
    }
  },
  setup() {
    const route = useRoute()

    watch(route, () => {
    })
  },
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
}
</script>

<style>
@import '../src/css/styles.css';

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

</style>
