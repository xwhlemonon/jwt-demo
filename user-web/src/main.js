import {createApp} from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import axios from "axios";

const app = createApp(App);

//

window.axios = axios.create({
    baseURL: "/api", //
    withCredentials: true, //
});

window.axios.interceptors.request.use((config) => {
    config.withCredentials = true;
    return config;
}, (error) => {
    return Promise.reject(error);
});

//

app.use(ElementPlus).use(store).use(router).mount('#app');
