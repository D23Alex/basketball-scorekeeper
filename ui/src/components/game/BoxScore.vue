<script setup>
import { VueTable  } from "@harv46/vue-table"


defineProps({
  lineup: Array,
  gameEventLog: Object
})
</script>

<script>

import stats from "@/stats";

export default {
  data() {
    return {
      header: ["name", "points", "assists", "rebounds"],
      keys: ["name", "points", "assists", "rebounds"],
      data: []
    }
  },

  mounted() {
    let tableData = this.lineup.map(player => stats.gameStats(player, this.gameEventLog));
    tableData.forEach(tableRow => tableRow.name = tableRow.player.firstName + " " + tableRow.player.lastName);
    this.data = tableData;
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