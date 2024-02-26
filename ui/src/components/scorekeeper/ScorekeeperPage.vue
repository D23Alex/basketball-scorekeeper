<script>
import GamePreview from "@/components/game/GamePreview.vue";
import GameEvent from "@/components/scorekeeper/GameEvent.vue";
import PlayerPreview from "@/components/scorekeeper/PlayerPreview.vue";
import axios from "axios";
import { API } from "@/constants";
import Timer from "@/components/scorekeeper/Timer.vue";
import playerPage from "@/components/player/PlayerPage.vue";
import { secondsInPeriods } from "@/util";

export default {
  components: { Timer, GameEvent, GamePreview, PlayerPreview },
  data() {
    return {
      allLoaded: false,
      teamIdByPlayerId: {},
      currentGameTimeInSeconds: 0,
      eventsWithTypesOrderedByMillisSinceStart: [],
      periodRelatedEventsWithTypes: [],
      eventOptions: [
        { name: "Бросок с игры", slug: "field-goal-attempt" },
        { name: "Штрафной бросок", slug: "free-throw-attempt" },
        { name: "Фол", slug: "personal-foul" },
        { name: "Удаление", slug: "player-ejection" },
        { name: "Технический фол", slug: "player-technical-foul" },
        { name: "Потеря", slug: "turnover" },
        { name: "Выход в начале периода", slug: "period-starter" },
        { name: "Выход с площадки в конце периода", slug: "period-ender" },
      ],
      season: 2023,
      game: {},
      team1Performance: {},
      team2Performance: {},
      team1Lineup: [],
      team2Lineup: [],
      eventsNotSaved: [],
      gameEventLog: {
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
    this.team1Performance = (
      await axios.get(
        API +
          "/stats/team-single-game-performance/" +
          this.game.team1.id +
          "/" +
          this.$route.params.gameId
      )
    ).data.performance;
    this.team2Performance = (
      await axios.get(
        API +
          "/stats/team-single-game-performance/" +
          this.game.team2.id +
          "/" +
          this.$route.params.gameId
      )
    ).data.performance;
    this.team1Lineup = this.gameEventLog.lineupOccurrences
      .filter((occurrence) => occurrence.team.id === this.game.team1.id)
      .map((occurrence) => occurrence.player);
    this.team2Lineup = this.gameEventLog.lineupOccurrences
      .filter((occurrence) => occurrence.team.id === this.game.team2.id)
      .map((occurrence) => occurrence.player);
    this.updateEventsWithTypesOrderedByMillisSinceStart();

    this.team1Lineup.forEach(
      (player) => (this.teamIdByPlayerId[player.id] = this.game.team1.id)
    );
    this.team2Lineup.forEach(
      (player) => (this.teamIdByPlayerId[player.id] = this.game.team2.id)
    );

    console.log(1);
    console.log(this.gameEventLog.periodStarters);

    this.allLoaded = true;
  },

  methods: {
    updateGameTime(timeIsSeconds) {
      this.currentGameTimeInSeconds = timeIsSeconds;
    },

    startPeriod() {
      axios.post(API + "/events/period-start", {
        game: this.game,
        timestamp: Date.now(),
      });
    },

    endPeriod() {
      axios.post(API + "/events/period-ending", {
        game: this.game,
        timestamp: Date.now(),
      });
    },

    updateEventsWithTypesOrderedByMillisSinceStart() {
      this.gameEventLog.periodStarters.forEach(
        (ev) =>
          (ev.millisecondsSinceStart = secondsInPeriods(ev.period - 1) * 1000)
      );
      this.gameEventLog.periodEnders.forEach(
        (ev) =>
          (ev.millisecondsSinceStart = (secondsInPeriods(ev.period) - 1) * 1000)
      );

      this.eventsWithTypesOrderedByMillisSinceStart =
        this.gameEventLog.fieldGoalAttempts
          .map((ev) => ({ type: "field-goal-attempt", ev: ev }))
          .concat(
            this.gameEventLog.freeThrowAttempts.map((ev) => ({
              type: "free-throw-attempt",
              ev: ev,
            }))
          )
          .concat(
            this.gameEventLog.personalFouls.map((ev) => ({
              type: "personal-foul",
              ev: ev,
            }))
          )
          .concat(
            this.gameEventLog.playerEjections.map((ev) => ({
              type: "player-ejection",
              ev: ev,
            }))
          )
          .concat(
            this.gameEventLog.playerTechnicalFouls.map((ev) => ({
              type: "player-technical-foul",
              ev: ev,
            }))
          )
          .concat(
            this.gameEventLog.turnovers.map((ev) => ({
              type: "turnover",
              ev: ev,
            }))
          )
          .concat(
            this.gameEventLog.periodStarters.map((ev) => ({
              type: "period-starter",
              ev: ev,
            }))
          )
          .concat(
            this.gameEventLog.periodEnders.map((ev) => ({
              type: "period-ender",
              ev: ev,
            }))
          )
          .sort(
            (a, b) => a.ev.millisecondsSinceStart - b.ev.millisecondsSinceStart
          );
    },

    eventByPlayerClicked(eventSlug, player) {
      if (eventSlug === "field-goal-attempt")
        return {
          shooter: player,
          assistant: null,
          blockedBy: null,
          reboundedBy: null,
          millisecondsSinceStart: this.currentGameTimeInSeconds * 1000,
          isSuccessful: false,
          type: 0,
          game: this.game,
        };
      if (eventSlug === "free-throw-attempt")
        return {
          shooter: player,
          millisecondsSinceStart: this.currentGameTimeInSeconds * 1000,
          isSuccessful: false,
          game: this.game,
        };
      if (eventSlug === "player-technical-foul")
        return {
          foulingPlayer: player,
          millisecondsSinceStart: this.currentGameTimeInSeconds * 1000,
          game: this.game,
        };
      if (eventSlug === "personal-foul")
        return {
          foulingPlayer: player,
          fouledPlayer: null,
          isUnsportsmanlike: false,
          isOffensive: false,
          millisecondsSinceStart: this.currentGameTimeInSeconds * 1000,
          game: this.game,
        };
      if (eventSlug === "player-ejection")
        return {
          ejectedPlayer: player,
          ejectionCause: null,
          millisecondsSinceStart: this.currentGameTimeInSeconds * 1000,
          game: this.game,
        };
      if (eventSlug === "turnover")
        return {
          player: player,
          stealer: null,
          cause: null,
          millisecondsSinceStart: this.currentGameTimeInSeconds * 1000,
          game: this.game,
        };
      if (eventSlug === "period-starter")
        return {
          player: player,
          period: 1,
          team: { id: this.teamIdByPlayerId[player.id] },
          game: this.game,
        };
      if (eventSlug === "period-ender")
        return {
          player: player,
          period: 1,
          team: { id: this.teamIdByPlayerId[player.id] },
          game: this.game,
        };
    },

    handleClick(event, item) {
      this.$refs.vueSimpleContextMenu.showMenu(event, item);
    },

    optionClicked(event) {
      console.log({ type: event.option.slug, ev: event.item });
      this.eventsNotSaved.push({
        type: event.option.slug,
        ev: this.eventByPlayerClicked(event.option.slug, event.item),
      });
    },
  },
};
</script>

<template>
  <Timer @selectedtimechanged="updateGameTime" />
  <template v-if="allLoaded">
    <GamePreview
      :season="season"
      :team1-score="team1Performance.totals.points"
      :team2-score="team2Performance.totals.points"
      :game-status="'TODO: статус игры'"
      :team1-info="game.team1"
      :team2-info="game.team2"
      :game-scheduled-start="game.scheduledStartTime"
      :game-id="game.id"
    />
    <div @click="startPeriod">начать период</div>
    <div @click="endPeriod">закончить период</div>
    <div class="lineups">
      <div class="lineup">
        <div
          v-for="player in team1Lineup"
          @click.prevent.stop="handleClick($event, player)"
        >
          <PlayerPreview
            :player-id="player.id"
            :teamId="game.team1.id"
            :first-name="player.firstName"
            :last-name="player.lastName"
          />
        </div>
      </div>
      <div class="lineup">
        <div
          v-for="player in team2Lineup"
          @click.prevent.stop="handleClick($event, player)"
        >
          <PlayerPreview
            :player-id="player.id"
            :teamId="game.team2.id"
            :first-name="player.firstName"
            :last-name="player.lastName"
          />
        </div>
      </div>
      <vue-simple-context-menu
        element-id="myUniqueId"
        :options="eventOptions"
        ref="vueSimpleContextMenu"
        @option-clicked="optionClicked"
      >
      </vue-simple-context-menu>
    </div>
    <div>
      <div v-for="eventAndType in eventsWithTypesOrderedByMillisSinceStart">
        <GameEvent
          :type="eventAndType.type"
          :ev="eventAndType.ev"
          :players="team1Lineup.concat(team2Lineup)"
          :team-id-by-player-id="teamIdByPlayerId"
        />
      </div>
      Новые ивенты
      <div v-for="eventAndType in eventsNotSaved">
        <GameEvent
          unsaved-by-default="true"
          :type="eventAndType.type"
          :ev="eventAndType.ev"
          :players="team1Lineup.concat(team2Lineup)"
          :team-id-by-player-id="teamIdByPlayerId"
        />
      </div>
    </div>
  </template>
</template>

<style scoped>
.lineups-container {
  display: flex;
  justify-content: start;
}

.lineup {
  flex-grow: 1;
  border: 5px solid #000000;
  display: flex;
  flex-direction: row;
  border-radius: 8px;
}

.player {
  margin-bottom: 5px;
}
</style>
