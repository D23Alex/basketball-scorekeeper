<script setup>
import TeamPreview from "@/components/league/TeamPreview.vue";

let props = defineProps({
  team1Info: {
    id: Number,
    name: String,
    city: String
  },
  team1Score: Number,
  team2Info: {
    id: Number,
    name: String,
    city: String
  },
  team2Score: Number,
  season: Number,
  gameStatus: String,
  gameScheduledStart: String,
  gameId: Number,
});
</script>

<template>
  <div class="game-preview" @click="$router.push({path: '/game/' + gameId})">
    <div>ПРЕВЬЮ ИГРЫ</div>
    <div>{{ gameStatus }}</div>
    <div>{{ gameScheduledStart }}</div>
    <div style="display: flex; flex-direction: row">
      <TeamPreview :season="season" :team-id="team1Info.id"
                   :team-city="team1Info.city" :team-name="team1Info.name"/>
      <div> {{ team1Score }} - {{ team2Score }}</div>
      <TeamPreview :season="season" :team-id="team2Info.id"
                   :team-city="team2Info.city" :team-name="team2Info.name"/>
    </div>
  </div>
</template>

<style scoped>
.game-preview {
  border: 5px solid black;
}

.game-preview:hover {
  background-color:rgba(0, 0, 0, 0.2);
  cursor: pointer;
}
</style>