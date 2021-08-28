import { users, recipes, myFollowings } from "~/assets/data/db"

export const firstDataFetch = ({ dispatch }, { userId }) => {
  dispatch('getUserInfo', { userId });
  dispatch('checkMyFollowing', { userId });
  dispatch('getUserRecipes', { userId });
}

export const getUserInfo = ({ commit }, { userId }) => {
  //userInfo get : axios.get()으로 변경
  const selectedUser = users.filter((user) => user.userId == userId)[0];
  commit('getUserInfo', { selectedUser });
};

export const checkMyFollowing = ({ commit }, { userId }) => {
  //나의 follow 여부 get : axios.get()으로 변경
  const myFollowing = myFollowings.filter((myFollowing) => myFollowing.userId == userId)[0].follow;
  commit('checkMyFollowing', { myFollowing });
};

export const getUserRecipes = ({ commit }, { userId }) => {
  //userRecipes get : axios.get()으로 변경
  const userRecipes = recipes.filter((recipe) => recipe.userId == userId);
  commit('getUserRecipes', { userRecipes });
};

export const updateMyFollowing = async ({ commit }, { toFollow }) => {
  //myFollowing update : axios.post()추가
  //delay 2seconds for loading test
  await new Promise(resolve => setTimeout(resolve, 1000));

  commit('updateMyFollowing', { toFollow });
}
