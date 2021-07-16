<template>
  <div id="recipe-gallery">
    <div id="recipes">
      <span v-for="recipe in recipes" :key="recipe.id">
        <recipe :recipe="recipe"></recipe>
      </span>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState(["recipes"])
  }
};
</script>
<style scoped>
#recipes {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(44vmin, auto));
  grid-gap: min(0.8vmin, 4px);
  justify-content: center;
  padding: 0;
}
#recipes span {
  text-align: center;
  padding: 0;
}
</style>
