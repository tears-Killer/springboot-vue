import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
import './assets/css/icon.css';
import './components/common/directives';
import 'babel-polyfill';
import axios from 'axios'
import qs from 'qs'
import echarts from 'echarts'

axios.defaults.baseURL= 'http://localhost:8080/'
Vue.prototype.$axios = axios
Vue.prototype.$qs = qs;
Vue.prototype.$echarts = echarts;
Vue.config.productionTip = false;
Vue.use(ElementUI, {
    size: 'small'
});


//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    document.title = `${to.meta.title}`;
    next()
});

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
