<script setup>
import {SHOT_TYPE_TRANSLATION} from "@/constants";

defineProps({
  personalFoul: {},
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
      <PlayerPreview :playerId="personalFoul.foulingPlayer.id"
                     :season="2023"
                     :firstName="personalFoul.foulingPlayer.firstName"
                     :lastName="personalFoul.foulingPlayer.lastName"
                     :position="personalFoul.foulingPlayer.position"
                     :teamId="teamIdByPlayerId[personalFoul.foulingPlayer.id]"
                     :label="'сфолил'"/>
      <PlayerPreview v-if="personalFoul.fouledPlayer" :playerId="personalFoul.fouledPlayer.id"
                     :season="2023"
                     :firstName="personalFoul.fouledPlayer.firstName"
                     :lastName="personalFoul.fouledPlayer.lastName"
                     :position="personalFoul.fouledPlayer.position"
                     :teamId="teamIdByPlayerId[personalFoul.fouledPlayer.id]"
                     :label="'на ком сфолили'"/>
    </div>
    <div class="info">
      <div class="result" v-if="personalFoul.isUnsportsmanlike">неспортивный</div>
      <div class="result" v-if="!personalFoul.isOffensive">в защите</div>
      <div class="result-error" v-else>в нападении</div>
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
