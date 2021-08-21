export const firstDataFetch = ({ commit }, {userId}) => {
  commit('getUserInfo', {userId});
  commit('checkMyFollowing', {userId});
  commit('getUserRecipes', {userId});
}

export const updateMyFollowing = ({ commit }, {toFollow}) => {
  commit('updateMyFollowing', {toFollow});
}
