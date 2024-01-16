<script setup>
defineProps({
  initialTime: Number
});
</script>

<script>
import { useStopwatch } from 'vue-timer-hook';

export default {
  data() {
    return {
      mainPeriodInSeconds: 600,
      overtimePeriodInSeconds: 300,
      mainTimeInSeconds: 2400,
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

    period() {
      let beforeOverTime = this.secondsSinceStart() < this.mainTimeInSeconds;
      if (beforeOverTime)
        return 1 + Math.floor(this.secondsSinceStart() / this.mainPeriodInSeconds);
      return 5 + Math.floor((this.secondsSinceStart() - this.mainTimeInSeconds) / this.overtimePeriodInSeconds);
    },

    secondsInPeriods(periods) {
      if (periods <= 4)
        return this.mainPeriodInSeconds * periods;
      return this.mainTimeInSeconds + this.overtimePeriodInSeconds * (periods - 4);
    },

    minute() {
      return Math.floor((this.secondsSinceStart() - this.secondsInPeriods(this.period() - 1)) / 60);
    },

    second() {
      return this.secondsSinceStart() % 60;
    },

    periodAsText(period) {
      if (period <= 4)
        return period + ' четверть';
      return period - 4 + 'ОТ';
    }
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
      {{this.periodAsText(this.period()) + ' 0' + this.minute() + ':' + (this.second() >= 10 ? '' : '0') + this.second()}}
    </div>
    <div class="controls">
      <div @click="rewind(period() <= 5 ? this.mainPeriodInSeconds : this.overtimePeriodInSeconds)">
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
      <div @click="toggleTimer()">{{this.stopwatch.isRunning ? 'stop' : 'go'}}</div>
      <div @click="forward(1)">
        +
      </div>
      <div @click="forward(10)">
        >
      </div>
      <div @click="forward(60)">
        >>
      </div>
      <div @click="forward(secondsSinceStart() < 2100 ? this.mainPeriodInSeconds : this.overtimePeriodInSeconds)">
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