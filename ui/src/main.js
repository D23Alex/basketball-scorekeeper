import {createApp} from "vue";
import {createRouter, createWebHashHistory, createWebHistory} from "vue-router";
import App from './App.vue'

import './assets/main.css'
import Teams from "@/components/league/Teams.vue";
import Auth from "@/components/Auth.vue";
import Welcome from "@/components/Welcome.vue";
import Error from "@/components/Error.vue";
import Game from "@/components/game/Game.vue";
import PlayerPage from "@/components/player/PlayerPage.vue";
import TeamPage from "@/components/league/TeamPage.vue";
import Calendar from "@/components/Calendar.vue";

const router = createRouter({
    history: createWebHashHistory('/basketball-scorekeeper/'),
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
            path: "/team/:teamId",
            name: "team",
            component: TeamPage,
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
            path: "/calendar",
            name: "calendar",
            component: Calendar
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
