<script>
import axios from "axios";
import BoxScore from "@/components/game/BoxScore.vue";
import TeamPreview from "@/components/league/TeamPreview.vue";
import { API } from "@/constants";
import GamePreview from "@/components/game/GamePreview.vue";
import EventLog from "@/components/game/EventLog.vue";

export default {
  components: { GamePreview, TeamPreview, BoxScore, EventLog },
  data() {
    return {
      allLoaded: false,
      timer: null,
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
        turnovers: [],
      },
    };
  },

  async mounted() {
    this.gameEventLog = (
      await axios.get(API + "/events/game/" + this.$route.params.gameId)
    ).data;
    this.game = (
      await axios.get(API + "/games/" + this.$route.params.gameId)
    ).data;
    this.team1Lineup = this.gameEventLog.lineupOccurrences
      .filter((occurrence) => occurrence.team.id === this.game.team1.id)
      .map((occurrence) => occurrence.player);
    this.team2Lineup = this.gameEventLog.lineupOccurrences
      .filter((occurrence) => occurrence.team.id === this.game.team2.id)
      .map((occurrence) => occurrence.player);
    await this.updatePerformance();

    this.timer = setInterval(() => {
      this.updateGameEventLog();
    }, 10000); // TODO: only do regular updates on a game that is online at the moment

    this.allLoaded = true;
  },

  beforeDestroy() {
    clearInterval(this.timer);
  },

  methods: {
    async updatePerformance() {
      this.team1Performance = (
        await axios.get(
          API +
            "/stats/team-single-game-performance/" +
            this.game.team1.id +
            "/" +
            this.$route.params.gameId
        )
      ).data.performance;
      console.log(this.team1Performance);
      this.team2Performance = (
        await axios.get(
          API +
            "/stats/team-single-game-performance/" +
            this.game.team2.id +
            "/" +
            this.$route.params.gameId
        )
      ).data.performance;
    },

    async updateGameEventLog() {
      let newEventLog = (
        await axios.get(API + "/events/game/" + this.$route.params.gameId)
      ).data;
      if (JSON.stringify(this.gameEventLog) !== JSON.stringify(newEventLog)) {
        this.gameEventLog = newEventLog;
        if (this.game !== null) await this.updatePerformance();
      }
    },
  },
};
</script>

<template>
  <template v-if="allLoaded">
    <div class="outer">
      <div class="game-container">
        <TeamPreview
          :season="season"
          :team-id="game.team1.id"
          :team-city="game.team1.city"
          :team-name="game.team1.name"
        />
        <div class="score-container">
          {{ team1Performance.totals.points }} -
          {{ team2Performance.totals.points }}
        </div>
        <TeamPreview
          :season="season"
          :team-id="game.team2.id"
          :team-city="game.team2.city"
          :team-name="game.team2.name"
        />
      </div>
      <BoxScore :key="gameEventLog" class="box-score" />
      <EventLog :key="gameEventLog" :game-event-log="gameEventLog" />
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
  background-color: #d9d9d9;
  /* FIXME: in percents */
  height: 80%;
  width: 100%;
  padding: 50px;
  overflow: auto;
}
</style>
