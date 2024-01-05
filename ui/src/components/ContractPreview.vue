<script setup>
import {IMAGES_PATH} from "@/constants";

let props = defineProps({
  season: Number,
  teamId: Number,
  teamName: String,
  jerseyNumber: Number,
  from: Date,
  to: Date,
});
</script>

<script>
import {TEAM_MAIN_COLOR_BY_ID} from "@/constants"
</script>

<template>
<div class="contract-preview">
  <div class="team-image" @click="$router.push({path: '/team/' + teamId})">
    <img v-bind:src="IMAGES_PATH +  '/teams/' + season + '/' + teamId + '.png'" alt="team image" style="width: 300px; height: 300px; border-radius: 150px;"/>
  </div>
  <div>
    {{ teamName}}
  </div>
  <div>
    {{ "#" + jerseyNumber}}
  </div>
  <div>
    {{ from + " - " + to }}
  </div>
</div>
</template>

<style scoped>
.contract-preview {
  border: 5px solid v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]);
  width: 300px;
  height: 400px;
}
.contract-preview:hover {
  background-color: v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]);
}

.team-image:hover {
  cursor: pointer;
}
</style>