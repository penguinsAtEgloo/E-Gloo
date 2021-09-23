export const bookmark = (state, object) => {
  object.bookmarked = !object.bookmarked;
};
export const removeSearch = (state, object) => {
  state.recentSearched.splice(object.index, 1);
  if (state.recentSearched.length === 0) {
    localStorage.removeItem("recentSearched");
  } else {
    localStorage.setItem("recentSearched", state.recentSearched);
  }
};

export const fetchRecentSearch = (state, object) => {
  if (localStorage.getItem("recentSearched") != null) {
    state.recentSearched = localStorage.getItem("recentSearched").split(",");
  }
};
