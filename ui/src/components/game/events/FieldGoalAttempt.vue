<script setup>
import {SHOT_TYPE_TRANSLATION} from "@/constants";

defineProps({
  fieldGoalAttempt: {},
  teamIdByPlayerId: {},
})
</script>

<script>
import PlayerPreview from "@/components/player/PlayerPreview.vue"
export default {
  components: {PlayerPreview},
}
</script>

<template>
  <div class="field-goal-attempt">
    <div class="players">
      <PlayerPreview :playerId="fieldGoalAttempt.shooter.id"
                     :season="2023"
                     :firstName="fieldGoalAttempt.shooter.firstName"
                     :lastName="fieldGoalAttempt.shooter.lastName"
                     :position="fieldGoalAttempt.shooter.position"
                     :teamId="teamIdByPlayerId[fieldGoalAttempt.shooter.id]"
                     :label="'бросил'"/>
      <PlayerPreview v-if="fieldGoalAttempt.reboundedBy" :playerId="fieldGoalAttempt.reboundedBy.id"
                     :season="2023"
                     :firstName="fieldGoalAttempt.reboundedBy.firstName"
                     :lastName="fieldGoalAttempt.reboundedBy.lastName"
                     :position="fieldGoalAttempt.reboundedBy.position"
                     :teamId="teamIdByPlayerId[fieldGoalAttempt.reboundedBy.id]"
                     :label="'подобрал'"/>
      <PlayerPreview v-if="fieldGoalAttempt.assistant" :playerId="fieldGoalAttempt.assistant.id"
                     :season="2023"
                     :firstName="fieldGoalAttempt.assistant.firstName"
                     :lastName="fieldGoalAttempt.assistant.lastName"
                     :position="fieldGoalAttempt.assistant.position"
                     :teamId="teamIdByPlayerId[fieldGoalAttempt.assistant.id]"
                     :label="'результативная передача'"/>
      <PlayerPreview v-if="fieldGoalAttempt.blockedBy" :playerId="fieldGoalAttempt.blockedBy.id"
                     :season="2023"
                     :firstName="fieldGoalAttempt.blockedBy.firstName"
                     :lastName="fieldGoalAttempt.blockedBy.lastName"
                     :position="fieldGoalAttempt.blockedBy.position"
                     :teamId="teamIdByPlayerId[fieldGoalAttempt.blockedBy.id]"
                     :label="'блок'"/>
    </div>
    <div class="info">
      <div class="result" v-if="fieldGoalAttempt.isSuccessful">Попадание</div>
      <div class="result-error" v-else>Промах</div>
    </div>
  </div>
</template>

<style scoped>
.field-goal-attempt {
  margin: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f5f5f5;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.players {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.info {
  margin-top: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.info div {
  margin-bottom: 8px;
  font-size: 18px;
  color: #333;
}

.info .type {
  font-weight: bold;
}

.info .result {
  color: #4CAF50; /* Зеленый цвет для успешного попадания */
}

.info .result-error {
  color: #FF0000; /* Красный цвет для неудачного попадания */
}
</style>
