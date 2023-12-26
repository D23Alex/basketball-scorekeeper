<script setup>
import { VueTable  } from "@harv46/vue-table"

</script>

<script>
import axios from "axios";
import {API} from "@/constants";

export default {
  data() {
    return {
      header: ["name", "T", "PTS", "AST", "REB", "BLK", "2PM", "2PA", "2P%", "3PM", "3PA", "3P%", "FTM", "FTA", "FT%"],
      keys: ["name",
        ["performance", "totals", "timePlayedInMillis"],
        ["performance", "totals", "points"],
        ["performance", "totals", "assists"],
        ["performance", "totals", "rebounds"],
        ["performance", "totals", "blocks"],
        ["performance", "totals", "twoPointersMade"],
        ["performance", "totals", "twoPointersAttempted"],
        ["performance", "efficiency", "twoPointEfficiency"],
        ["performance", "totals", "threePointersMade"],
        ["performance", "totals", "threePointersAttempted"],
        ["performance", "efficiency", "threePointEfficiency"],
        ["performance", "totals", "freeThrowsMade"],
        ["performance", "totals", "freeThrowsAttempted"],
        ["performance", "efficiency", "freeThrowEfficiency"]],
      data: []
    }
  },

  async mounted() {
    this.data = (await axios.get(API + "/stats/boxscore/" + this.$route.params.gameId)).data;
    this.data.forEach(row => row.name = row.player.firstName + " " + row.player.lastName);
  }
}
</script>

<template>
  <div>
    <VueTable :headers="header" :data="data" :keys="keys" />
  </div>
</template>

<style scoped>

</style>