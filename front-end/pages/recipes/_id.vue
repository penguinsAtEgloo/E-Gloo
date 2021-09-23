<template>
  <div>
    <v-container style="width: 400px">
      <v-row>
        <v-col cols="12" class="recipeHeader"> 달걀 볶음밥({{ id }})</v-col>
        <v-col cols="12"
          ><div class="recipeHeadImage"></div>
          <div class="recipeSubTitle">간편편하하게 먹먹기기좋좋은</div>
          <div class="recipeTitle">달걀 볶음밥</div>
          <div class="recipeInfo">
            요요리리를 간간단단하하게 설설명명합합니니다다. 요요리리를 간간단단
            간단히 간단하게 설명합니다. 간단하게 설명을 합니다. 간단하게 설명을
            합니다ㅣ 간단하게 설명을 합니다.
          </div></v-col
        >
      </v-row>
      <v-row>
        <br />
        <v-tabs fixed-tabs style="color: #e57979">
          <v-tab>요리 재료</v-tab>
          <v-tab>상세설명</v-tab>
          <v-tab>관련상품</v-tab>
          <v-tab>후기(99+)</v-tab>

          <v-tab-item class="recipeIngredients">
            <div class="recipeIngredientsHead">기본 재료(1인분 기준)</div>
            <br />
            <v-divider></v-divider>
            <br />
            <v-row v-for="i in [1, 2, 3, 4]" :key="i">
              <v-col></v-col>
              <v-col class="recipeIngredientsName">달걀</v-col>
              <v-col class="recipeIngredientsCount">2개</v-col>
              <v-col></v-col>
              <v-col class="recipeIngredientsName">쌀밥</v-col>
              <v-col class="recipeIngredientsCount">100g</v-col>
              <v-col></v-col>
            </v-row>
          </v-tab-item>

          <v-tab-item class="recipeDetail">
            <div class="recipeDetailStep">STEP.01</div>
            <div class="recipeHeadImage"></div>
            <div class="recipeDetailStepInfo">
              요요리리를 간간단단하하게 설설명명합합니니다다. 요요리리를
              간간단단 간단히 간단하게 설명합니다. 간단하게 설명을 합니다.
              간단하게 설명을 합니다ㅣ 간단하게 설명을 합니다.
            </div>
          </v-tab-item>

          <v-tab-item class="recipeSimilar">
            <div class="recipeSimilarHeader">관련 레시피</div>
            <v-divider></v-divider>
            <br />
            <v-row v-for="i in [1, 2, 3, 4]" :key="i">
              <v-col cols="3"><div class="recipeSimilarImage"></div></v-col>
              <v-col cols="7"
                ><div class="recipeSimilarSubTitle">
                  간편편하하게 먹먹기기좋좋은
                </div>
                <div class="recipeSimilarTitle">계란 볶음밥</div></v-col
              >
              <v-col cols="2"
                ><v-icon large style="margin-top: 20px"
                  >mdi-chevron-right</v-icon
                ></v-col
              >
            </v-row>
          </v-tab-item>

          <v-tab-item class="recipeReviews">
            <v-row>
              <v-col cols="8"
                ><div class="recipeReviewsHeader1">
                  사용자 후기(999+)
                </div></v-col
              >
              <v-col cols="4"
                ><div class="recipeReviewsHeader2">
                  더보기<v-icon>mdi-chevron-right</v-icon>
                </div></v-col
              >
            </v-row>
            <v-divider></v-divider>
            <div class="recipeReviewRating">
              <v-icon style="color: #ffe60a">mdi-star</v-icon>
              <v-icon style="color: #ffe60a">mdi-star</v-icon>
              <v-icon style="color: #ffe60a">mdi-star</v-icon>
              <v-icon style="color: #ffe60a">mdi-star</v-icon>
              <v-icon style="color: #dfdfdf">mdi-star</v-icon>
              <ReviewsGrid
                :recipes="recipesCookable"
                :bookmarks="recipeBookmarks"
              />
            </div>
          </v-tab-item>
        </v-tabs>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import ReviewsGrid from "@/components/ReviewsGrid";
import InfiniteLoading from "vue-infinite-loading";

