<template>
  <div style="width: 480px">
    <v-container class="grey lighten-5 mb-6">
      <v-row style="height: 30px; text-align: center" no-gutters>
        <v-col cols="12">{{ userId }}</v-col>
      </v-row>
      <v-row>
        <v-col cols="3"><img :src="userImage" width="100%" /></v-col>
        <v-col cols="6"
          >{{ userId }}<br />
          <h3>팔로워 {{ follower }} 팔로잉 {{ following }}</h3>
        </v-col>
        <v-col cols="3">
          <v-btn
            v-if="!myFollowing"
            @click="updateMyFollowing(true)"
            class="ma-2"
            outlined
            :loading="myFollowingLoading"
            :disabled="myFollowingLoading"
            color="info"
          >
            팔로우
          </v-btn>
          <v-btn
            v-if="myFollowing"
            @click="updateMyFollowing(false)"
            class="ma-2"
            :loading="myFollowingLoading"
            :disabled="myFollowingLoading"
            color="info"
          >
            팔로우 취소
          </v-btn>
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col cols="12"
          ><p>{{ userIntro }}</p></v-col
        >
      </v-row>
    </v-container>
    <v-container class="grey lighten-5 mb-6">
      <v-row>
        <v-col cols="12"> 사진 : {{ userRecipesCnt }} </v-col>
      </v-row>
      <v-row>
        <v-col
          v-for="n in userRecipesCnt"
          :key="n"
          class="d-flex child-flex"
          cols="4"
        >
          <v-img
            :src="userRecipes[n - 1].recipeImage"
            :lazy-src="userRecipes[n - 1].recipeImage"
            aspect-ratio="1"
            class="grey lighten-2"
          >
            <template v-slot:placeholder>
              <v-row class="fill-height ma-0" align="center" justify="center">
                <v-progress-circular
                  indeterminate
                  color="grey lighten-5"
                ></v-progress-circular>
              </v-row>
            </template>
          </v-img>
          <v-card> {{ userRecipes[n - 1].recipeName }}</v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";

export default {
  async asyncData({ route, store }) {
    const { query } = route;
    const userIdParam = query.userId ? query.userId : "mh9406";
    await store.dispatch(
      "profile/firstDataFetch",
      { userId: userIdParam },
      { root: true }
    );
  },
  data() {
    return {};
  },
  props: {
    myFollowingLoading: {
      type: Boolean,
      default: false,
    },
  },
  computed: {
    ...mapGetters(["isAuthenticated", "loggedInUser"]),
    ...mapState("profile", [
      "userId",
      "userImage",
      "userIntro",
      "follower",
      "following",
      "myFollowing",
      "myFollowLoading",
      "userRecipes",
    ]),
    userRecipesCnt() {
      return this.$store.state.profile.userRecipes.length;
    },
  },
  methods: {
    async updateMyFollowing(toFollow) {
      this.myFollowingLoading = true;
      await this.$store.dispatch(
        "profile/updateMyFollowing",
        { toFollow },
        { root: true }
      );
      this.myFollowingLoading = false;
    },
  },
};
</script>