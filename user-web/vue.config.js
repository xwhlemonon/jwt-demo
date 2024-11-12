const {defineConfig} = require('@vue/cli-service')

module.exports = defineConfig({
    transpileDependencies: true, devServer: {
        webSocketServer: false, //
        proxy: {
            '^/api': {
                target: 'http://localhost:8080', changeOrigin: true, //
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
        // , client: {
        //     overlay: false
        // }
    }
})
