export const updateKeyword = (state, { keyword }) => {
  state.keyword = keyword;
};

export const getRecipesCookable = (state, { recipesCookable }) => {
  state.recipesCookable = recipesCookable;
};

export const getRecipesRecommended = (state, { recipesRecommended }) => {
  state.recipesRecommended = recipesRecommended;
};

export const updateRecipeBookmark = (state, { recipeBookmarks }) => {
  state.recipeBookmarks = recipeBookmarks;
};
