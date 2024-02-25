<script setup>
import TeamPreviewMini from "@/components/league/TeamPreviewMini.vue";
import { GAME_STATUS_TRANSLATION } from "../../constants";

let props = defineProps({
  team1Info: {
    id: Number,
    name: String,
    city: String,
  },
  team1Score: Number,
  team2Info: {
    id: Number,
    name: String,
    city: String,
  },
  team2Score: Number,
  season: Number,
  gameStatus: String,
  gameScheduledStart: String,
  gameId: Number,
});
</script>

<template>
  <div class="game-preview" @click="$router.push({ path: '/game/' + gameId })">
    <div class="status">{{ GAME_STATUS_TRANSLATION[gameStatus] }}</div>
    <div class="scheduled-start">{{ gameScheduledStart.split("T")[0] }}</div>
    <div class="teams-scores">
      <TeamPreviewMini
        :season="season"
        :team-id="team1Info.id"
        :team-city="team1Info.city"
        :team-name="team1Info.name"
      />
      <div class="score">{{ team1Score }}</div>
      <div class="score-separator">-</div>
      <div class="score">{{ team2Score }}</div>
      <TeamPreviewMini
        :season="season"
        :team-id="team2Info.id"
        :team-city="team2Info.city"
        :team-name="team2Info.name"
      />
    </div>
  </div>
</template>

<style scoped>
.game-preview {
  flex-shrink: 0;
  border: 6px solid #3498db; /* Blue border */
  border-radius: 8px;
  padding: 4px;
  transition: background-color 0.3s;
}

.game-preview:hover {
  background-color: rgba(
    52,
    152,
    219,
    0.1
  ); /* Light blue background on hover */
  cursor: pointer;
}

.status {
  font-size: 1.2em;
  margin-bottom: 5px;
}

.scheduled-start {
  color: #888;
  margin-bottom: 20px; /* Add margin to create space below the scheduled start */
}

.teams-scores {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

/* Adjust the margin for horizontal spacing between TeamPreview and scores */
.score,
.score-separator {
  font-size: 1em;
  margin: 0 5px; /* Add margin for horizontal spacing between scores and TeamPreview */
}

.score {
  font-weight: bold;
}
</style>
