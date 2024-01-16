<script setup>
import {IMAGES_PATH} from "@/constants";

defineProps({
  playerId: Number,
  teamId: Number,
  jerseyNumber: Number,
  firstName: String,
  lastName: String
})
</script>

<script>
import {TEAM_MAIN_COLOR_BY_ID} from "@/constants"
</script>

<template>
<div class="player-preview">
  <div class="player-preview-image">
    <!-- FIXME: 2023 is hardcoded atm -->
    <img v-bind:src=" IMAGES_PATH + '/players/' + 2023 + '/' + playerId + '.jpg'"
         alt="player image" style="width: 150px; height: 150px;"/>
  </div>
  <div> {{ jerseyNumber }} </div>
  <div> {{ firstName + ' ' + lastName }} </div>
</div>
</template>

<style scoped>
.player-preview {
  background-color: v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]);
  width: 150px;
  height: 200px;
}

</style>