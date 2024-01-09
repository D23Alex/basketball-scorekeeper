<script>
import axios from "axios";
import BoxScore from "@/components/game/BoxScore.vue";
import TeamPreview from "@/components/league/TeamPreview.vue";
import {API} from "@/constants";
import GamePreview from "@/components/game/GamePreview.vue";


export default {
  components: {GamePreview, TeamPreview, BoxScore},
  data() {
    return {
      allLoaded: false,
      game: null,
      season: 2023, //TODO: season is hardcoded atm, deal with it later

      team1Lineup: [],
      team2Lineup: [],
      team1Performance: null,
      team2Performance: null,

      gameEventLog: {
        game: null,
        periodStarts: [],
        periodEndings: [],
        lineupOccurrences: [],
        periodStarters: [],
        periodEnders: [],
        afterTimeoutCourtAppearances: [],
        afterTimeoutCourtExits: [],
        coachEjections: [],
        coachTechnicalFouls: [],
        fieldGoalAttempts: [],
        freeThrowAttempts: [],
        personalFouls: [],
        playerEjections: [],
        playerTechnicalFouls: [],
        substitutionCalls: [],
        substitutionIns: [],
        substitutionOuts: [],
        timeouts: [],
        turnovers: []
      }
    }
  },

  async mounted() {
    this.gameEventLog = (await axios.get(API + "/events/game/" + this.$route.params.gameId)).data;
    this.game = (await axios.get(API + "/games/" + this.$route.params.gameId)).data;
    this.team1Lineup = this.gameEventLog.lineupOccurrences
        .filter(occurrence => occurrence.team.id === this.game.team1.id)
        .map(occurrence => occurrence.player);
    this.team2Lineup = this.gameEventLog.lineupOccurrences
        .filter(occurrence => occurrence.team.id === this.game.team2.id)
        .map(occurrence => occurrence.player);
    this.team1Performance = (await axios.get(API + "/stats/team-single-game-performance/"
        + this.game.team1.id + "/" + this.$route.params.gameId)).data.performance;
    console.log(this.team1Performance);
    this.team2Performance = (await axios.get(API + "/stats/team-single-game-performance/"
        + this.game.team2.id + "/" + this.$route.params.gameId)).data.performance;
    this.allLoaded = true;
  }
}
</script>

<template>
  <div>Вся информация о конкретной игре. Тут будут располагаться стартовые составы, box score, графики и т.п.</div>
  <template v-if="allLoaded">
    <GamePreview :season="this.season" :team1-score="this.team1Performance.totals.points"
                 :team2-score="this.team2Performance.totals.points" :game-status="'TODO: статус игры'"
                 :team1-info="this.game.team1" :team2-info="this.game.team2"
                 :game-scheduled-start="this.game.scheduledStartTime"
                 :game-id="this.game.id"/>
    <BoxScore/>
    <div>//TODO: Ивент лог игры ЗДЕСЬ</div>
  </template>

</template>

<style scoped>

</style>