import { recipes, myBookmarks } from "~/assets/data/db";

export const updateKeyword = ({ commit }, { keyword }) => {
  //RecipesRecommended get : axios.get()으로 변경
  commit("updateKeyword", { keyword });
};

export const getRecipesCookable = ({ commit }, { keyword }) => {
  //RecipesRecommended get : axios.get()으로 변경
  const recipesCookable = recipes;
  commit("getRecipesCookable", { recipesCookable });
};

export const getRecipesRecommended = ({ commit }, { keyword }) => {
  //RecipesRecommended get : axios.get()으로 변경
  const recipesRecommended = recipes;
  commit("getRecipesRecommended", { recipesRecommended });
};

export const getRecipeBookmarks = ({ commit }, { userId }) => {
  //bookmark 최신 버전 get : axios.get()추가
  //userId nuxt-auth에서 받아오도록 수정
  const recipeBookmarks = myBookmarks.filter(bm => bm.userId === "mh9406")[0]
    .bookmarks;

  commit("updateRecipeBookmark", { recipeBookmarks });
};

export const addRecipeBookmark = async (
  { commit, state },
  { recipeId, userId }
) => {
  //bookmark update : axios.post()추가

  //delay 0.2seconds for loading test
  await new Promise(resolve => setTimeout(resolve, 200));

  //bookmark 최신 버전 get : axios.get()추가
  const recipeBookmarks = state.recipeBookmarks.concat(recipeId);
  commit("updateRecipeBookmark", { recipeBookmarks });
};

export const removeRecipeBookmark = async (
  { commit, state },
  { recipeId, userId }
) => {
  //bookmark update : axios.post()추가

  //delay 0.2seconds for loading test
  await new Promise(resolve => setTimeout(resolve, 200));

  //bookmark 최신 버전 get : axios.get()추가
  const recipeBookmarks = state.recipeBookmarks.filter(bm => bm != recipeId);
  commit("updateRecipeBookmark", { recipeBookmarks });
};

export const updateRecommendTime = async (
  { commit, state },
  { time, bool }
) => {
  //추천 옵션 변경
  //RecipesRecommended get : axios.get()으로 변경
  const recommendTime = Object.assign({}, state.recommendTime);
  recommendTime[time] = bool;
  const recipesRecommended = recipes;

  //delay 0.2seconds for loading test
  await new Promise(resolve => setTimeout(resolve, 200));

  commit("getRecipesRecommended", { recipesRecommended });
  commit("updateRecommendTime", { recommendTime })
};

export const updateRecommendKind = async (
  { commit, state },
  { kind, bool }
) => {
  //추천 옵션 변경
  //RecipesRecommended get : axios.get()으로 변경
  const recommendKind = Object.assign({}, state.recommendKind);
  recommendKind[kind] = bool;
  const recipesRecommended = recipes;

  //delay 0.2seconds for loading test
  await new Promise(resolve => setTimeout(resolve, 200));

  commit("getRecipesRecommended", { recipesRecommended });
  commit("updateRecommendKind", { recommendKind })
};

export const updateRecommendTheme = async (
  { commit, state },
  { theme, bool }
) => {
  //추천 옵션 변경
  //RecipesRecommended get : axios.get()으로 변경
  const recommendTheme = Object.assign({}, state.recommendTheme);
  recommendTheme[theme] = bool;
  const recipesRecommended = recipes;

  //delay 0.2seconds for loading test
  await new Promise(resolve => setTimeout(resolve, 200));

  commit("getRecipesRecommended", { recipesRecommended });
  commit("updateRecommendTheme", { recommendTheme })
};

