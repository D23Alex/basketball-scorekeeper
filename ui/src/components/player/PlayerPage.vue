<script>
import PlayerPreview from "@/components/player/PlayerPreview.vue"
import ContractPreview from "@/components/ContractPreview.vue"

import axios from "axios";
import {VueTable} from "@harv46/vue-table";
import {API} from "@/constants";

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
        "TEAM",
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
      this.displayedContract = (await axios.get(API + "/player-contracts/" + this.playerId
          + "/last-in-season/" + this.season)).data;
      this.performancesForEachContractInSeason = (await axios.get(API + "/stats/season-performance/"
          + this.playerId + "/" + this.season)).data;
    },
  },

  async mounted() {
    this.playerInfo = (await axios.get(API + "/players/" + this.playerId)).data;
    this.contractHistory = (await axios.get(API + "/player-contracts/get-all-by-player/"
        + this.playerId)).data;
    (await axios.get(API + "/stats/seasons-participated/" + this.playerId)).data
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
  <div class="outer">
    <template v-if="allLoaded">
      <div class="general-player-info">
        <PlayerPreview
          :playerId="this.playerId"
          :season="this.season"
          :first-name="this.playerInfo.firstName"
          :last-name="this.playerInfo.lastName"
          :team-id="displayedContract.team.id"
          :position="this.playerInfo.position"
        />
        <ContractPreview
          :season="this.season"
          :team-id="displayedContract.team.id"
          :team-name="displayedContract.team.name"
          :jersey-number="displayedContract.jerseyNumber"
          :from="displayedContract.validFrom"
          :to="displayedContract.validTo"
        />
      </div>

      <select v-model="season" style="height: 50px;">
        <option v-for="season in availableSeasons" v-bind:value="season.value">
          {{ season.text }}
        </option>
      </select>

      <div style="padding-top: 40px;">
        <div class="section-title">
          <strong>Статистика игрока</strong>
        </div>
        <div>
          <VueTable :headers="header" :data="performancesForEachContractInSeason" :keys="keys" />
        </div>
      </div>

      <div style="padding-top: 60px;">
        <div class="section-title"><strong>Карьера игрока</strong></div>
        <div style="padding-top: 60px;">
          <ul class="contract-list">
            <li v-for="contract in contractHistory" class="contract-item">
              <ContractPreview
                :season="this.season"
                :team-id="contract.team.id"
                :team-name="contract.team.name"
                :jersey-number="contract.jerseyNumber"
                :from="contract.validFrom"
                :to="contract.validTo"
              />
            </li>
          </ul>
        </div>
      </div>
    </template>
  </div>
</template>


<style scoped>
  .general-player-info {
    display: flex;
    align-items: center; 
    justify-content: space-between;
  }

  .outer {
    background-color: #D9D9D9;
    height: 480px;
    width: 1200px;
    padding: 50px;
    overflow: auto;
  }

  
  .player-preview {
    margin-right: 10px; 
  }

  
  .contract-preview {
    margin-left: 10px;
  }


  select {
    margin-right: 10px; 
  }

  .section-title {
    text-align: center;
    margin-bottom: 20px;
    font-size: 1.5em;
  }

  .contract-list {
    padding: 0;
    list-style: none;
    display: flex;
    flex-direction: column;
    align-items: center; 
  }

  .contract-item {
    margin-bottom: 60px;
  }

  
  .contract-item img {
    max-height: 100%;
    width: auto; 
  }
</style>
