<script setup>
import { VueTable  } from "@harv46/vue-table"

</script>

<script>
import axios from "axios";

export default {
  data() {
    return {
      header: ["name", "timePlayed", "points", "assists", "rebounds", "twoPointersMade", "twoPointersAttempted",
        "threePointersMade", "threePointersAttempted", "freeThrowsMade", "freeThrowsAttempted"],
      keys: ["name", "timePlayedInMillis", "points", "assists", "rebounds", "twoPointersMade", "twoPointersAttempted",
        "threePointersMade", "threePointersAttempted", "freeThrowsMade", "freeThrowsAttempted"],
      data: []
    }
  },

  async mounted() {
    this.data = (await axios.get("http://localhost:8080/api/stats/boxscore/" + this.$route.params.gameId)).data;
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