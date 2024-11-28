import {createApp} from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

import ElementPlus, {ElMessage} from 'element-plus';
import 'element-plus/dist/index.css';
import axios from "axios";

const app = createApp(App);

app.use(ElementPlus).use(store).use(router).mount('#app');

//

window.axios = axios.create({
    baseURL: "/api", //
});

window.axios.interceptors.request.use(config => {
    config.withCredentials = true;
    return config;
});

const errorCode = {
    2001: msg => ElMessage.error(msg), //
};

const httpErrorCode = {
    404: () => ElMessage.error("接口不存在"), //
    401: () => ElMessage.error("未授权访问"), //
    500: () => ElMessage.error("服务器错误"), //
}

window.axios.interceptors.response.use( //
    res => {
        const {code} = res.data;
        if (code === 1001) return res.data.data;
        errorCode[code]?.(res.data.data);
        return Promise.reject(res);
    }, //
    err => {
        const {status} = err;
        httpErrorCode[status]?.();
        return Promise.reject(err);
    });

//
