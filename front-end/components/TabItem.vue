<template>
  <div class="container" :style="cssVars">
    <div @click="$emit('input', id)" :class="[active, 'tab']">
      <span>{{ labelLonger }}</span>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    id: Number,
    label: String,
    value: Number,
    activeColor: String,
    fontColor: String
  },
  computed: {
    active() {
      return this.value === this.id ? "active" : false;
    },
    cssVars() {
      return {
        "--border-style": this.activeColor ? "none" : "solid",
        "--border-bottom-color": this.activeColor ? "none" : "#2F80ED",
        "--active-color": this.activeColor ? this.activeColor : "transparent",
        "--font-color": this.fontColor ? this.fontColor : "black",
        "--width": this.fontColor ? "100%" : "fit-content"
      };
    },
    labelLonger() {
      return this.fontColor
        ? this.label
        : "\xa0\xa0\xa0" + this.label + "\xa0\xa0\xa0";
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  width: var(--width);
  text-align: center;
}
.tab {
  display: flex;
  color: black;
  background: transparent;
  border-color: transparent;
  font-size: 0.75rem;
  width: 100%;
  line-height: 2rem;
  justify-content: center;
}
.active {
  background: var(--active-color);
  color: var(--font-color);
  border-bottom-style: var(--border-style);
  border-bottom-color: var(--border-bottom-color);
  border-width: 0.13rem;
}
span {
  display: inline-flex;
  align-items: center;
}
</style>
