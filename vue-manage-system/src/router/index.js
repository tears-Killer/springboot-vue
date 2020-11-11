import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

export default new Router({
    mode:'history',
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/dashboard',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/shop',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/ShopTable.vue'),
                    meta: { title: '商品管理' }
                },
                {
                    path: '/order',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/OrderTable.vue'),
                    meta: { title: '订单管理' }
                },
                {
                    path: '/employee',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/EmployeeTable.vue'),
                    meta: { title: '员工管理' }
                },
                {
                    path: '/notice',
                    component: () => import(/* webpackChunkName: "tabs" */ '../components/page/Tabs.vue'),
                    meta: { title: '消息通知' }
                },
                {
                    path: '/personInfo',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/PersonInfo.vue'),
                    meta: { title: '个人信息' }
                },
                {
                    path: '/404',
                    component: () => import(/* webpackChunkName: "404" */ '../components/page/404.vue'),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    component: () => import(/* webpackChunkName: "403" */ '../components/page/403.vue'),
                    meta: { title: '403' }
                }
            ]
        },
        {
            path: '/login',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: { title: '登录' }
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
});
