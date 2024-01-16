<script setup>
import {RULES} from "@/constants";
import {periodsInSeconds, prettyGameTimestampBySecondsSinceStart} from "@/util";

defineProps({
  initialTime: Number
});
</script>

<script>
import { useStopwatch } from 'vue-timer-hook';
import {RULES} from "@/constants";
import {periodsInSeconds} from "@/util";

export default {
  data() {
    return {
      stopwatch: useStopwatch(this.initialTime !== undefined ? this.initialTime : 0, false),
    }
  },
  methods: {
    useStopwatch,

    toggleTimer() {
      if (this.stopwatch.isRunning)
        this.stopwatch.pause();
      else this.stopwatch.start();
    },

    setTime(seconds) {
      this.stopwatch = useStopwatch(Math.max(0, seconds), this.stopwatch.isRunning);
    },

    forward(seconds) {
      this.setTime(this.secondsSinceStart() + seconds);
    },

    rewind(seconds) {
      this.forward(-1 * seconds);
    },

    secondsSinceStart() {
      return (this.stopwatch.minutes) * 60 + this.stopwatch.seconds;
    },
  },

  watch: {
    "stopwatch.seconds" : function (val) {
      this.$emit('selectedtimechanged', this.secondsSinceStart());
    },
    "stopwatch.minutes" : function (val) {
      this.$emit('selectedtimechanged', this.secondsSinceStart());
    },
  }
}
</script>

<template>
  <div class="timer">
    <div class="display">
      {{ prettyGameTimestampBySecondsSinceStart(secondsSinceStart()) }}
    </div>
    <div class="controls">
      <div @click="rewind(periodsInSeconds(secondsSinceStart()) <= RULES.mainPeriods + 1 ?
        RULES.mainPeriodInSeconds : RULES.overtimePeriodInSeconds)">
        |&lt;
      </div>
      <div @click="rewind(60)">
        &lt;&lt;
      </div>
      <div @click="rewind(10)">
        &lt;
      </div>
      <div @click="rewind(1)">
        -
      </div>
      <div @click="toggleTimer()">{{stopwatch.isRunning ? 'stop' : 'go'}}</div>
      <div @click="forward(1)">
        +
      </div>
      <div @click="forward(10)">
        >
      </div>
      <div @click="forward(60)">
        >>
      </div>
      <div @click="forward(secondsSinceStart() < 2100 ? RULES.mainPeriodInSeconds : RULES.overtimePeriodInSeconds)">
        >|
      </div>
    </div>
  </div>
</template>

<style scoped>
.controls, .display {
  display: flex;
  flex-direction: row;
}

.timer {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.controls > div {
  border: 1px solid black;
}

.controls > div:hover {
  cursor: pointer;
  background-color: red;
}
</style>