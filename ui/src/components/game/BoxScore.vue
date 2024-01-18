<script setup>
import { VueTable } from "@harv46/vue-table";
import "@harv46/vue-table/dist/style.css"
</script>

<script>
import axios from "axios";
import { API } from "@/constants";
import {timePlayedInMinutesAndSeconds} from "@/util";

export default {
  data() {
    return {
      header: ["name", "T", "PTS", "AST", "REB", "BLK", "2PM", "2PA", "2P%", "3PM", "3PA", "3P%", "FTM", "FTA", "FT%"],
      keys: ["name",
        "time",
        ["performance", "totals", "points"],
        ["performance", "totals", "assists"],
        ["performance", "totals", "rebounds"],
        ["performance", "totals", "blocks"],
        ["performance", "totals", "twoPointersMade"],
        ["performance", "totals", "twoPointersAttempted"],
        "twoPointEfficiency",
        ["performance", "totals", "threePointersMade"],
        ["performance", "totals", "threePointersAttempted"],
        "threePointEfficiency",
        ["performance", "totals", "freeThrowsMade"],
        ["performance", "totals", "freeThrowsAttempted"],
        "freeThrowEfficiency"],
      data: []
    };
  },

  async mounted() {
    this.data = (await axios.get(API + "/stats/boxscore/" + this.$route.params.gameId)).data;
    this.data.forEach(row => row.name = row.player.firstName + " " + row.player.lastName);
    this.data.forEach(row => row.twoPointEfficiency = (row.performance.efficiency.twoPointEfficiency * 100).toFixed(1) + "%");
    this.data.forEach(row => row.threePointEfficiency = (row.performance.efficiency.threePointEfficiency * 100).toFixed(1) + "%");
    this.data.forEach(row => row.freeThrowEfficiency = (row.performance.efficiency.freeThrowEfficiency * 100).toFixed(1) + "%");
    this.data.forEach(row => row.time = timePlayedInMinutesAndSeconds(Math.floor(row.performance.totals.timePlayedInMillis / 1000)));
  }
}
</script>

<template>
  <div>
    <VueTable :headers="header" :data="data" :keys="keys" class="custom-vue-table" />
  </div>
</template>

<style scoped>
/* Стили VueTable */
.custom-vue-table {
  border-collapse: collapse;
  width: 100%;
  font-family: 'Arial', sans-serif;
  background-color: #fff; /* Белый фон */
  //border: 2px solid #333; /* Границы таблицы */
  border-radius: 8px;
  overflow: hidden;
}

</style>
