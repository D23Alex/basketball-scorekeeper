<script setup>
import {IMAGES_PATH, PLAYER_POSITION_TRANSLATION} from "@/constants";

let props = defineProps({
  playerId: Number,
  season: Number,
  firstName: String,
  lastName: String,
  position: String,
  teamId: Number,
  label: String,
});
</script>

<script>
import {TEAM_MAIN_COLOR_BY_ID} from "@/constants"
</script>

<template>
  <div class="player-preview" @click="$router.push({path: '/player/' + playerId})">
    <div class="player-preview-content">
      <div class="player-preview-image">
        <img v-bind:src=" IMAGES_PATH + '/players/' + season + '/' + playerId + '.jpg'" alt="player image" style="width: 200px; height: 200px; border-radius: 180px;"/> 
      </div>
      <div class="label" v-if="label">{{ label }}</div>
      <div class="player-info">
        <div class="player-name">{{ firstName + " " + lastName }}</div>
        <div class="player-position">{{ PLAYER_POSITION_TRANSLATION[position] }}</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.player-preview {
  display: flex;
  align-items: center;
}

.player-preview-content {
  display: flex;
  align-items: center;
}
.player-preview:hover {
  background-color: v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]);
  cursor: pointer;

}

.player-preview-image {
  margin-right: 10px; /* Просто для отступа между изображением и информацией */
}

.player-info {
  background-color: #DD8484;
  padding: 10px;
  border-radius: 5px;
  text-align: left; /* Выравнивание текста слева */
  color: white; /* Цвет текста */
}

.player-name,
.player-position {
  margin: 5px 0;
}

.label {
  font-weight: bold;
  margin-right: 10px;
  color: white;
  background-color: #333;
  padding: 5px;
  border-radius: 5px;
  display: inline-block; /* Чтобы элементы вокруг него выстраивались в ряд */
}
</style>
