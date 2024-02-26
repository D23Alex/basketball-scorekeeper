<script setup>
import {SHOT_TYPE_TRANSLATION} from "@/constants";

defineProps({
  freeThrowAttempt: {},
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
      <PlayerPreview :playerId="freeThrowAttempt.shooter.id"
                     :season="2023"
                     :firstName="freeThrowAttempt.shooter.firstName"
                     :lastName="freeThrowAttempt.shooter.lastName"
                     :position="freeThrowAttempt.shooter.position"
                     :teamId="teamIdByPlayerId[freeThrowAttempt.shooter.id]"
                     :label="'бросил'"/>
    </div>
    <div class="info">
      <div class="type">{{ SHOT_TYPE_TRANSLATION[freeThrowAttempt.type] }}</div>
      <div class="result" v-if="freeThrowAttempt.isSuccessful">Попадание</div>
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
