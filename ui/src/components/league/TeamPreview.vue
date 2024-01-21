<script setup>
import {IMAGES_PATH} from "@/constants";

defineProps({
  teamId: Number,
  teamName: String,
  teamCity: String,
  season: Number
})
</script>

<script>
import { TEAM_MAIN_COLOR_BY_ID } from "@/constants"
</script>

<template>
  <div class="team-preview" @click="$router.push({path: '/team/' + teamId})">
    <div class="team-container">
      <div class="team-image">
        <img v-bind:src="'images/teams/' + season + '/' + teamId + '.png'" alt="team image" style="width: 120px; height: 120px; border-radius: 150px;"/>
      </div>
      <div class="team-details">
        <div class="team-name">{{ teamName }}</div>
        <div class="team-city">{{ teamCity }}</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.team-preview {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 200px;
  height: 200px;
}

.team-preview:hover {
  border-radius: 10px;
  border: 3px solid v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]);
  cursor: pointer;
}

.team-container {
  border-radius: 10px;
  padding: 10px;
  text-align: center;
}

.team-image {
  text-align: center;
}

.team-details {
  margin-top: 10px;
}

.team-name,
.team-city {
  color: #fff;
  font-weight: bold;
}

.team-details {
  background-color: v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]);
  border-radius: 10px;
}
</style>
