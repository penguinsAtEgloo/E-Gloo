<template>
  <div class="wrap">
    <div class="tabs">
      <TabItem
        v-for="item in list"
        v-bind="item"
        :key="item.id"
        v-model="currentId"
        activeColor="linear-gradient(0deg, #0063f7, #0063f7)"
        fontColor="white"
      />
    </div>
    <div class="contents">
      <section class="item" :key="currentId">
        <grid
          v-if="current.content == 'recipe'"
          :component="recipe"
          :list="recipes"
          :size="2"
          :columnGap="1"
          :rowGap="0.5"
          :margin="2"
        />
        <span v-if="current.content == 'item2'">hello</span>
      </section>
    </div>
  </div>
</template>
<script>
import TabItem from "~/components/TabItem";
import Recipe from "~/components/Recipe";
import Grid from "~/components/Grid";
import { mapState } from "vuex";

export default {
  components: { TabItem, Recipe, Grid },
  data() {
    return {
      list: [
        { id: 1, label: "요리 가능 레시피", content: "recipe" },
        { id: 2, label: "추천 레시피", content: "item2" }
      ],
      currentId: 1,
      recipe: Recipe
    };
  },
  computed: {
    current() {
      return this.list.find(el => el.id === this.currentId) || {};
    },
    ...mapState(["recipes"])
  }
};
</script>

<style scoped>
.wrap {
  display: block;
  overflow: hidden;
}
.tabs {
  display: flex;
  justify-content: stretch;
}
.tabs:nth-child(1) {
  border-radius: 5px;
  border: 1px solid #c7c9d9;
}
.contents {
  position: relative;
  overflow: scroll;
}
</style>
