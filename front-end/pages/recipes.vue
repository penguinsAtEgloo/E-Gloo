<template>
  <div>
    <v-container style="width: 400px">
      <v-tabs fixed-tabs right style="color: #e57979">
        <v-tab>요리 가능 레시피</v-tab>
        <v-tab>추천 레시피</v-tab>
        <v-tab-item>
          <RecipesGrid
            :recipes="recipesCookable"
            :bookmarks="recipeBookmarks"
          />
          <br />
          <infinite-loading @infinite="infiniteHandler" spinner="spiral">
            <div slot="spinner">레시피 불러오는 중</div>
            <div slot="no-more">레시피가 더 이상 없습니다</div>
            <div slot="no-results">레시피가 더 이상 없습니다</div>
          </infinite-loading>
        </v-tab-item>
        <v-tab-item>
          <RecipesGrid
            :recipes="recipesRecommended"
            :bookmarks="recipeBookmarks"
          />
          <br />
          <infinite-loading @infinite="infiniteHandler" spinner="spiral">
            <div slot="spinner">레시피 불러오는 중</div>
            <div slot="no-more">레시피가 더 이상 없습니다</div>
            <div slot="no-results">레시피가 더 이상 없습니다</div>
          </infinite-loading>
        </v-tab-item>
      </v-tabs>
    </v-container>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import RecipesGrid from "@/components/RecipesGrid";
import InfiniteLoading from "vue-infinite-loading";

export default {
  components: {
    RecipesGrid,
    InfiniteLoading
  },
  async asyncData({ route, store }) {
    const { query } = route;
    const keyword = query.keyword ? query.keyword : "rice";

    await store.dispatch("recipes/updateKeyword", { keyword }, { root: true });
    await store.dispatch(
      "recipes/getRecipesRecommended",
      { keyword },
      { root: true }
    );
    await store.dispatch(
      "recipes/getRecipesCookable",
      { keyword },
      { root: true }
    );

    await store.dispatch("recipes/getRecipeBookmarks", {}, { root: true });
  },
  data() {
    return {
      //페이징 처리를 위한 local state
      recipesCookableOffset: 0,
      recipesRecommendedOffset: 0,
      limit: 20
    };
  },

  props: {},
  computed: {
    ...mapGetters(["isAuthenticated", "loggedInUser"]),
    ...mapState("recipes", [
      "recipesCookable",
      "recipesRecommended",
      "recipeBookmarks"
    ])
  },
  methods: {
    infiniteHandler($state) {
      setTimeout(async () => {
        if (0) {
          //1개 이상의 data fetch 성공
          $state.loaded();
        } else {
          //더 이상 fetch할 data가 없음
          //no-results
          $state.complete();
        }
      }, 500);
    }
  }
};
</script>

<style scoped></style>
