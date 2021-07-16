export const isAuthenticated = state => {
  return state.loggedIn;
};

export const loggedInUser = state => {
  return state.userId;
};
