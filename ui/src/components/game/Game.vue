<script>
import axios from "axios";
import BoxScore from "@/components/game/BoxScore.vue";


export default {
  components: {BoxScore},
  data() {
    return {
      loaded: false,
      game: null,

      team1Lineup: [],
      team2Lineup: [],

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
    this.gameEventLog = (await axios.get("http://localhost:8080/api/events/game/" + this.$route.params.gameId)).data
    this.game = (await axios.get("http://localhost:8080/api/game/" + this.$route.params.gameId)).data;
    this.team1Lineup = this.gameEventLog.lineupOccurrences.map(occurrence => occurrence.player); //TODO: filter that
  }
}
</script>

<template>
  <p>Вся информация о конкретной игре. Тут будут располагаться стартовые составы, box score, графики и т.п.</p>
  <p>ИГРА</p>
  {{game}}
  <BoxScore/>
  <p>Ивент лог игры</p>
  <p>{{ gameEventLog }}</p>

</template>

<style scoped>

</style>