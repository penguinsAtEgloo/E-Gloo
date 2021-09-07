<template>
  <v-container fluid>
    <v-carousel
      class="customDelimiter"
      :continuous="false"
      :show-arrows="false"
      hide-delimiter-background
      delimiter-icon=""
    >
      <v-carousel-item
        v-for="(page, index) in ingredientsPages"
        :key="index"
        class="fill-height"
      >
        <br />
        <v-row>
          <v-col
            v-for="p in page"
            :key="p"
            class="d-flex child-flex"
            cols="3"
            style="padding: 9px"
            align="center"
          >
            <!--tobe image url + /상품명 으로 변경-->
            <v-img
              v-if="!checkIfSelected(ingredients[p])"
              width="74px"
              aspect-ratio="1"
              @click="selectIngredients(ingredients[p])"
              src="~assets/images/white.jpg"
              @error="$event.target.src = '~assets/images/white.jpg'"
              class="ingredientImage"
            />
            <v-img
              v-else
              width="74px"
              aspect-ratio="1"
              @click="exceptIngredients(ingredients[p])"
              src="~assets/images/white.jpg"
              @error="$event.target.src = '~assets/images/white.jpg'"
              class="ingredientImageSelected"
            />
            <div class="ingredientHeader">
              {{ ingredients[p] }}
            </div>
          </v-col>
        </v-row>
        <br />
      </v-carousel-item>
    </v-carousel>
  </v-container>
</template>

<script>
export default {
  props: {
    category: {
      type: String,
      default: () => "",
    },
    ingredients: {
      type: Array,
      default: () => [],
    },
    selectedIngredients: {
      type: Array,
      default: () => [],
    },
  },
  computed: {
    //for ingredientsPaging
    ingredientsPages() {
      const pages = [];
      let page = [];
      for (let i = 0; i < this.ingredients.length; i++) {
        if (i > 0 && i % 16 === 0) {
          pages.push(page);
          page = [i];
        } else page.push(i);
      }
      if (page.length > 0) pages.push(page);

      return pages;
    },
  },
  methods: {
    checkIfSelected(content) {
      return this.selectedIngredients.some(
        (si) => si.category === this.category && si.content === content
      );
    },
    selectIngredients(content) {
      this.$store.dispatch(
        "ingredients/selectIngredients",
        {
          category: this.category, //props로 받은 값 그대로 사용
          content,
        },
        { root: true }
      );
    },
    exceptIngredients(content) {
      this.$store.dispatch(
        "ingredients/exceptIngredients",
        {
          category: this.category, //props로 받은 값 그대로 사용
          content,
        },
        { root: true }
      );
    },
  },
};
</script>
<style>
.customDelimiter .v-btn {
  color: #e0e0e0 !important;
  border: 1px solid #e0e0e0 !important;
  background-color: #e0e0e0 !important;
}

.customDelimiter .v-btn--active {
  color: #f98888 !important;
  border: 1px solid #f98888 !important;
  background-color: #f98888 !important;
}
</style>

<style scoped>
.ingredientHeader {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 10px;
  line-height: 14px;
  text-align: center;
  padding-top: 3px;

  color: #b9b9b9;
}
.ingredientImage {
  border-radius: 50%;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.04), 0px 0px 2px rgba(0, 0, 0, 0.06),
    0px 0px 1px rgba(0, 0, 0, 0.04);
}
.ingredientImageSelected {
  border-radius: 50%;
  background: #ffffff;
  border: 3px solid #f98888;
  box-sizing: border-box;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.04), 0px 0px 2px rgba(0, 0, 0, 0.06),
    0px 0px 1px rgba(0, 0, 0, 0.04);
}
</style>
