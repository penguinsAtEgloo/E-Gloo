<template>
  <div id="container">
    <div id="outer">
      <div id="inputWrapper">
        <input
          type="text"
          name="search"
          id="searchBar"
          v-model="searchData"
          @focus="focused = true"
          :placeholder="placeholdVal"
          autocomplete="off"
        />
        <search-icon id="searchIcon" @click="$emit('search', searchData)" />
      </div>
      <div id="searchResultWrapper" v-show="focused">
        <p id="title">{{ title }}</p>
        <div id="searchResult" v-for="(word, index) in recommend" :key="word">
          <div id="word">{{ word }}</div>
          <multiply-icon
            v-if="searchData !== ''"
            id="multiplyIcon"
            @click="removeSearch({ word, index })"
          />
          <hr />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SearchIcon from "@/assets/images/search.svg?inline";
import MultiplyIcon from "~/assets/images/multiply.svg?inline";
import { mapGetters, mapMutations } from "vuex";

export default {
  name: "EGlooSearchbar",
  components: {
    SearchIcon,
    MultiplyIcon
  },

  data() {
    return {
      searchData: "",
      focused: false,
      bestSearched: []
    };
  },

  mounted() {
    this.$store.commit("fetchRecentSearch");
  },

  async fetch() {
    this.bestSearched = ["인기검색어1", "인기검색어2"];
  },

  methods: {},
  computed: {
    placeholdVal() {
      return this.searchData === "" ? "통합 검색" : "검색어를 입력중..";
    },
    recommend() {
      return this.searchData === ""
        ? this.bestSearched
        : this.storedRecentSearch();
    },
    title() {
      return this.searchData === "" ? "인기 검색어" : "최근 검색어";
    }
  },
  methods: {
    ...mapGetters(["storedRecentSearch"]),
    ...mapMutations(["removeSearch"])
  }
};
</script>

<style scoped>
#searchBar {
  line-height: 2rem;
  border: 0.031rem solid #e1b8b8;
  background: #fbfafa;
  box-sizing: border-box;
  box-shadow: 0px 0px 8px 2px rgba(0, 0, 0, 0.08);
  border-radius: 6rem;
  padding: 0rem 2rem 0rem 1rem;
  font-size: 1rem;
  width: 100%;
}
input:focus {
  outline: none;
}
#searchIcon {
  z-index: 1;
  position: absolute;
  right: 1rem;
  top: 0.5rem;
}
#searchResult {
  flex: 1 1 100%;
}
#container {
  background: white;
  text-align: center;
}
#outer {
  margin: 1rem auto;
  background-color: #fefefe;
  text-align: left;
}
#title {
  color: #7b8794;
  font-size: 0.8rem;
  bottom: 0.8rem;
}
#inputWrapper {
  position: relative;
}
#searchResultWrapper {
  z-index: 1;
  position: fixed;
  width: 100%;
  background: white;
  height: 100%;
}
#multiplyIcon {
  /* z-index: -1; */
  position: absolute;
  right: 1rem;
  width: 1rem;
}
#word {
  display: inline-block;
  height: 1.5rem;
  font-size: 0.8rem;
}
</style>
