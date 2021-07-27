<template>
  <div class="grid" :style="cssVars">
    <span v-for="item in list" :key="item.id">
      <component :is="component" :item="item"></component>
    </span>
  </div>
</template>
<script>
export default {
  props: {
    list: {
      type: Array,
      default: () => {
        return [];
      }
    },
    size: {
      default: 1
    },
    columnGap: {
      default: 0
    },
    rowGap: {
      default: 0
    },
    margin: {
      default: 0
    },
    component: {
      type: Object,
      default: () => {
        return {};
      }
    }
  },
  computed: {
    cssVars() {
      return {
        "--grid-size": this.size,
        "--grid-size-md": this.size + 1,
        "--grid-size-lg": this.size + 2,
        "--item-size": this.itemSize,
        "--column-gap": this.columnGap + "%",
        "--row-gap": this.rowGap + "%",
        "--margin": this.margin + "%"
      };
    }
  }
};
</script>
<style scoped>
.grid {
  display: grid;
  grid-template-columns: repeat(var(--grid-size), 1fr);
  column-gap: var(--column-gap);
  row-gap: var(--row-gap);
  justify-content: space-between;
  margin: 0.1rem var(--margin);
}
span {
  place-self: center;
  width: 100%;
  height: 100%;
}

@media only screen and (min-width: 48rem) {
  .grid {
    display: grid;
    grid-template-columns: repeat(var(--grid-size-md), 1fr);
    column-gap: var(--column-gap);
    row-gap: var(--row-gap);
    justify-content: space-between;
    margin: 0.1rem var(--margin);
  }
  span {
    place-self: center;
    width: 100%;
    height: 100%;
  }
}

@media only screen and (min-width: 69rem) {
  .grid {
    display: grid;
    grid-template-columns: repeat(var(--grid-size-lg), 1fr);
    column-gap: var(--column-gap);
    row-gap: var(--row-gap);
    justify-content: space-between;
    margin: 0.1rem var(--margin);
  }
  span {
    place-self: center;
    width: 100%;
    height: 100%;
  }
}
</style>
