<template>
  <div :style="cssVars" class="horizontal-scroll-wrapper">
    <div v-for="item in list" :key="item.recipeId" class="wrap">
      <div
        class="item"
        :style="{ backgroundImage: 'url(' + item.recipeImage + ')' }"
      ></div>
      <div v-if="isRecipe" class="info">
        <span class="recipesInfo">{{ item.recipeName }}</span>
        <span class="recipesHead">
          <v-icon size="10">mdi-signal-cellular-outline</v-icon>
          {{ item.recipeLevel }}&nbsp;
          <v-icon size="10">mdi-clock-outline</v-icon>
          {{ item.recipeCookTime }}</span
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    list: [],
    itemWidth: "",
    itemHeight: "",
    margin: "",
    borderRadius: "",
    isRecipe: ""
  },
  computed: {
    cssVars() {
      return {
        "--item-width": this.itemWidth,
        "--item-height": this.itemHeight,
        "--item-margin": this.margin,
        "--border-radius": this.borderRadius
      };
    }
  }
};
</script>
<style scoped>
::-webkit-scrollbar {
  width: 0px;
  height: 0px;
}
::-webkit-scrollbar-button {
  width: 0px;
  height: 0px;
}
.horizontal-scroll-wrapper {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  overflow-x: auto;
  overflow-y: visible;
}
.item {
  width: var(--item-width);
  height: var(--item-height);
  margin-right: var(--item-margin);
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  flex-grow: 0;
  flex-shrink: 0;
  border-radius: var(--border-radius);
}
.item:last-child {
  margin-right: var(--item-margin);
}
.info {
  position: relative;
}
.recipesInfo {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 10px;
  line-height: 14px;
  color: #4e4e4e;
}
.recipesHead {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 9px;
  line-height: 13px;
  color: #8c8585;
  position: relative;
}
</style>
