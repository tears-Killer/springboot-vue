import Vue from 'vue'
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        user: {}
    },
    mutations: {
        getUser(store, payload) {
            store.user = payload
            console.log(store.user);

        }
    },
    getters: {

    },
    actions: {
      
    }
})