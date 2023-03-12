const Dotenv = require('dotenv-webpack')

module.exports = {
  outputDir: '../src/main/resources/static',
  // devServer: {
  //   proxy: {
  //     '/': {
  //       target: 'http://localhost:8080',
  //       changeOrigin: true,
  //       pathRewrite: {
  //         '^/': ''
  //       },
  //       ws: false
  //     }
  //   }
  // }
  devServer: {
    proxy: 'http://localhost:8080'
  },
  chainWebpack: config => {
    const svgRule = config.module.rule('svg')
    svgRule.uses.clear()
    svgRule.use('vue-svg-loader').loader('vue-svg-loader')
  },
  configureWebpack: {
    plugins: [
      new Dotenv()
    ]
  }
}
