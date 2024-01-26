<script>
import axios from "axios";
import { API } from "@/constants";
import GamePreview from "@/components/game/GamePreview.vue";

export default {
  components: { GamePreview },
  data() {
    return {
      season: 2023,
      gamePreviews: [],
      allLoaded: false,
      availableSeasons: [2022, 2023, 2024],
    };
  },

  methods: {
    async loadSeasonDependentInfo() {
      let games = (
        await axios.get(API + "/games/all-in-season/" + this.season)
      ).data;

      this.gamePreviews = [];
      for (let i = 0; i < games.length; i++) {
        this.gamePreviews.push({
          game: games[i],
          team1Performance: (
            await axios.get(
              API +
                "/stats/team-single-game-performance/" +
                games[i].team1.id +
                "/" +
                games[i].id
            )
          ).data.performance,
          team2Performance: (
            await axios.get(
              API +
                "/stats/team-single-game-performance/" +
                games[i].team2.id +
                "/" +
                games[i].id
            )
          ).data.performance,
          gameStatus: (
              await axios.get(
                  API +
                  "/games/status/" +
                  games[i].id
              )
          ).data,
        });
      }
    },
  },

  async mounted() {
    await this.loadSeasonDependentInfo();
    this.allLoaded = true;
  },

  watch: {
    season: function (val) {
      this.loadSeasonDependentInfo();
    },
  },
};
</script>

<template>
  <div>
    <select v-model="season" style="height: 50px;">
      <option v-for="season in availableSeasons" :key="season" :value="season">
        {{ season }}
      </option>
    </select>
    <div class="game-previews" style="width: 1300px;">
      <template v-if="allLoaded">
        <div v-for="preview in gamePreviews" :key="preview.game.id" class="game-preview-container">
          <GamePreview
            :season="season"
            :team1-score="preview.team1Performance.totals.points"
            :team2-score="preview.team2Performance.totals.points"
            :game-status="preview.gameStatus"
            :team1-info="preview.game.team1"
            :team2-info="preview.game.team2"
            :game-scheduled-start="preview.game.scheduledStartTime"
            :game-id="preview.game.id"
          />
        </div>
      </template>
    </div>
  </div>
</template>

<style scoped>
.game-previews {
  display: flex;
  flex-wrap: wrap;
  align-items: flex-start;
  justify-content: space-between;
}

.game-preview-container {
  width: calc(33.33% - 10px);
  margin-bottom: 10px;
}
</style>
