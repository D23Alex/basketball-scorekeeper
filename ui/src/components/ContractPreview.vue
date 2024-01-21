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
import { TEAM_MAIN_COLOR_BY_ID } from "@/constants";
import TeamPreview from "@/components/league/TeamPreview.vue"
export default {
  components: {TeamPreview}
}
</script>

<template>
  <div class="contract-preview">
    <div class="contract-details">
      <div class="team-info">
        <TeamPreview :team-id="teamId" :team-name="teamName" :season="season"/>
        <div class="text-info">
          <div class="contract-number">
            {{ "#" + jerseyNumber }}
          </div>
          <div class="team-name">
            {{ teamName }}
          </div>
          <div class="contract-duration">
            {{ from.split('T')[0] + " - " + to.split('T')[0] }}
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

.team-image:hover {
  box-sizing: border-box;
  box-shadow: inset 0px 0px 0px 3px v-bind(TEAM_MAIN_COLOR_BY_ID[teamId]);
  border-radius: 10px;
  cursor: pointer;
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

.team-image:hover {
  cursor: pointer;
}
</style>

