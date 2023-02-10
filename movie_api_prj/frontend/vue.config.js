module.exports = {
  outputDir: '../src/main/resources/static',
  devServer: {
    proxy: {
      '/': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: {
          '^/': ''
        },
        ws: false
      }
    }
  }
}
