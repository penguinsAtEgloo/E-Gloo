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
