<script setup>
import {SHOT_TYPE_TRANSLATION} from "@/constants";

defineProps({
  playerTechnicalFoul: {},
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
      <PlayerPreview :playerId="playerTechnicalFoul.foulingPlayer.id"
                     :season="2023"
                     :firstName="playerTechnicalFoul.foulingPlayer.firstName"
                     :lastName="playerTechnicalFoul.foulingPlayer.lastName"
                     :position="playerTechnicalFoul.foulingPlayer.position"
                     :teamId="teamIdByPlayerId[playerTechnicalFoul.foulingPlayer.id]"
                     :label="'сфолил'"/>
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
