<script>
import TeamPreview from "@/components/league/TeamPreview.vue";
import PlayerPreview from "@/components/player/PlayerPreview.vue";
import axios from "axios";
import {API} from "@/constants";

export default {
  components: {TeamPreview, PlayerPreview},
  data() {
    return {
      season: 2023, //TODO: hardcoded atm
      teamInfo: {},
      allLoaded: false,
      availableSeasons: [2022, 2023, 2024], // TODO: hardcoded too, fix later
      players: []
    }
  },

  methods: {
    async loadPlayers() {
      if (this.season === new Date().getFullYear())
        this.players = (await axios.get(API + "/players/by_team/" + this.teamInfo.id + "/"
            + new Date().toISOString().slice(0, 10))).data;
      else
        this.players = (await axios.get(API + "/players/by_team-season-end/" + this.teamInfo.id + "/"
            + this.season)).data;
      console.log(this.players);
    },
  },

  async mounted() {
    this.teamInfo = (await axios.get(API + "/teams/" + this.$route.params.teamId)).data;
    await this.loadPlayers();
    this.allLoaded = true;
  },

  watch: {
    season: function(val) {
      this.loadPlayers();
    }
  }
}
</script>

<template v-if="allLoaded">
<div class="team-page">
  <TeamPreview :team-id="teamInfo.id" :team-name="teamInfo.name" :team-city="teamInfo.city" :season="season"/>
  <div>{{ this.teamInfo.description }}</div>
  <select v-model="season" style="height: 50px;">
    <option v-for="season in availableSeasons" v-bind:value="season">
      {{ season }}
    </option>
  </select>
  <span>Сезон: {{ season }}</span>
  <div class="players">
    <div v-for="player in players">
      <PlayerPreview :playerId="player.id" :season="this.season" :first-name="player.firstName"
                     :last-name="player.lastName"
                     :team-id="this.teamInfo.id" :position="player.position" />
    </div>
  </div>
</div>
</template>

<style scoped>

</style>