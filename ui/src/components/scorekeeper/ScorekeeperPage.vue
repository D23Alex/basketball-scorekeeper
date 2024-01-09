<script>
import GamePreview from "@/components/game/GamePreview.vue";
import GameEvent from "@/components/scorekeeper/GameEvent.vue";
import PlayerPreview from "@/components/scorekeeper/PlayerPreview.vue";
import axios from "axios";
import {API} from "@/constants";

export default {
  components: {GameEvent, GamePreview, PlayerPreview},
  data() {
    return {
      allLoaded: false,
      eventOptions: [
        {name: "Бросок с игры", slug: "field-goal-attempt"},
        {name: "Штрафной бросок", slug: "free-throw-attempt"}],
      season: 2023, //TODO: hardcoded atm
      game: {},
      team1Performance: {},
      team2Performance: {},
      team1Lineup: [],
      team2Lineup: [],
      gameClock: Number,
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
        turnovers: []
      }
    }
  },

  async mounted() {
    this.gameEventLog = (await axios.get(API + "/events/game/" + this.$route.params.gameId)).data;
    this.game = (await axios.get(API + "/games/" + this.$route.params.gameId)).data;
    this.team1Performance = (await axios.get(API + "/stats/team-single-game-performance/"
        + this.game.team1.id + "/" + this.$route.params.gameId)).data.performance;
    this.team2Performance = (await axios.get(API + "/stats/team-single-game-performance/"
        + this.game.team2.id + "/" + this.$route.params.gameId)).data.performance;
    this.team1Lineup = this.gameEventLog.lineupOccurrences
        .filter(occurrence => occurrence.team.id === this.game.team1.id)
        .map(occurrence => occurrence.player);
    this.team2Lineup = this.gameEventLog.lineupOccurrences
        .filter(occurrence => occurrence.team.id === this.game.team2.id)
        .map(occurrence => occurrence.player);
    this.allLoaded = true;
    console.log(this.gameEventLog.fieldGoalAttempts);
  },

  methods: {
    eventsWithTypesOrderedByMillisSinceStart() {
      return this.gameEventLog.fieldGoalAttempts.map(ev => ({type: "field-goal-attempt", ev: ev}))
        .concat(this.gameEventLog.freeThrowAttempts.map(ev => ({type: "free-throw-attempt", ev: ev})))
        .sort((a, b) => a.ev.millisecondsSinceStart - b.ev.millisecondsSinceStart);
    },

    eventByPlayerClicked(eventSlug, player) {
      if (eventSlug === "field-goal-attempt")
        return {
          shooter: player,
          assistant: null,
          blockedBy: null,
          reboundedBy: null,
          millisecondsSinceStart: 0,
          isSuccessful: false,
          type: 0,
          game: this.game
      }
    },

    handleClick(event, item) {
      this.$refs.vueSimpleContextMenu.showMenu(event, item);
    },

    optionClicked(event) {
      console.log({type: event.option.slug, ev: event.item});
      this.eventsNotSaved.push({type: event.option.slug, ev: this.eventByPlayerClicked(event.option.slug, event.item)});
    }
  },
}
</script>

<template>
Тут страница матча для секретаря
  <div>TODO: сделать таймер</div>
  <template v-if="allLoaded">
    <GamePreview :season="this.season" :team1-score="this.team1Performance.totals.points"
                 :team2-score="this.team2Performance.totals.points" :game-status="'TODO: статус игры'"
                 :team1-info="this.game.team1" :team2-info="this.game.team2"
                 :game-scheduled-start="this.game.scheduledStartTime"
                 :game-id="this.game.id"/>
    <div class="lineups">
      Игроки
      <div class="lineup">
        <div v-for="player in team1Lineup"
             @click.prevent.stop="this.handleClick($event, player)">
          <PlayerPreview :player-id="player.id"
                         :jersey-number="1"
                         :teamId="this.game.team1.id"
                         :first-name="player.firstName"
                         :last-name="player.lastName"/>
        </div>
      </div>
      <div class="lineup">
        <div v-for="player in team2Lineup"
             @click.prevent.stop="this.handleClick($event, player)">
          <PlayerPreview :player-id="player.id"
                         :jersey-number="1"
                         :teamId="this.game.team2.id"
                         :first-name="player.firstName"
                         :last-name="player.lastName"/>
        </div>
      </div>
      <vue-simple-context-menu element-id="myUniqueId"
                               :options="this.eventOptions"
                               ref="vueSimpleContextMenu"
                               @option-clicked="optionClicked">
      </vue-simple-context-menu>
    </div>
    <div>
      Ивенты уже имеющиеся в базе
      <div v-for="eventAndType in this.eventsWithTypesOrderedByMillisSinceStart()">
        <GameEvent :type="eventAndType.type" :ev="eventAndType.ev" :players="team1Lineup.concat(team2Lineup)"/>
      </div>
      Несохранённые ивенты
      <div v-for="eventAndType in this.eventsNotSaved">
        <GameEvent unsaved-by-default="true" :type="eventAndType.type" :ev="eventAndType.ev" :players="team1Lineup.concat(team2Lineup)"/>
      </div>
    </div>
  </template>
</template>

<style scoped>
.lineup, .lineups {
  display: flex;
  flex-direction: row;
}
</style>