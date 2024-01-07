<script setup>
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
import { TEAM_MAIN_COLOR_BY_ID } from "@/constants";
</script>

<template>
  <div class="contract-preview">
    <div class="contract-details">
      <div class="team-info">
        <div class="team-image">
          <img
            v-bind:src="'/images/teams/' + season + '/' + teamId + '.png'"
            alt="team image"
            style="width: 200px; height: 200px; border-radius: 180px;"
          />
        </div>
        <div class="text-info">
          <div class="contract-number">
            {{ "#" + jerseyNumber }}
          </div>
          <div class="team-name">
            {{ teamName }}
          </div>
          <div class="contract-duration">
            {{ from + " - " + to }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.contract-preview {
  display: flex;
  align-items: center;
  /* border: 5px solid v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]); */
  width: 500px;
  height: 150px;
}

.contract-preview:hover {
  /* background-color: v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]); */
}

.contract-details {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  width: 100%;
}

.team-info {
  display: flex;
  align-items: center;
  color: white;
}

.text-info {
  margin-left: 10px; /* Добавленный отступ слева для текста */
}

.contract-number,
.team-name,
.contract-duration {
  margin: 5px 0;
  padding: 5px; /* Добавлено для создания пространства вокруг текста */
  background-color: #DD8484; /* Цвет фона */
}
</style>
