<script setup>
import { RULES } from "@/constants";
import {
  periodsInSeconds,
  prettyGameTimestampBySecondsSinceStart,
} from "@/util";

defineProps({
  initialTime: Number,
});
</script>

<script>
import { useStopwatch } from "vue-timer-hook";
import { RULES } from "@/constants";
import { periodsInSeconds } from "@/util";

export default {
  data() {
    return {
      stopwatch: useStopwatch(
        this.initialTime !== undefined ? this.initialTime : 0,
        false
      ),
    };
  },
  methods: {
    useStopwatch,

    toggleTimer() {
      if (this.stopwatch.isRunning) this.stopwatch.pause();
      else this.stopwatch.start();
    },

    setTime(seconds) {
      this.stopwatch = useStopwatch(
        Math.max(0, seconds),
        this.stopwatch.isRunning
      );
    },

    forward(seconds) {
      this.setTime(this.secondsSinceStart() + seconds);
    },

    rewind(seconds) {
      this.forward(-1 * seconds);
    },

    secondsSinceStart() {
      return this.stopwatch.minutes * 60 + this.stopwatch.seconds;
    },
  },

  watch: {
    "stopwatch.seconds": function (val) {
      this.$emit("selectedtimechanged", this.secondsSinceStart());
    },
    "stopwatch.minutes": function (val) {
      this.$emit("selectedtimechanged", this.secondsSinceStart());
    },
  },
};
</script>

<template>
  <div class="timer">
    <div class="display">
      {{ prettyGameTimestampBySecondsSinceStart(secondsSinceStart()) }}
    </div>
    <div class="controls">
      <div
        @click="
          rewind(
            periodsInSeconds(secondsSinceStart()) <= RULES.mainPeriods + 1
              ? RULES.mainPeriodInSeconds
              : RULES.overtimePeriodInSeconds
          )
        "
        class="control-btn"
      >
        |&lt;
      </div>
      <div @click="rewind(60)" class="control-btn">&lt;&lt;</div>
      <div @click="rewind(10)" class="control-btn">&lt;</div>
      <div @click="rewind(1)" class="control-btn">-</div>
      <div @click="toggleTimer()" class="control-btn">
        {{ stopwatch.isRunning ? "stop" : "go" }}
      </div>
      <div @click="forward(1)" class="control-btn">+</div>
      <div @click="forward(10)" class="control-btn">></div>
      <div @click="forward(60)" class="control-btn">>></div>
      <div
        @click="
          forward(
            secondsSinceStart() < 2100
              ? RULES.mainPeriodInSeconds
              : RULES.overtimePeriodInSeconds
          )
        "
        class="control-btn"
      >
        >|
      </div>
    </div>
  </div>
</template>

<style scoped>
.controls {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.control-btn {
  border: none;
  background-color: #4caf50;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 8px;
}

.control-btn:hover {
  background-color: #45a049;
}

.display {
  font-size: 20px;
  margin-bottom: 20px;
}

.timer {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
