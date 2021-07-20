<template>
  <div class="wrap">
    <div class="tabs">
      <TabItem
        v-for="item in list"
        v-bind="item"
        :key="item.id"
        v-model="currentId"
      />
    </div>
    <div class="contents">
      <section class="item" :key="currentId">
        <recipe-gallery v-if="current.content == 'recipe'" />
        <span v-if="current.content == 'item2'">hello</span>
      </section>
    </div>
  </div>
</template>
<script>
import TabItem from "~/components/TabItem";
import RecipeGallery from "~/components/RecipeGallery";

export default {
  components: { TabItem, RecipeGallery },
  data() {
    return {
      list: [
        { id: 1, label: "요리 가능 레시피", content: "recipe" },
        { id: 2, label: "추천 레시피", content: "item2" }
      ],
      currentId: 1
    };
  },
  computed: {
    current() {
      return this.list.find(el => el.id === this.currentId) || {};
    }
  }
};
</script>

<style scoped>
.wrap {
  display: block;
  /* margin: 2vmin; */
  padding: 1em min(3vmin, 17px);
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
  overflow: hidden;
}
.item {
  width: 100%;
  transition: all 0.8s ease;
}
</style>
