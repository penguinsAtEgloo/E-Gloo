<template>
  <div>
    <section>
      <div style="margin:1em">
        <search-bar></search-bar>
      </div>
    </section>
    <section class="display1">
      <h3>오늘은 뭘 먹을까?</h3>
      <h5 class="hashtag">#말복에도 #이글루와함께 #어쩌고저쩌고 #어쩌고</h5>
      <horizontal-scroll-dp
        :list="recipesCookable"
        itemWidth="200px"
        itemHeight="200px"
        margin="1em"
      ></horizontal-scroll-dp>
    </section>
    <section class="display2">
      <div>
        <h3>맞춤레시피 추천</h3>
        <h6>좋아하는 레시피를 바탕으로 요리법을 추천할게요!</h6>
        <horizontal-scroll-dp
          :list="recipesRecommended"
          isRecipe="true"
          itemWidth="160px"
          itemHeight="160px"
          margin="1em"
        ></horizontal-scroll-dp>
      </div>
    </section>
    <section class="display2">
      <div>
        <h3>베스트 스토리</h3>
        <horizontal-scroll-dp
          :list="recipesRecommended"
          itemWidth="100px"
          itemHeight="183px"
          borderRadius="10px"
          ,
          margin="1em"
        ></horizontal-scroll-dp>
      </div>
    </section>
  </div>
</template>

<script>
import SearchBar from "@/components/SearchBar";
import HorizontalScrollDp from "../components/HorizontalScrollDp.vue";
import { mapState } from "vuex";
export default {
  components: {
    SearchBar,
    HorizontalScrollDp
  },
  async asyncData({ route, store }) {
    const { query } = route;
    const keyword = query.keyword ? query.keyword : "rice";
    await store.dispatch("recipes/updateKeyword", { keyword }, { root: true });
    await store.dispatch("recipes/getRecipesRecommended", { root: true });
    await store.dispatch(
      "recipes/getRecipesCookable",
      { keyword },
      { root: true }
    );
  },

  computed: {
    ...mapState("recipes", ["recipesCookable", "recipesRecommended"])
  }
};
</script>
<style>
.display1 {
  border: 1px #e0e0e0;
  border-style: solid none;
  background: #f8f8f8;
  padding: 0 0 1rem 1.5rem;
}
.display2 {
  padding: 0 0 1rem 1.5rem;
}
h3 {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 20px;
  line-height: 29px;
  color: #4f4f4f;
  padding: 15px 0;
}
h5 {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 15px;
  line-height: 22px;
  padding: 0 0 8px 0;

  color: #a5a5a5;
}
h6 {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 12px;
  line-height: 17.38px;
  color: #a5a5a5;
  padding: 0 0 8px 0;
}
</style>
