<script>
import PlayerPreview from "@/components/player/PlayerPreview.vue"
import ContractPreview from "@/components/ContractPreview.vue"

import axios from "axios";
import {VueTable} from "@harv46/vue-table";

export default {
  components: {VueTable, PlayerPreview, ContractPreview},
  data() {
    return {
      season: 2023,
      playerId: this.$route.params.playerId,
      playerInfo: null,
      displayedContract: null,
      contractHistory: [],
      allLoaded: false,
      performancesForEachContractInSeason: [],
      header: [
        "team",
        "T-TOTAL", "PTS-TOTAL", "AST-TOTAL", "REB-TOTAL", "BLK-TOTAL", "2PM-TOTAL", "2PA-TOTAL",
        "T-AVG", "PTS-AVG", "AST-AVG", "REB-AVG", "BLK-AVG", "2PM-AVG", "2PA-AVG",
        "2P%", "3PM", "3PA", "3P%", "FTM", "FTA", "FT%"
      ],
      keys: [
        ["team", "name"],
        ["playerOverTimePerformance", "performance", "totals", "timePlayedInMillis"],
        ["playerOverTimePerformance", "performance", "totals", "points"],
        ["playerOverTimePerformance", "performance", "totals", "assists"],
        ["playerOverTimePerformance", "performance", "totals", "rebounds"],
        ["playerOverTimePerformance", "performance", "totals", "blocks"],
        ["playerOverTimePerformance", "performance", "totals", "twoPointersMade"],
        ["playerOverTimePerformance", "performance", "totals", "twoPointersAttempted"],

        ["playerOverTimePerformance", "performance", "averages", "timePlayedInMillis"],
        ["playerOverTimePerformance", "performance", "averages", "points"],
        ["playerOverTimePerformance", "performance", "averages", "assists"],
        ["playerOverTimePerformance", "performance", "averages", "rebounds"],
        ["playerOverTimePerformance", "performance", "averages", "blocks"],
        ["playerOverTimePerformance", "performance", "averages", "twoPointersMade"],
        ["playerOverTimePerformance", "performance", "averages", "twoPointersAttempted"],

        ["playerOverTimePerformance", "performance", "efficiency", "twoPointEfficiency"],
        ["playerOverTimePerformance", "performance", "totals", "threePointersMade"],
        ["playerOverTimePerformance", "performance", "totals", "threePointersAttempted"],
        ["playerOverTimePerformance", "performance", "efficiency", "threePointEfficiency"],
        ["playerOverTimePerformance", "performance", "totals", "freeThrowsMade"],
        ["playerOverTimePerformance", "performance", "totals", "freeThrowsAttempted"],
        ["playerOverTimePerformance", "performance", "efficiency", "freeThrowEfficiency"]
      ],

      availableSeasons: [],
    }
  },

  methods: {
    async loadSeasonDependentInfo() {
      this.displayedContract = (await axios.get("http://localhost:8080/api/player-contracts/" + this.playerId
          + "/last-in-season/" + this.season)).data;
      this.performancesForEachContractInSeason = (await axios.get("http://localhost:8080/api/stats/season-performance/"
          + this.playerId + "/" + this.season)).data;
    },
  },

  async mounted() {
    this.playerInfo = (await axios.get("http://localhost:8080/api/players/" + this.playerId)).data;
    this.contractHistory = (await axios.get("http://localhost:8080/api/player-contracts/get-all-by-player/"
        + this.playerId)).data;
    (await axios.get("http://localhost:8080/api/stats/seasons-participated/" + this.playerId)).data
        .forEach(season => this.availableSeasons.push({text: season, value: season}));
    await this.loadSeasonDependentInfo();

    this.allLoaded = true;
  },

  watch: {
    season: function (val) {
      this.loadSeasonDependentInfo();
    }
  }
}

</script>

<template>
<div>
  Страница игрока
  <template v-if="allLoaded">
    <div style="display: flex; flex-direction: row;">
      <PlayerPreview :playerId="this.playerId" :season="this.season" :first-name="this.playerInfo.firstName"
                     :last-name="this.playerInfo.lastName"
                     :team-id="displayedContract.team.id" :position="this.playerInfo.position" />
      <ContractPreview :season="this.season" :team-id="displayedContract.team.id"
                       :team-name = displayedContract.team.name
                       :jersey-number="displayedContract.jerseyNumber" :from="displayedContract.validFrom"
                       :to="displayedContract.validTo" />
      <div>{{ this.playerInfo.description }}</div>
      <select v-model="season" style="height: 50px;">
        <option v-for="season in availableSeasons" v-bind:value="season.value">
          {{ season.text }}
        </option>
      </select>
      <span>Сезон: {{ season }}</span>
    </div>
    <div>Статистика игрока</div>
    <div>
      <VueTable :headers="header" :data="performancesForEachContractInSeason" :keys="keys" />
    </div>
    <div>Карьера игрока</div>
    <div>
      <!-- //TODO: season is always this.season, not the actual starting season of the contract, fix it later -->
      <li v-for="contract in contractHistory">
        <ContractPreview :season="this.season" :team-id="contract.team.id"
                         :team-name = contract.team.name
                         :jersey-number="contract.jerseyNumber" :from="contract.validFrom"
                         :to="contract.validTo" />
      </li>
    </div>
  </template>
</div>
</template>

<style scoped>

</style>