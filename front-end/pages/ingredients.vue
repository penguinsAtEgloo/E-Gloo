<template>
  <div>
    <div class="wrap">
      <div class="tabs">
        <TabItem
          v-for="item in list"
          v-bind="item"
          :key="item.id"
          v-model="currentId"
        />
      </div>
    </div>
    <div class="contents">
      <section :key="currentId">
        <grid
          v-if="current.content == 'ingredients'"
          :component="ingredient"
          :list="ingredients"
          :size="4"
          :columnGap="2"
          :rowGap="0.5"
          :margin="2"
        />
        <span v-if="current.content != 'ingredients'">{{
          current.content
        }}</span>
      </section>
    </div>
  </div>
</template>
<script>
import TabItem from "~/components/TabItem";
import Ingredient from "~/components/Ingredient";
import Grid from "~/components/Grid";
import { mapState } from "vuex";

export default {
  components: { TabItem, Ingredient, Grid },
  data() {
    return {
      list: [
        { id: 1, label: "쌀/잡곡", content: "ingredients" },
        { id: 2, label: "과일", content: "item2" },
        { id: 3, label: "채소", content: "item3" },
        { id: 4, label: "정육/계란", content: "item4" },
        { id: 5, label: "우유/유제품", content: "item5" }
      ],
      currentId: 1,
      ingredient: Ingredient
    };
  },
  computed: {
    current() {
      return this.list.find(el => el.id === this.currentId) || {};
    },
    ...mapState(["ingredients"])
  }
};
</script>

<style scoped>
.wrap {
  border-bottom: 0.06rem solid #e0e0e0;
  margin-bottom: 0.56rem;
}
.tabs {
  margin: 0 1.63rem -0.06rem 1.63rem;
  display: flex;
  justify-content: stretch;
}
.contents {
  position: relative;
  overflow: scroll;
}
</style>
