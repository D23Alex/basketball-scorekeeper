import {createApp} from "vue";
import {createRouter, createWebHistory} from "vue-router";
import App from './App.vue'

import './assets/main.css'
import Teams from "@/components/Teams.vue";
import Auth from "@/components/Auth.vue";
import Welcome from "@/components/Welcome.vue";
import Error from "@/components/Error.vue";

const router = createRouter({
    history: createWebHistory(),
    routes : [
        {
            path: "/auth",
            name: "auth",
            component: Auth
        },
        {
            path: "/teams",
            name: "teams",
            component: Teams
        },
        {
            path: "/",
            name: "welcome",
            component: Welcome
        },
        {
            path: "/*",
            name: "error-page",
            component: Error,
            props: {
                errorCode: "404",
                errorMessage: "Not found"
            }
        }
    ]
})
createApp(App).use(router).mount('#app')
