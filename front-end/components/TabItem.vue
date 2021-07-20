<template>
  <div class="container">
    <button @click="$emit('input', id)" :class="[active, 'tab']">
      {{ label }}
    </button>
  </div>
</template>

<script>
export default {
  props: {
    id: Number,
    label: String,
    value: Number
  },
  computed: {
    active() {
      return this.value === this.id ? "active" : false;
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  width: 100%;
  height: 40px;
}
.tab {
  color: black;
  background: white;
  border-color: transparent;
  display: block;
  width: 100%;
  font-size: 12px;
  line-height: 17.38px;
}

.tabs:nth-child(1) {
  border-radius: 5vmin;
}

.active {
  background: linear-gradient(0deg, #0063f7, #0063f7);
  color: white;
}
</style>
