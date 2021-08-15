const {configureWebpack} = require("vue-cli-plugin-vuetify/vue.config");


module.exports = {
  transpileDependencies: [
    'vuetify'
  ],

  devServer: {
    disableHostCheck: true,
    port: 8080,
    public: '0.0.0.0:8080'
  },
  // publicPath: "/"
}