export default {
  components: {
    ReviewsGrid,
    InfiniteLoading,
  },
  async asyncData({ route, store, params }) {
    const { query } = route;
    const keyword = query.keyword ? query.keyword : "rice";

    await store.dispatch("recipes/updateKeyword", { keyword }, { root: true });
    await store.dispatch(
      "recipes/getRecipesRecommended",
      { keyword, time: "오전" },
      { root: true }
    );
    await store.dispatch(
      "recipes/getRecipesCookable",
      { keyword },
      { root: true }
    );

    await store.dispatch("recipes/getRecipeBookmarks", {}, { root: true });
    return { id: params.id };
  },
  data() {
    return {
      //페이징 처리를 위한 local state
      recipesCookableOffset: 0,
      recipesRecommendedOffset: 0,
      limit: 20,
    };
  },

  props: {},
  computed: {
    ...mapGetters(["isAuthenticated", "loggedInUser"]),
    ...mapState("recipes", [
      "recipesCookable",
      "recipesRecommended",
      "recipeBookmarks",
      "recommendTime",
      "recommendKind",
      "recommendTheme",
    ]),
    recommend() {
      const arr = [];
      for (let a in this.recommendTime) {
        if (this.recommendTime[a]) arr.push(a);
      }
      for (let b in this.recommendKind) {
        if (this.recommendKind[b]) arr.push(b);
      }
      for (let c in this.recommendTheme) {
        if (this.recommendTheme[c]) arr.push(c);
      }
      return arr.join(",");
    },
  },
  methods: {
    infiniteHandler($state) {
      setTimeout(async () => {
        if (0) {
          //1개 이상의 data fetch 성공
          $state.loaded();
        } else {
          //더 이상 fetch할 data가 없음
          //no-results
          $state.complete();
        }
      }, 500);
    },
    async updateRecommendTime(time, bool) {
      await this.$store.dispatch(
        "recipes/updateRecommendTime",
        { time, bool },
        { root: true }
      );
    },
    async updateRecommendKind(kind, bool) {
      await this.$store.dispatch(
        "recipes/updateRecommendKind",
        { kind, bool },
        { root: true }
      );
    },
    async updateRecommendTheme(theme, bool) {
      await this.$store.dispatch(
        "recipes/updateRecommendTheme",
        { theme, bool },
        { root: true }
      );
    },
  },
  validate({ params }) {
    return /^\d+$/.test(params.id);
  },
};
</script>

<style scoped>
.recipeHeader {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 15px;
  line-height: 22px;
  text-align: center;
  color: #5f5f5f;
}
.recipeSubTitle {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 10px;
  line-height: 14px;
  margin-top: 15px;

  color: #9d9d9d;
}
.recipeTitle {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 15px;
  line-height: 22px;

  color: #4f4f4f;
}
.recipeInfo {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 10px;
  line-height: 14px;
  margin-top: 15px;

  color: #4e4e4e;
}
.recipeHeadImage {
  height: 148px;

  background: #c4c4c4;
}
.recipeIngredients {
  padding: 20px;
  background-color: #fafafc;
  color: black;
  text-align: center;
}
.recipeIngredientsHead {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 900;
  font-size: 11px;
  line-height: 16px;

  color: #686868;
}
.recipeIngredientsName {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 11px;
  line-height: 16px;

  color: #4e4e4e;
}
.recipeIngredientsCount {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 11px;
  line-height: 16px;

  color: #4e4e4e;
}

.recipeDetail {
  padding: 12px;
  color: black;
}
.recipeDetailStep {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 900;
  font-size: 11px;
  line-height: 16px;

  padding-top: 15px;
  padding-bottom: 15px;

  text-align: center;

  color: #686868;
}
.recipeDetailStepInfo {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 10px;
  line-height: 14px;

  padding-top: 15px;
  padding-bottom: 15px;

  color: #4e4e4e;
}

.recipeSimilar {
  color: black;
}
.recipeSimilarHeader {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 15px;
  line-height: 22px;
  margin: 12px;

  color: #4f4f4f;
}
.recipeSimilarImage {
  width: 81px;
  height: 81px;
  margin-left: 12px;
  background: #c4c4c4;
  border-radius: 15px;
}
.recipeSimilarSubTitle {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 10px;
  line-height: 14px;

  margin-top: 20px;

  color: #9d9d9d;
}
.recipeSimilarTitle {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 15px;
  line-height: 22px;

  color: #4f4f4f;
}

.recipeReviews {
  color: black;
}
.recipeReviewsHeader1 {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 15px;
  line-height: 22px;
  margin: 12px;

  color: #4f4f4f;
}
.recipeReviewsHeader2 {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 10px;
  line-height: 14px;
  text-align: right;
  margin: 12px;

  color: #8c8585;
}
.recipeReviewRating {
  padding: 10px;
}
</style>
