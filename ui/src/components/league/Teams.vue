<script>
import axios from "axios";
import TeamPreview from "@/components/league/TeamPreview.vue";
import {API} from "@/constants";

export default {
  components: {TeamPreview},
  data() {
    return {
      teams: [],
      season: 2023 // hardcoded for now
    }
  },

  async mounted() {
    this.teams = (await axios.get(API + "/teams/get_all")).data;
  }
}
</script>

<template>

  <div class="team-holder-outer">
    <div class="team-holder">
      <li v-for="team in teams">
        <TeamPreview :season="this.season" :team-id="team.id" :team-city="team.city" :team-name="team.name"/>
      </li>  
    </div>
  </div>

</template>

<style scoped>
  .team-holder-outer {
    background-color: #D9D9D9;

    height: 700px;
    width: 100%;

    padding: 50px;
    overflow: auto;
  }

  .team-holder {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px;
  }

  .team-holder li {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
  }

  .team-image {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    overflow: hidden;
  }

.team-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

</style>