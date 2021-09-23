<template>
  <div class="outer" :style="cssVars">
    <div class="wrap-image">
      <div
        :class="{ ing_circle: true, bookmarked: ingredient.bookmarked }"
        role="img"
        aria-label="alt"
        @click="bookmark(ingredient)"
      />
    </div>
    <div class="ing_text">{{ ingredient.name }}</div>
  </div>
</template>
<script>
import { mapMutations } from "vuex";

export default {
  props: {
    ingredient: {
      type: Object,
      default: () => {
        return {};
      }
    }
  },
  computed: {
    cssVars() {
      return {
        "--image-url":
          this.ingredient.imageUrl == ""
            ? "#e0e0e0"
            : "url(" + this.ingredient.imageUrl + ")"
      };
    }
  },
  methods: {
    ...mapMutations(["bookmark"])
  }
};
</script>
<style scoped>
.outer {
  width: 100%;
}
.wrap-image {
  position: relative;
  padding-bottom: 100%;
  height: 0;
}
.ing_circle {
  position: absolute;
  width: 100%;
  height: 100%;
  background: var(--image-url);
  box-shadow: 0 0.25rem 0.5rem rgba(0, 0, 0, 0.04),
    0 0 0.13rem rgba(0, 0, 0, 0.06), 0 0 0.06rem rgba(0, 0, 0, 0.04);
  border-radius: 50%;
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}
.ing_text {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 0.5rem;
  line-height: 0.75em;
  text-align: center;
  color: #8f90a6;
  left: 30%;
  right: 30%;
  margin-top: 0.81rem;
  top: 0%;
  bottom: 0%;
  line-height: 0.75rem;
}
.bookmarked {
  box-shadow: 0 0 0.13rem 0.065rem rgba(0, 0, 0, 0.5);
}
</style>
