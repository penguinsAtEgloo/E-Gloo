<template>
  <div data-app>
    <v-container style="width: 400px; background-color: #fafafc">
      <v-tabs fixed-tabs right style="color: #e57979">
        <v-tab>요리 가능 레시피</v-tab>
        <v-tab>추천 레시피</v-tab>
        <v-tab-item style="background-color: #fafafc">
          <v-container fluid>
            <v-row align="center">
              <v-col></v-col>
              <v-col class="d-flex" cols="3">
                <v-select
                  v-model="select"
                  :items="filters"
                  item-text="text"
                  item-value="value"
                  return-object
                  v-on:change="filterChanged"
                  style="font-size: 10px"
                  dense
                >
                </v-select>
              </v-col> </v-row
          ></v-container>
          <RecipesGrid
            :recipes="recipesCookable"
            :bookmarks="recipeBookmarks"
          />
          <br />
          <infinite-loading @infinite="infiniteHandler" spinner="spiral">
            <div slot="spinner">레시피 불러오는 중</div>
            <div slot="no-more">레시피가 더 이상 없습니다</div>
            <div slot="no-results">레시피가 더 이상 없습니다</div>
          </infinite-loading>
        </v-tab-item>
        <v-tab-item style="background-color: #fafafc">
          <v-expansion-panels style="margin-top: 10px; margin-bottom: 10px">
            <v-expansion-panel>
              <v-expansion-panel-header class="recommendHeader">
                <span
                  style="font-weight: bold; text-decoration-line: underline"
                  >{{ recommend }}</span
                >추천 레시피 입니다
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <span class="recommendContentTitle">시간대</span>
                <span v-for="time in Object.keys(recommendTime)" :key="time">
                  <span
                    v-if="!recommendTime[time]"
                    class="recommendContent"
                    @click="updateRecommendTime(time, true)"
                    >{{ time }}</span
                  >
                  <span
                    v-else
                    class="recommendContentSelected"
                    @click="updateRecommendTime(time, false)"
                    >{{ time }}</span
                  >
                </span>
                <v-divider style="margin: 4px"></v-divider>
                <span class="recommendContentTitle">종류</span
                ><span v-for="kind in Object.keys(recommendKind)" :key="kind">
                  <span
                    v-if="!recommendKind[kind]"
                    class="recommendContent"
                    @click="updateRecommendKind(kind, true)"
                    >{{ kind }}</span
                  >
                  <span
                    v-else
                    class="recommendContentSelected"
                    @click="updateRecommendKind(kind, false)"
                    >{{ kind }}</span
                  >
                </span>
                <v-divider style="margin: 4px"></v-divider>
                <span class="recommendContentTitle">테마</span>
                <span v-for="theme in Object.keys(recommendTheme)" :key="theme">
                  <span
                    v-if="!recommendTheme[theme]"
                    class="recommendContent"
                    @click="updateRecommendTheme(theme, true)"
                    >{{ theme }}</span
                  >
                  <span
                    v-else
                    class="recommendContentSelected"
                    @click="updateRecommendTheme(theme, false)"
                    >{{ theme }}</span
                  >
                </span>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
          <v-container fluid>
            <v-row align="center">
              <v-col></v-col>
              <v-col class="d-flex" cols="3">
                <v-select
                  v-model="select"
                  :items="filters"
                  item-text="text"
                  item-value="value"
                  return-object
                  v-on:change="filterChanged"
                  style="font-size: 10px"
                  dense
                >
                </v-select>
              </v-col> </v-row
          ></v-container>
          <RecipesGrid
            :recipes="recipesRecommended"
            :bookmarks="recipeBookmarks"
          />
          <br />
          <infinite-loading @infinite="infiniteHandler" spinner="spiral">
            <div slot="spinner">레시피 불러오는 중</div>
            <div slot="no-more">레시피가 더 이상 없습니다</div>
            <div slot="no-results">레시피가 더 이상 없습니다</div>
          </infinite-loading>
        </v-tab-item>
      </v-tabs>
    </v-container>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import RecipesGrid from "@/components/RecipesGrid";
import InfiniteLoading from "vue-infinite-loading";

export default {
  components: {
    RecipesGrid,
    InfiniteLoading,
  },
  async asyncData({ route, store }) {
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
  },

  data: () => ({
    //페이징 처리를 위한 local state
    recipesCookableOffset: 0,
    recipesRecommendedOffset: 0,
    limit: 20,
    select: { text: "인기순", value: "popular" },
    filters: [
      { text: "인기순", value: "popular" },
      { text: "난이도순", value: "level" },
      { text: "최신순", value: "latest" },
    ],
  }),
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
    filterChanged(event) {
      console.log(event.value);
      alert(event.text + " 정렬");
    },
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
};
</script>

<style scoped>
.recommendHeader {
  font-family: Noto Sans KR;
  font-style: normal;
  font-size: 13px;
  line-height: 19px;

  text-align: left;
  color: #5b5b5b;
}
.recommendContentTitle {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 10px;
  line-height: 14px;
  display: inline-block;
  width: 70px;

  color: #a2a2a2;
}
.recommendContent {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 10px;
  line-height: 14px;
  margin-right: 4px;

  color: #a9a9a9;
}
.recommendContentSelected {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 900;
  font-size: 10px;
  line-height: 14px;
  margin-right: 4px;

  color: #f98888;
}
select {
  width: 150px;
  height: 35px;
  background: url("https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png")
    calc(100% - 5px) center no-repeat;
  background-size: 20px;
  padding: 5px 30px 5px 10px;
  border-radius: 4px;
  outline: 0 none;
}
</style>
