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
  <template v-if="allLoaded">
    <div class="outer">
      <div class="game-container">
      <TeamPreview :season="this.season" :team-id="this.game.team1.id"
                   :team-city="this.game.team1.city" :team-name="this.game.team1.name"/>
      <div class="score-container">
        {{ this.team1Performance.totals.points }} - {{ this.team2Performance.totals.points }}
      </div>
      <TeamPreview :season="this.season" :team-id="this.game.team2.id"
                   :team-city="this.game.team2.city" :team-name="this.game.team2.name"/>
    </div>
    <BoxScore class="box-score"/>
    <div>//TODO: Ивент лог игры ЗДЕСЬ</div>
    </div>
  </template>
</template>

<style scoped>

.box-score {
  margin-top: 70px; /* Отступ сверху в 40px */
}
.game-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.score-container {
  background-color: #3498db;
  color: #fff;
  font-size: 24px;
  font-weight: bold;
  padding: 12px 20px;
  border-radius: 12px;
}

.outer {
    background-color: #D9D9D9;
    height: 480px;
    width: 1200px;
    padding: 50px;
    overflow: auto;
  }

</style>
