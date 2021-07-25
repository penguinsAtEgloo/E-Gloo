<template>
  <div class="outer" :style="cssVars">
    <div class="wrap-image">
      <div class="recipe-img" @click="hello"></div>
      <bookmark-icon
        @click="bookmark(item)"
        :class="{ bookmark: true, bookmarked: item.bookmarked }"
      />
    </div>
    <div class="info">
      <div>
        <b
          ><a class="itemTitle" href="#0">{{ item.recipeName }}</a></b
        >
      </div>
      <div class="etc">
        <difficulty-icon />
        <span>{{ item.difficulty }}</span>
        <clock-icon />
        <span>{{ item.duration }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import DifficultyIcon from "~/assets/images/difficulty.svg?inline";
import ClockIcon from "~/assets/images/clock.svg?inline";
import BookmarkIcon from "~/assets/images/bookmark.svg?inline";
import { mapMutations } from "vuex";

export default {
  components: {
    DifficultyIcon,
    ClockIcon,
    BookmarkIcon
  },
  props: {
    item: {
      type: Object,
      default: () => {
        return {};
      }
    }
  },
  computed: {
    cssVars() {
      return {
        "--image-url": "url(" + this.item.imageUrl + ")"
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
.outer {
  width: 100%;
  margin: 0;
  place-self: center;
}
.wrap-image {
  position: relative;
  padding-bottom: 100%;
  height: 0;
}
.recipe-img {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #c4c4c4;
  border-width: 3px;
  background-image: var(--image-url);
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
}
.bookmark {
  position: absolute;
  right: 2.23%;
  top: 1.85%;
  bottom: 87.04%;
  stroke: white;
}
li .outer {
  float: center;
}
.info {
  margin-left: 0.5em;
  text-align: left;
}
.itemTitle {
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
.bookmarked {
  fill: #0063f7;
  stroke: #0063f7;
}
</style>
