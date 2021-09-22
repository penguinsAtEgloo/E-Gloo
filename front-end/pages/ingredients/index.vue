<template>
  <div>
    <v-container style="width: 400px">
      <div class="ingredientsPageHeader">내 재료</div>
      <v-row>
        <v-tabs fixed-tabs style="color: #e57979">
          <v-tab
            v-for="{ category } in ingredients"
            v-bind:key="category"
            class="ingredientsTabs"
            >{{ category }}</v-tab
          >
          <v-tab-item
            v-for="{ category, content } in ingredients"
            v-bind:key="category"
          >
            <IngredientsGrid
              :category="category"
              :ingredients="content"
              :selectedIngredients="selectedIngredients"
            ></IngredientsGrid>
          </v-tab-item>
        </v-tabs>
      </v-row>
      <v-row>
        <v-col cols="3"></v-col>
        <v-col
          cols="6"
          @click="updateIngredients()"
          class="ingredientsNextButton"
          >계속하기</v-col
        >
        <v-col cols="3"></v-col>
      </v-row>
      <v-row data-app>
        <v-dialog v-model="dialog" max-width="290">
          <div
            style="
              background: #ffffff;
              box-shadow: 0px 0px 4px 5px rgba(0, 0, 0, 0.05);
            "
          >
            <div class="dialogContent">
              식재료를 등록하지 않으면 나중에 레시피를
              <br />추천 받을 수 없어요. 정말 나중에 등록 할까요?
            </div>

            <div style="text-align: center; padding: 20px">
              <v-btn text @click="dialog = false" class="dialogOk">
                지금 할게요
              </v-btn>
              &nbsp;
              <v-btn text @click="dialog = false" class="dialogCancel">
                네, 나중에 할래요.
              </v-btn>
            </div>
          </div>
        </v-dialog>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import IngredientsGrid from "@/components/IngredientsGrid";

export default {
  components: {
    IngredientsGrid,
  },
  async asyncData({ route, store }) {
    const { query } = route;
    await store.dispatch("ingredients/getIngredients", {}, { root: true });
  },
  data() {
    return {
      dialog: false,
    };
  },
  props: {},
  computed: {
    ...mapGetters(["isAuthenticated", "loggedInUser"]),
    ...mapState("ingredients", ["ingredients", "selectedIngredients"]),
  },
  methods: {
    async updateIngredients() {
      if (this.selectedIngredients.length === 0) this.dialog = true;

      await this.$store.dispatch(
        "ingredients/updateIngredients",
        {},
        { root: true }
      );
    },
  },
};
</script>

<style scoped>
.ingredientsPageHeader {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 20px;
  line-height: 29px;
  height: 60px;
  padding-top: 10px;
  text-align: center;

  color: #4f4f4f;
}
.ingredientsTabs {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 12px;
  line-height: 17px;
  padding: 0px;
  min-width: 60px;

  text-align: center;
  color: #b9b9b9;
}
.ingredientsNextButton {
  padding-top: 17.5px;
  padding-bottom: 17.5px;

  font-family: Mulish;
  font-style: normal;
  font-weight: bold;
  font-size: 15px;
  line-height: 100%;

  text-align: center;
  letter-spacing: 1.25px;
  text-transform: uppercase;
  border-radius: 30px;

  background-color: #f98888;
  color: #fafafc;
}
.round {
  border-radius: 30px;
  overflow-y: hidden;
}
.dialogContent {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 10px;
  line-height: 14px;
  text-align: center;
  padding-bottom: 10px;
  padding-top: 40px;

  color: #727272;
}
.dialogOk {
  background: #f98888;
  border-radius: 100px;
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 10px;
  line-height: 14px;

  color: #ffffff;
}
.dialogCancel {
  border: 1px solid #b7b7b7;
  box-sizing: border-box;
  border-radius: 100px;
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 10px;
  line-height: 14px;

  color: #b7b7b7;
}
</style>
