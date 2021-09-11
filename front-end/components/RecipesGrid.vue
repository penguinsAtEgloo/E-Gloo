<template>
  <v-container fluid>
    <v-row>
      <v-col
        v-for="recipe in recipes"
        :key="recipe.recipeId"
        class="d-flex child-flex"
        cols="6"
        style="padding: 4px"
      >
        <v-img
          :src="recipe.recipeImage"
          :lazy-src="recipe.recipeImage"
          aspect-ratio="1"
          class="profile_image"
          position="left"
        >
          <v-icon
            v-if="!bookmarks.includes(recipe.recipeId)"
            @click="addRecipeBookmark(recipe.recipeId)"
            class="recipesGridBookmark"
          >
            mdi-bookmark-outline
          </v-icon>
          <v-icon
            v-else
            @click="removeRecipeBookmark(recipe.recipeId)"
            class="recipesGridBookmark"
          >
            mdi-bookmark
          </v-icon>
          <template v-slot:placeholder>
            <v-row class="fill-height" align="center" justify="center">
              <v-progress-circular
                indeterminate
                color="white"
              ></v-progress-circular>
            </v-row>
          </template>
        </v-img>
        <span class="recipesInfo">{{ recipe.recipeName }}</span>
        <span class="recipesHead">
          <v-icon size="10">mdi-signal-cellular-outline</v-icon>
          {{ recipe.recipeLevel }}&nbsp;
          <v-icon size="10">mdi-clock-outline</v-icon>
          {{ recipe.recipeCookTime }}분</span
        >
        <br />
        <span class="recipesInfo"
          >예상비용 :&nbsp;{{ recipe.recipeBudget }}원~</span
        >
        <br />
        <p style="word-break:keep-all">
          <span
            class="recipesIngredients"
            v-for="ingredient in recipe.ingredients"
            :key="ingredient"
          >
            {{ ingredient }}
          </span>
        </p>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: {
    recipes: {
      type: Array,
      default: () => {
        return [];
      }
    },
    bookmarks: {
      type: Array,
      default: () => {
        return [];
      }
    }
  },
  methods: {
    async addRecipeBookmark(recipeId) {
      await this.$store.dispatch(
        "recipes/addRecipeBookmark",
        {
          userId: "mh9406", //nuxt auth에서 받아오는 것으로 변경
          recipeId
        },
        { root: true }
      );
    },
    async removeRecipeBookmark(recipeId) {
      await this.$store.dispatch(
        "recipes/removeRecipeBookmark",
        {
          userId: "mh9406", //nuxt auth에서 받아오는 것으로 변경
          recipeId
        },
        { root: true }
      );
    }
  }
};
</script>
<style scoped>
.recipesGridBookmark {
  float: right;
  top: 5%;
  right: 5%;

  color: #e57979;
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
}

.recipesIngredients {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 9px;
  line-height: 13px;

  border: 1px solid;
  border-radius: 5px;
  padding: 3px;
  margin-right: 3px;

  color: #f98888;
}
</style>
