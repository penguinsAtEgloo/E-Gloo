export const isAuthenticated = state => {
  return state.auth.loggedIn;
};

export const loggedInUser = state => {
  return state.auth.user;
};
