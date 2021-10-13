export const state = () => ({
  images: [
    {
      src:
        "https://recipe1.ezmember.co.kr/cache/recipe/2018/05/26/d0c6701bc673ac5c18183b47212a58571.jpg"
    },
    {
      src:
        "https://recipe1.ezmember.co.kr/cache/recipe/2018/04/04/833880e807106a8288be48259b19c4031.jpg"
    }
  ], //이미지 목록
  recipeId: 1,
  recipeName: "달걀 볶음밥",
  recipeShortContent: "레시피 간단 설명",
  userId: "mh9406",
  date: "2021.11.12",
  content:
    "오늘의 온더 테이블!\n간단한 달걀 볶음밥이지만 이렇게 맛있게 먹을 수 있다니, 레시피에서 집간장 추가하니까 훨씬 맛있어요",
  text: "", // 리뷰텍스트
  tags: ["#자취요리1", "#자취요리2", "#자취요리3", "#자취요리4"], // 태그 목록
  scrap: 1009,
  hits: 728,
  reviews: [
    {
      reviewId: 1,
      reviewerName: "mh9406",
      reviewComment: "달걀볶음밥 너무 맛있었어요~! 간편히 먹기 좋네요",
      date: "20211015",
      like: 1,
      reply: 2
    },
    {
      reviewId: 2,
      reviewerName: "mh9406",
      reviewComment: "달걀볶음밥 너무 맛있었어요~! 간편히 먹기 좋네요",
      date: "20211015",
      like: 1,
      reply: 2
    },
    {
      reviewId: 3,
      reviewerName: "mh9406",
      reviewComment: "달걀볶음밥 너무 맛있었어요~! 간편히 먹기 좋네요",
      date: "20211015",
      like: 1,
      reply: 2
    }
  ] //리뷰
});
