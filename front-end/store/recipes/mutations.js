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

export const updateRecommendTime = (state, { recommendTime }) => {
  state.recommendTime = recommendTime;
};

export const updateRecommendKind = (state, { recommendKind }) => {
  state.recommendKind = recommendKind;
};

export const updateRecommendTheme = (state, { recommendTheme }) => {
  state.recommendTheme = recommendTheme;
};