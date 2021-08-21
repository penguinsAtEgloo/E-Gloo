export const getUserInfo = (state, {selectedUser}) => {
  const {userImage, userIntro, follower, following} = selectedUser;
  state.userImage = userImage;
  state.userIntro = userIntro;
  state.follower = follower;
  state.following = following;
};

export const checkMyFollowing = (state, {myFollowing}) => {
  state.myFollowing = myFollowing;
};

export const getUserRecipes = (state, {userRecipes}) => { 
  state.userRecipes = userRecipes;
};

export const updateMyFollowing = (state, {toFollow}) => {
  //myFollowing update : axios.post() 추가
  state.myFollowing = toFollow;
};

