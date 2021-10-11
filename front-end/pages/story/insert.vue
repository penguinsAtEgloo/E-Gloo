<template>
  <div data-app>
    <v-container style="width: 400px">
      <v-row><v-col class="storyInsertHeader">스토리 작성</v-col></v-row>
      <br />
      <v-row>
        <v-col cols="12" align="center"
          ><v-rating></v-rating
          ><span class="ratingHeader">별점을 매겨주세요!</span></v-col
        >
      </v-row>
      <br />
      <v-divider></v-divider>
      <br />
      <v-row>
        <v-col>
          <v-textarea
            outlined
            name="input-7-4"
            placeholder="이 요리의 스토리를 자유롭게 작성해 보세요.
(ex. #로 키워드 태그하기)"
            @keyup="checkTags"
            auto-grow
            v-model="comment"
          ></v-textarea>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <span v-for="(tag, i) in tags" v-bind:key="i">
            <v-btn
              v-if="!selectedTags.includes(tag)"
              class="storyInsertTagButton"
              @click="addSelectedTags(tag)"
              outlined
            >
              <span>{{ tag }}</span>
            </v-btn>
            <v-btn
              v-else
              class="storyInsertTagButton"
              @click="removeSelectedTags(tag)"
              style="background: #f98888; color: white"
            >
              <span>{{ tag }}</span>
            </v-btn>
          </span>
        </v-col>
      </v-row>
      <v-row>
        <v-col class="storyInsertImageTagHeader" cols="12">
          재료를 태그하려면 해당 사진을 터치하세요
          <input
            type="file"
            style="display: none"
            ref="fileInput"
            accept="image/*"
            @change="addImage"
          />
        </v-col>
        <v-slide-group show-arrows>
          <v-slide-item>
            <div class="storyInsertImage" @click="uploadImage">
              <div style="margin-top: 30px; text-align: center">
                <v-icon
                  size="30"
                  style="color: grey"
                  v-text="'mdi-plus-circle-outline'"
                ></v-icon>
                <br />
                ({{ images.length }}/20)
              </div>
            </div>
          </v-slide-item>
          <v-slide-item v-for="n in imageUrls.length" :key="n">
            <div class="storyInsertImage">
              <div
                style="
                  margin-top: -15px;
                  margin-right: -10px;
                  text-align: right;
                "
              >
                <v-icon
                  size="30"
                  style="color: grey; z-index: 9999"
                  v-text="'mdi-close-circle'"
                  @click="removeImage(n - 1)"
                ></v-icon>
              </div>
              <v-img
                width="100%"
                max-height="98"
                style="margin-top: -15px"
                :src="imageUrls[n - 1]"
              >
              </v-img>
            </div>
          </v-slide-item>
        </v-slide-group>
      </v-row>
      <v-row>
        <v-col align="center">
          <v-btn outlined class="storyInsertCompleteButton">
            <span class="storyInsertComplete">스토리 올리기</span>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";

export default {
  components: {},
  async asyncData({ route, store }) {
    const { query } = route;
    const keyword = query.keyword ? query.keyword : "rice";

    await store.dispatch(
      "recipes/getRecipesRecommended",
      { keyword, time: "오전" },
      { root: true }
    );
  },

  data: () => ({
    imageUrls: [],
    comment: "",
  }),
  props: {},
  computed: {
    ...mapGetters(["isAuthenticated", "loggedInUser"]),
    ...mapState("story/insert", ["tags", "selectedTags", "images", "urls"]),
    getTagsWithSelected() {
      //선택된 태그 구분하여 태그 목록 return
      return [];
    },
  },
  methods: {
    async checkTags() {
      const tagsTmp = this.comment.match(/\#(\S+)\s/g);
      if (tagsTmp && tagsTmp.length > 0) {
        await this.updateTags(tagsTmp.map((v) => v.slice(0, v.length - 1)));
      }
    },

    async updateTags(newTags) {
      await this.$store.dispatch(
        "story/insert/updateTags",
        { tags: newTags },
        { root: true }
      );
    },

    async addSelectedTags(tag) {
      await this.$store.dispatch(
        "story/insert/addSelectedTags",
        { tag },
        { root: true }
      );
    },

    async removeSelectedTags(tag) {
      await this.$store.dispatch(
        "story/insert/removeSelectedTags",
        { tag },
        { root: true }
      );
    },

    uploadImage() {
      this.$refs.fileInput.value = "";
      this.$refs.fileInput.click();
    },
    async addImage(event) {
      const { files } = event.target;
      await this.$store.dispatch(
        "story/insert/addImage",
        { image: files[0] },
        { root: true }
      );
      const fileReader = new FileReader();
      fileReader.addEventListener("load", () => {
        const url = fileReader.result;
        this.imageUrls.push(url);
      });
      fileReader.readAsDataURL(files[0]);
    },
    async removeImage(imageIdx) {
      await this.$store.dispatch(
        "story/insert/removeImage",
        { imageIdx },
        { root: true }
      );
      this.imageUrls = this.imageUrls.filter((image, idx) => idx != imageIdx);
    },
  },
};
</script>

<style scoped>
.storyInsertHeader {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 15px;
  line-height: 22px;

  text-align: center;
  background-color: #f9f9f9;
  color: #5f5f5f;
}
.ratingHeader {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 13px;
  line-height: 19px;

  color: #bbbbbb;
}
.storyInsertTagButton {
  border: 1.2px solid #f98888;
  box-sizing: border-box;
  border-radius: 100px;

  margin-right: 5px;
  margin-bottom: 5px;
  text-transform: none !important;

  color: #f98888;
}

.storyInsertImageTagHeader {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 12px;
  line-height: 17px;

  color: #939393;
}
.storyInsertImage {
  margin-right: 10px;
  margin-top: 15px;
  height: 100px;
  width: 100px;
  border: 1px solid black;
}

.storyInsertComplete {
  font-family: Mulish;
  font-style: normal;
  font-weight: bold;
  font-size: 15px;
  line-height: 100%;

  text-align: center;
  letter-spacing: 1.25px;
  text-transform: uppercase;
}
.storyInsertCompleteButton {
  width: 90%;
  height: 100px;
  padding: 100px;

  color: #fafafc;
  background: #f98888;
  border-radius: 50px;
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
