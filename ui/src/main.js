import {createApp} from "vue";
import {createRouter, createWebHistory} from "vue-router";
import App from './App.vue'

import './assets/main.css'
import Teams from "@/components/league/Teams.vue";
import Auth from "@/components/Auth.vue";
import Welcome from "@/components/Welcome.vue";
import Error from "@/components/Error.vue";
import Game from "@/components/game/Game.vue";
import PlayerPage from "@/components/player/PlayerPage.vue";

const router = createRouter({
    history: createWebHistory('/basketball-scorekeeper/'),
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
            path: "/game/:gameId",
            name: "game",
            component: Game,
        },
        {
            path: "/player/:playerId",
            name: "player",
            component: PlayerPage,
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
