<template>
  <div v-if="recipe.recipeName" class="recipe">
    <div class="close-image" @click="hello">
      <div
        class="img"
        role="img"
        aria-label="alt"
        :style="cssVars"
        v-if="recipe.imageUrl"
      />
    </div>
    <bookmark-icon
      @click="bookmark(recipe)"
      class="bookmark"
      :fill="bookmarkColor"
      :stroke="bookmarkColor == 'none' ? 'white' : '#0063F7'"
    />
    <div class="info">
      <div>
        <b
          ><a class="recipeTitle" href="#0">{{ recipe.recipeName }}</a></b
        >
      </div>
      <div class="etc">
        <difficulty-icon />
        <span>{{ recipe.difficulty }}</span>
        <clock-icon />
        <span>{{ recipe.duration }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import DifficultyIcon from "~/assets/images/difficulty.svg?inline";
import ClockIcon from "~/assets/images/clock.svg?inline";
import BookmarkIcon from "~/assets/images/bookmark.svg?inline";
import NoImage from "~/assets/images/no-image.svg?inline";
import { mapMutations } from "vuex";

export default {
  components: {
    DifficultyIcon,
    ClockIcon,
    BookmarkIcon,
    NoImage
  },
  props: {
    recipe: {
      type: Object,
      default: () => {
        return {};
      }
    }
  },
  computed: {
    bookmarkColor() {
      return this.recipe.bookmarked ? "#0063F7" : "none";
    },
    cssVars() {
      return {
        "--imageUrl": "url(" + this.recipe.imageUrl + ")"
      };
    }
  },
  methods: {
    hello() {
      alert("hello");
    },
    ...mapMutations(["bookmark"])
  }
};
</script>
<style scoped>
.recipe {
  display: block;
  position: relative;
  padding: 0;
  margin: 0;
  overflow: hidden;
}
.close-image {
  display: flex;
  position: relative;
  padding: 0;
  border: 0;
  width: 179px;
  height: 176px;
  vertical-align: middle;
  background-color: #c4c4c4;
  border-width: 3px;
}
.img {
  position: relative;
  height: 100%;
  width: 100%;
  background-image: var(--imageUrl);
  background-repeat: no-repeat;
  background-position-y: 50%;
  background-size: contain;
}

.bookmark {
  position: absolute;
  display: block;
  top: 2vmin;
  right: 2vmin;
}
li .recipe {
  float: center;
}
.info {
  margin-left: 0.5em;
  text-align: left;
}
.recipeTitle {
  color: black;
  font-family: "Noto Sans KR";
  font-size: 0.85em;
}
.etc {
  display: inline-flex;
  align-self: center;
}
.etc > span {
  font-family: "Noto Sans KR";
  color: gray;
  font-size: 0.65em;
  margin-right: 0.7em;
}

.etc > svg {
  height: 0.7em;
  width: 0.7em;
  top: 0.2em;
  position: relative;
  margin-right: 0.3em;
}
</style>
