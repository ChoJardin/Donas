const {configureWebpack} = require("vue-cli-plugin-vuetify/vue.config");


module.exports = {
  transpileDependencies: [
    'vuetify'
  ],

  devServer: {
    https: true,
  }
}