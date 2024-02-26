<script setup>
import {SHOT_TYPE_TRANSLATION, TURNOVER_CAUSE_TRANSLATION} from "@/constants";

defineProps({
  turnover: {},
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
      <PlayerPreview :playerId="turnover.player.id"
                     :season="2023"
                     :firstName="turnover.player.firstName"
                     :lastName="turnover.player.lastName"
                     :position="turnover.player.position"
                     :teamId="teamIdByPlayerId[turnover.player.id]"
                     :label="'потерял'"/>
      <PlayerPreview v-if="turnover.stealer" :playerId="turnover.stealer.id"
                     :season="2023"
                     :firstName="turnover.stealer.firstName"
                     :lastName="turnover.stealer.lastName"
                     :position="turnover.stealer.position"
                     :teamId="teamIdByPlayerId[turnover.stealer.id]"
                     :label="'перехват'"/>
    </div>
    <div class="info">
      <div class="result-error">{{ TURNOVER_CAUSE_TRANSLATION[turnover.cause] }}</div>
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
