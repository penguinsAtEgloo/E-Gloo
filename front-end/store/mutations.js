export const LOGIN = (state, userId) => {
  state.loggedIn = true;
  state.userId = userId;
};

export const LOGOUT = state => {
  state.loggedIn = false;
  state.userId = null;
  this.$router.push("/");
};

export const bookmark = (state, recipe) => {
  recipe.bookmarked = !recipe.bookmarked;
};
