<script setup>
defineProps({
  teamId: Number,
  teamName: String,
  teamCity: String,
  season: Number
})
</script>

<script>
import {TEAM_MAIN_COLOR_BY_ID} from "@/constants"
</script>

<template>
  <div class="team-preview">
    <div class="team-image">
      <img v-bind:src="'images/teams/' + season + '/' + teamId + '.png'" alt="team image" style="width: 200px; height: 200px; border-radius: 150px;"/>
    </div>
    <div>{{ teamName }}</div>
    <div>{{ teamCity }}</div>
  </div>
</template>

<style scoped>
.team-preview {
  display: flex;
  flex-direction: column;
  /* border: 5px solid v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]); */
  width: 300px;
  height: 400px;
}
/* .team-preview:hover {
  background-color: v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]);
} */
</style>