<script>
import axios from "axios";
import {API} from "@/constants";
import GamePreview from "@/components/game/GamePreview.vue";

export default {
  components: {GamePreview},
  data() {
    return {
      season: 2023,
      gamePreviews: [],
      allLoaded: false,
      availableSeasons: [2022, 2023, 2024],

    }
  },

  methods: {
    async loadSeasonDependentInfo() {
      let games = (await axios.get(API + "/games/all-in-season/" + this.season)).data;

      this.gamePreviews = [];
      for (let i = 0; i < games.length; i++) {
        this.gamePreviews.push({
          game: games[i],
          team1Performance: (await axios.get(API + "/stats/team-single-game-performance/"
              + games[i].team1.id + "/" + games[i].id)).data.performance,
          team2Performance: (await axios.get(API + "/stats/team-single-game-performance/"
              + games[i].team2.id + "/" + games[i].id)).data.performance,
        });
      }
    }
  },

  async mounted() {
    await this.loadSeasonDependentInfo();
    this.allLoaded = true;
  },

  watch: {
    season: function(val) {
      this.loadSeasonDependentInfo();
    }
  }
}
</script>

<template>
  <select v-model="season" style="height: 50px;">
    <option v-for="season in availableSeasons" v-bind:value="season">
      {{ season }}
    </option>
  </select>
  <span>Сезон: {{ season }}</span>
  <div class="game=previews">
    <template v-if="allLoaded">
      <div v-for="preview in this.gamePreviews">
        <GamePreview :season="this.season" :team1-score="preview.team1Performance.totals.points"
                     :team2-score="preview.team2Performance.totals.points" :game-status="'TODO: статус игры'"
                     :team1-info="preview.game.team1" :team2-info="preview.game.team2"
                     :game-scheduled-start="preview.game.scheduledStartTime"
                     :game-id="preview.game.id"/>
      </div>
    </template>
  </div>
</template>

<style scoped>
.game-previews {
  display: flex;
  flex-wrap: wrap;
  align-items: flex-start;
}
</style>