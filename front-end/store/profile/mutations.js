import {users, recipes, myFollowings} from "~/assets/data/db"

export const getUserInfo = (state, {userId}) => {
  //userInfo get : axios.get()으로 변경
  const selectedUser = users.filter((user) => user.userId==userId)[0];
  const {userImage, userIntro, follower, following} = selectedUser;
  state.userImage = userImage;
  state.userIntro = userIntro;
  state.follower = follower;
  state.following = following;
};

export const checkMyFollowing = (state, {userId}) => {
  //나의 follow 여부 get : axios.get()으로 변경
  const myFollowing = myFollowings.filter((myFollowing)=> myFollowing.userId==userId)[0].follow;
  state.myFollowing = myFollowing;
};

export const getUserRecipes = (state, {userId}) => {
  //userRecipes get : axios.get()으로 변경
  const userRecipes = recipes.filter((recipe) => recipe.userId==userId);  
  state.userRecipes = userRecipes;
};

export const updateMyFollowing = (state, {toFollow}) => {
  //myFollowing update : axios.post() 추가
  state.myFollowing = toFollow;
};

