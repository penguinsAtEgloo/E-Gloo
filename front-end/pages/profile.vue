<template>
  <div>
    <v-container style="width: 400px">
      <v-row class="profile_header">
        <v-col cols="12">{{ userId }}</v-col>
      </v-row>
      <v-row class="pa-2" style="height: 100px">
        <v-col cols="3">
          <v-avatar size="74px">
            <img :src="userImage" width="100%" />
          </v-avatar>
        </v-col>
        <v-col cols="6">
          <div style="height: 17px"></div>
          <p class="profile_userId">{{ userId }}</p>
          <p class="profile_follow_info">
            팔로워 {{ follower }} 팔로잉 {{ following }}
          </p>
        </v-col>
        <v-col
          cols="3"
          class="profile_button"
          style="padding-right: 10px; text-align: right"
        >
          <div style="height: 17px"></div>
          <v-btn
            v-if="!myFollowing"
            @click="updateMyFollowing(true)"
            class="profile_button ma-2"
            outlined
            :loading="myFollowingLoading"
            :disabled="myFollowingLoading"
          >
            팔로우
          </v-btn>
          <v-btn
            v-if="myFollowing"
            @click="updateMyFollowing(false)"
            class="profile_button ma-2"
            :loading="myFollowingLoading"
            :disabled="myFollowingLoading"
            outlined
          >
            팔로우 취소
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col class="profile_userIntro" cols="12" v-html="userIntro"></v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <span class="profile_image_header">사진&nbsp;</span>
          <span class="profile_image_cnt">{{ userRecipesCnt }}</span>
        </v-col>
      </v-row>
      <v-row>
        <v-col
          v-for="n in userRecipesCnt"
          :key="n"
          class="d-flex child-flex"
          cols="4"
          style="padding: 3px"
        >
          <v-img
            :src="userRecipes[n - 1].recipeImage"
            :lazy-src="userRecipes[n - 1].recipeImage"
            aspect-ratio="1"
            class="profile_image"
            position="left"
          >
            {{ userRecipes[n - 1].recipeName }}

            <template v-slot:placeholder>
              <v-row class="fill-height" align="center" justify="center">
                <v-progress-circular
                  indeterminate
                  color="white"
                ></v-progress-circular>
              </v-row>
            </template>
          </v-img>
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
    userIntro() {
      return this.$store.state.profile.userIntro.replace(
        /(?:\r\n|\r|\n)/g,
        "<br />"
      );
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

<style scoped>
.profile_header {
  font-family: Mulish;
  font-style: normal;
  font-weight: bold;
  font-size: 20px;
  line-height: 120%;

  text-align: center;
  letter-spacing: 0.15px;

  background-color: #fafafc;
  color: #1c1c28;
}

.profile_userId {
  font-family: Mulish;
  font-style: normal;
  font-weight: bold;
  font-size: 16px;
  line-height: 120%;
  /* or 19px */

  letter-spacing: 0.15px;
}

.profile_userIntro {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 11px;
  line-height: 16px;

  color: #8c8c8c;
}

.profile_follow_info {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 12px;
  line-height: 17px;

  color: #4f4f4f;
}

.profile_button {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 11px;
  line-height: 16px;
  /* identical to box height */

  color: #f98888;
}

.profile_image_header {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 16px;
  line-height: 23px;

  color: #4f4f4f;
}

.profile_image_cnt {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 12px;
  line-height: 17px;

  color: #8c8c8c;
}

.profile_image {
  color: white;
}
</style>