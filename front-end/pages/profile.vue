<template>
  <div>
      <img :src="userImage" width="30%">
      <h3>{{userId}}</h3>
      <h3>팔로워 {{follower}}</h3>
      <h3>팔로잉 {{following}}</h3>
      <button v-if="!myFollowing" @click="updateMyFollowing(true)">팔로우</button>
      <button v-else @click="updateMyFollowing(false)">팔로우 취소</button>
      <br><br>
      <h3>{{userIntro}}</h3>
      <br>
      <h3>사진 : {{userRecipesCnt}}</h3>
      <ul>
        <li v-for="recipe in userRecipes" :key="recipe.recipeId">
          {{recipe.recipeName}}
        </li>
      </ul>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";

export default {
  async asyncData({route, store}) {
    const { query } = route;
    const userId = query.userId ? query.userId : "mh9406";
    await store.dispatch(     
      "profile/firstDataFetch",
      {userId},
      {root : true}
    );
  },
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["isAuthenticated", "loggedInUser"]),
    ...mapState("profile", 
      ["userId", "userImage", "userIntro", "follower", "following", "myFollowing", "userRecipes"]
    ),
    userRecipesCnt () {
      return this.$store.state.profile.userRecipes.length
    }
  },
  methods: {
    updateMyFollowing (toFollow) {
      this.$store.dispatch(
        "profile/updateMyFollowing",
        {toFollow},
        {root : true}
      );
    },
  }
}
</script>