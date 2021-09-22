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
        <br />

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
        <span class="reviewWriter">mh9406</span>
        <br />
        <router-link
          :to="{ path: '/reviews/' + recipe.recipeId }"
          style="text-decoration: none; color: inherit"
          ><span class="recipeName">{{ recipe.recipeName }}</span></router-link
        >
        <br />
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
      },
    },
    bookmarks: {
      type: Array,
      default: () => {
        return [];
      },
    },
  },
  methods: {
    async addRecipeBookmark(recipeId) {
      await this.$store.dispatch(
        "recipes/addRecipeBookmark",
        {
          userId: "mh9406", //nuxt auth에서 받아오는 것으로 변경
          recipeId,
        },
        { root: true }
      );
    },
    async removeRecipeBookmark(recipeId) {
      await this.$store.dispatch(
        "recipes/removeRecipeBookmark",
        {
          userId: "mh9406", //nuxt auth에서 받아오는 것으로 변경
          recipeId,
        },
        { root: true }
      );
    },
  },
};
</script>
<style scoped>
.recipesGridBookmark {
  float: right;
  top: 5%;
  right: 5%;

  color: #e57979;
}
.reviewWriter {
  font-family: Mulish;
  font-style: normal;
  font-weight: bold;
  font-size: 10px;
  line-height: 120%;

  text-align: center;
  letter-spacing: 0.15px;

  color: #4f4f4f;
}
.recipeName {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 10px;
  line-height: 14px;

  color: #4e4e4e;
}
</style>
