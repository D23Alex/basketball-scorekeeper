<script setup>
import FieldGoalAttempt from "@/components/scorekeeper/events/FieldGoalAttempt.vue";
import FreeThrowAttempt from "@/components/scorekeeper/events/FreeThrowAttempt.vue";
import PersonalFoul from "@/components/scorekeeper/events/PersonalFoul.vue";
import PlayerEjection from "@/components/scorekeeper/events/PlayerEjection.vue";
import PlayerTechnicalFoul from "@/components/scorekeeper/events/PlayerTechnicalFoul.vue";
import Turnover from "@/components/scorekeeper/events/Turnover.vue";
import {EVENT_NAME_BY_SLUG} from "@/constants";
import PeriodStarter from "@/components/scorekeeper/events/PeriodStarter.vue";
import PeriodEnder from "@/components/scorekeeper/events/PeriodEnder.vue";

defineProps({
  unsavedByDefault: Boolean,
  type: String,
  ev: Object,
  players: Array,
  initialGameTimeInSeconds: Number,
  teamIdByPlayerId: {},
})
</script>

<script>
import axios from "axios";
import {API} from "@/constants";

export default {
  data() {
    return {
      neverChanged: true,
      currentEvent: this.ev,
      color: 'green',
      hasUnsavedChanges: this.unsavedByDefault !== false && this.unsavedByDefault !== null
          && this.unsavedByDefault !== undefined,
      deleted: false
    }
  },
  methods: {
    setColor() {
      // есть несохранённые изменения - жёлтый, есть сохранённые изменения - синий,
      // не было изменений после загрузки из базы - зелёный. //TODO: цвета
      this.color = this.hasUnsavedChanges ? "yellow" : (this.neverChanged ? "green" : "blue");
    },

    async deleteEvent() {
      if (!this.hasUnsavedChanges)
        await axios.delete(API + "/events/" + this.type + "/" + this.ev.id);
      this.deleted = true;
    },

    async saveEvent() {
      this.ev.id = (await axios.post(API + "/events/" + this.type, this.ev)).data.id;
      this.hasUnsavedChanges = false;
      this.setColor();
    },

    updateEventLocally(newEv) {
      this.neverChanged = false;
      this.currentEvent = newEv;
      this.hasUnsavedChanges = true;
      this.setColor();
    },
  },

  mounted() {
    this.setColor();
  }
}
</script>

<template>
  <div v-if="!deleted" class="game-event">
    <div>{{ EVENT_NAME_BY_SLUG[type] }}</div>
    <div class="clickable" @click="deleteEvent()">удалить</div>
    <div class="clickable" v-if="hasUnsavedChanges" @click="saveEvent()">сохранить</div>
    <FieldGoalAttempt v-if="type === 'field-goal-attempt'" @fieldgoalattemptchanged="updateEventLocally"
                      :field-goal-attempt="ev" :team-id-by-player-id="this.teamIdByPlayerId"
                      :players="players" :initial-game-time-in-seconds="initialGameTimeInSeconds"/>
    <FreeThrowAttempt v-if="type === 'free-throw-attempt'" @freethrowattemptchanged="updateEventLocally"
                      :free-throw-attempt="ev" :team-id-by-player-id="this.teamIdByPlayerId"
                      :players="players" :initial-game-time-in-seconds="initialGameTimeInSeconds"/>
    <PersonalFoul v-if="type === 'personal-foul'" @personalfoulchanged="updateEventLocally"
                      :personal-foul="ev" :team-id-by-player-id="this.teamIdByPlayerId"
                      :players="players" :initial-game-time-in-seconds="initialGameTimeInSeconds"/>
    <PlayerEjection v-if="type === 'player-ejection'" @playerejectionchanged="updateEventLocally"
                      :player-ejection="ev" :team-id-by-player-id="this.teamIdByPlayerId"
                      :players="players" :initial-game-time-in-seconds="initialGameTimeInSeconds"/>
    <PlayerTechnicalFoul v-if="type === 'player-technical-foul'" @playertechnicalfoulchanged="updateEventLocally"
                    :player-technical-foul="ev" :team-id-by-player-id="this.teamIdByPlayerId"
                    :players="players" :initial-game-time-in-seconds="initialGameTimeInSeconds"/>
    <Turnover v-if="type === 'turnover'" @turnoverchanged="updateEventLocally"
                         :turnover="ev" :team-id-by-player-id="this.teamIdByPlayerId"
                         :players="players" :initial-game-time-in-seconds="initialGameTimeInSeconds"/>
    <PeriodStarter v-if="type === 'period-starter'" @periodstarterchanged="updateEventLocally"
                    :period-starter="ev" :team-id-by-player-id="this.teamIdByPlayerId"
                    :players="players"/>
    <PeriodEnder v-if="type === 'period-ender'" @periodenderchanged="updateEventLocally"
                   :period-ender="ev" :team-id-by-player-id="this.teamIdByPlayerId"
                   :players="players"/>
  </div>
</template>

<style scoped>
.game-event {
  border-style: solid;
  border-width: 5px;
  border-color: v-bind(color)
}

.game-event:hover {
  background-color:rgba(0, 0, 0, 0.2);
}

.clickable {
  cursor: pointer;
}
</style>