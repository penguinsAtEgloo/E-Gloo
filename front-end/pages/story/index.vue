<template>
  <v-app>
    <v-container style="width: 400px">
      <v-row><v-col class="storyHeader">달걀 볶음밥</v-col></v-row>
      <v-row>
        <v-col cols="12" align="center" style="padding: 0">
          <v-carousel
            hide-delimiters
            height="400"
            style="margin: 0; padding: 0"
          >
            <v-carousel-item
              v-for="(item, i) in images"
              :key="i"
              reverse-transition="fade-transition"
              transition="fade-transition"
            >
              <v-img
                width="100%"
                aspect-ratio="1"
                :src="item.src"
                @error="$event.target.src = '~assets/images/white.jpg'"
                class="ingredientImage"
              />
            </v-carousel-item>
          </v-carousel>
        </v-col>
      </v-row>

      <br /><v-divider></v-divider><br />

      <v-row style="height: 100px">
        <v-col cols="3">
          <v-avatar size="74px"> </v-avatar>
        </v-col>
        <v-col cols="7">
          <p class="storyMainHeader">{{ recipeName }}</p>
          <p class="recipeShortContent">{{ recipeShortContent }}</p>
        </v-col>
        <v-col cols="2" align="left">
          <v-icon
            v-if="!recipeBookmarks.includes(recipeId)"
            @click="addRecipeBookmark(recipeId)"
            class="recipeBookmark"
          >
            mdi-bookmark-outline
          </v-icon>
          <v-icon
            v-else
            @click="removeRecipeBookmark(recipeId)"
            class="recipeBookmark"
          >
            mdi-bookmark
          </v-icon>
        </v-col>
      </v-row>

      <br /><v-divider></v-divider><br />

      <v-row>
        <v-col cols="2">
          <v-img
            width="100%"
            aspect-ratio="1"
            src="./assets/images/white.jpg"
            @error="$event.target.src = '~assets/images/white.jpg'"
            class="ingredientImage"
          />
        </v-col>
        <v-col cols="10" align="left">
          <p class="storyUserId" style="margin-bottom:0;">{{ userId }}</p>
          <p class="storyDate">{{ date }}</p>
        </v-col>
        <v-col cols="12">
          <p v-html="replacedContent" class="storyContent"></p>
          <span v-for="(tag, i) in tags" v-bind:key="i">
            <v-btn class="storyTag" outlined>
              <span>{{ tag }}</span>
            </v-btn>
          </span>
        </v-col>
      </v-row>
      <v-row>
        <v-col align="right">
          <span class="storyBottomHeader">스크랩</span>
          <span class="storyBottomNumber">{{ scrap }}</span>
          <span class="storyBottomHeader">조회수</span>
          <span class="storyBottomNumber">{{ hits }}</span>
        </v-col>
      </v-row>

      <v-row v-for="review in reviews" v-bind:key="review.reviewId">
        <v-col cols="12"> <v-divider></v-divider><br /> </v-col>
        <v-col cols="1">
          <v-img
            width="100%"
            aspect-ratio="1"
            src="./assets/images/white.jpg"
            @error="$event.target.src = '~assets/images/white.jpg'"
            class="ingredientImage"
          />
        </v-col>
        <v-col cols="11">
          <span class="storyUserId">{{ review.reviewerName }}</span>
          <span class="storyDate">{{ review.date }}</span>
        </v-col>
        <v-col cols="12" class="storyContent">
          <p v-html="review.reviewComment" class="storyContent"></p>
        </v-col>
        <v-col cols="6" class="storyBottomHeader">신고</v-col>
        <v-col cols="6" class="storyBottomHeader" align="right">
          좋아요{{ review.like }} 답글{{ review.reply }}
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import { mapGetters, mapState } from "vuex";

export default {
  components: {},
  async asyncData({ route, store, params }) {
    const { query } = route;
    const keyword = query.keyword ? query.keyword : "rice";

    await store.dispatch("recipes/getRecipeBookmarks", {}, { root: true });
    return { storyId: params.id };
  },

  data: () => ({
    bookmarked: false
  }),
  props: {},
  computed: {
    ...mapGetters(["isAuthenticated", "loggedInUser"]),
    ...mapState("story", [
      "images",
      "recipeId",
      "recipeName",
      "recipeShortContent",
      "userId",
      "date",
      "content",
      "text",
      "tags",
      "scrap",
      "hits",
      "reviews"
    ]),
    ...mapState("recipes", ["recipeBookmarks"]),
    replacedContent() {
      return this.content.replace("\n", "<br />");
    },
    replacedReviews() {
      return reviews.map(review => {
        review.reviewContent = review.reviewContent.replace("\n", "<br />");
        return review;
      });
    }
  },
  methods: {
    async addRecipeBookmark(recipeId) {
      await this.$store.dispatch(
        "recipes/addRecipeBookmark",
        {
          userId: "mh9406", //nuxt auth에서 받아오는 것으로 변경
          recipeId
        },
        { root: true }
      );
    },
    async removeRecipeBookmark(recipeId) {
      await this.$store.dispatch(
        "recipes/removeRecipeBookmark",
        {
          userId: "mh9406", //nuxt auth에서 받아오는 것으로 변경
          recipeId
        },
        { root: true }
      );
    }
  }
};
</script>

<style scoped>
.storyHeader {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 15px;
  line-height: 22px;

  text-align: center;
  background-color: #f9f9f9;
  color: #5f5f5f;
}

.storyMainHeader {
  font-family: Mulish;
  font-style: normal;
  font-weight: bold;
  font-size: 13px;
  line-height: 120%;

  margin-top: 20px;
  margin-bottom: 0px;

  letter-spacing: 0.15px;

  color: #4f4f4f;
}

.recipeShortContent {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 13px;
  line-height: 19px;

  color: #4e4e4e;
}
.storyUserId {
  font-family: Mulish;
  font-style: normal;
  font-weight: bold;
  font-size: 13px;
  line-height: 120%;
  letter-spacing: 0.15px;

  color: #4f4f4f;
}
.storyDate {
  font-family: Mulish;
  font-style: normal;
  font-weight: bold;
  font-size: 10px;
  line-height: 120%;

  letter-spacing: 0.15px;

  color: #9f9f9f;
}

.storyContent {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 12px;
  line-height: 17px;

  color: #4e4e4e;
}

.storyTag {
  border: 1.2px solid #f98888;
  box-sizing: border-box;
  border-radius: 100px;

  margin-right: 5px;
  margin-bottom: 5px;
  text-transform: none !important;
  pointer-events: none;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 11px;
  line-height: 16px;

  color: #f98888;
}
.storyBottomHeader {
  font-family: Mulish;
  font-style: normal;
  font-weight: bold;
  font-size: 10px;
  line-height: 120%;
  /* identical to box height, or 12px */

  letter-spacing: 0.15px;

  color: #9f9f9f;
}

.storyBottomNumber {
  font-family: Mulish;
  font-style: normal;
  font-weight: bold;
  font-size: 10px;
  line-height: 120%;

  letter-spacing: 0.15px;

  color: #2e2e2e;
}

.recipeBookmark {
  margin-top: 20px;
  color: #e57979;
}
.v-slide-group__wrapper {
  overflow-x: auto; /* Enables the horizontal scrollbar */
  /* Next lines hides scrollbar in different browsers for styling purposes */
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.v-slide-group__wrapper::-webkit-scrollbar {
  display: none; /* Chrome opera and Safary */
  width: 0px;
  background: transparent;
}
</style>
