export const state = () => ({
  keyword: "",
  recipesCookable: [],
  recipesRecommended: [],
  recipeBookmarks: [],
  recommendTime: { "오전": true, "오후": false, "야간": false },
  recommendKind: { "한식": false, "중식": false, "양식": false, "일식": false, "기타": false },
  recommendTheme: {
    "비건": false,
    "다이어트": false,
    "안주": false,
    "간식": false,
    "건강식": false,
    "1인분": false,
    "디저트": false
  }
});