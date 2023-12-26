<script setup>
let props = defineProps({
  playerId: Number,
  season: Number,
  firstName: String,
  lastName: String,
  position: String,
  teamId: Number,
});
</script>

<script>
import {TEAM_MAIN_COLOR_BY_ID} from "@/constants"
</script>

<template>
<div class="player-preview">
  <div class="player-preview-image">
    <img v-bind:src="'basketball-scorekeeper/images/players/' + season + '/' + playerId + '.jpg'" alt="player image" style="width: 300px; height: 300px; border-radius: 150px;"/>
  </div>
  <div>
    {{ firstName + " " + lastName }}
  </div>
  <div>
    {{ position }}
  </div>
</div>

</template>

<style scoped>
.player-preview {
  border: 5px solid v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]);
  width: 300px;
  height: 400px;
}
.player-preview:hover {
  background-color: v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]);
}
</style>