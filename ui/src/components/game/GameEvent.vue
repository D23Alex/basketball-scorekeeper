<script setup>
import FieldGoalAttempt from "@/components/game/events/FieldGoalAttempt.vue";
import { prettyGameTimestampBySecondsSinceStart } from "@/util";
import {EVENT_NAME_BY_SLUG} from "@/constants";
import FreeThrowAttempt from "@/components/game/events/FreeThrowAttempt.vue";
import PersonalFoul from "@/components/game/events/PersonalFoul.vue";
import PlayerTechnicalFoul from "@/components/game/events/PlayerTechnicalFoul.vue";
import Turnover from "@/components/game/events/Turnover.vue";

defineProps({
  type: String,
  ev: Object,
  teamIdByPlayerId: {},
});
</script>

<template>
  <div class="game-event">
    <div class="event-type">{{ EVENT_NAME_BY_SLUG[type] }}</div>
    <div v-if="ev.millisecondsSinceStart" class="event-timestamp">
      {{ prettyGameTimestampBySecondsSinceStart(ev.millisecondsSinceStart / 1000) }}
    </div>
    <div v-if="type === 'field-goal-attempt'" class="game-event-wrapper">
      <FieldGoalAttempt :team-id-by-player-id="teamIdByPlayerId" :field-goal-attempt="ev" />
    </div>
    <div v-if="type === 'free-throw-attempt'" class="game-event-wrapper">
      <FreeThrowAttempt :team-id-by-player-id="teamIdByPlayerId" :free-throw-attempt="ev" />
    </div>
    <div v-if="type === 'personal-foul'" class="game-event-wrapper">
      <PersonalFoul :team-id-by-player-id="teamIdByPlayerId" :personal-foul="ev" />
    </div>
    <div v-if="type === 'player-technical-foul'" class="game-event-wrapper">
      <PlayerTechnicalFoul :team-id-by-player-id="teamIdByPlayerId" :player-technical-foul="ev" />
    </div>
    <div v-if="type === 'turnover'" class="game-event-wrapper">
      <Turnover :team-id-by-player-id="teamIdByPlayerId" :turnover="ev" />
    </div>
  </div>
</template>

<style scoped>
.game-event {
  /* FIXME */
  text-align: center;
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
  align-items: center; 
}

.event-type {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.event-timestamp {
  font-size: 14px;
  color: #555;
}

.game-event-wrapper {
  display: flex;
  justify-content: center; 
  align-items: center; 
  width: 100%; 
}
</style>