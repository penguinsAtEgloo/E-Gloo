export const users = [
  {
    userId: "mh9406",
    userName: "김민혜",
    userImage:
      "http://img4.tmon.kr/cdn3/deals/2020/08/18/4212779218/front_f5a6b_f3qer.jpg",
    userIntro: "소소하지만 홈쿡생활 @mh9406\nhttp://www.instagram.com/9406",
    follower: 200,
    following: 350,
  },
];

export const recipes = [
  {
    recipeId: 1,
    recipeName: "아이스 카라멜 마끼아또",
    recipeImage:
      "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110582]_20210415142706078.jpg",
    userId: "mh9406",
    recipeLevel: "초급",
    recipeCookTime: 15,
    recipeBudget: 7000,
    ingredients: ["달걀", "스팸", "식용유", "통깨", "밥", "대파", "올리브"],
    bookmark: false,
  },
  {
    recipeId: 2,
    recipeName: "라벤더 카페 브레베",
    recipeImage:
      "https://image.istarbucks.co.kr/upload/store/skuimg/2021/02/[9200000000432]_20210225090321791.jpg",
    userId: "mh9406",
    recipeLevel: "초급",
    recipeCookTime: 20,
    recipeBudget: 7000,
    ingredients: ["달걀", "스팸", "식용유", "통깨", "밥", "대파", "올리브"],
    bookmark: false,
  },
  {
    recipeId: 3,
    recipeName: "바닐라 플랫 화이트",
    recipeImage:
      "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000002406]_20210415135507733.jpg",
    userId: "mh9406",
    recipeLevel: "고급",
    recipeCookTime: 10,
    recipeBudget: 7000,
    ingredients: ["달걀", "스팸", "식용유", "통깨", "밥", "대파", "올리브"],
    bookmark: false,
  },
  {
    recipeId: "4",
    recipeName: "클래식 아포가토",
    recipeImage:
      "https://image.istarbucks.co.kr/upload/store/skuimg/2021/02/[9200000001631]_20210225090916684.jpg",
    userId: "mh9406",
    recipeLevel: "고급",
    recipeCookTime: 44,
    recipeBudget: 7000,
    ingredients: ["달걀", "스팸", "식용유", "통깨", "밥", "대파", "올리브"],
    bookmark: false,
  },
];

export const recipesRecommended = [
  {
    recipeId: 1,
    recipeName: "아이스 카라멜 마끼아또",
    recipeImage:
      "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110582]_20210415142706078.jpg",
    userId: "mh9406",
    recipeLevel: "초급",
    recipeCookTime: 15,
    recipeBudget: 7000,
    ingredients: ["달걀", "스팸", "식용유", "통깨", "밥", "대파", "올리브"],
    bookmark: false
  },
  {
    recipeId: 2,
    recipeName: "라벤더 카페 브레베",
    recipeImage:
      "https://image.istarbucks.co.kr/upload/store/skuimg/2021/02/[9200000000432]_20210225090321791.jpg",
    userId: "mh9406",
    recipeLevel: "초급",
    recipeCookTime: 20,
    recipeBudget: 7000,
    ingredients: ["달걀", "스팸", "식용유", "통깨", "밥", "대파", "올리브"],
    bookmark: false
  },
  {
    recipeId: 3,
    recipeName: "바닐라 플랫 화이트",
    recipeImage:
      "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000002406]_20210415135507733.jpg",
    userId: "mh9406",
    recipeLevel: "고급",
    recipeCookTime: 10,
    recipeBudget: 7000,
    ingredients: ["달걀", "스팸", "식용유", "통깨", "밥", "대파", "올리브"],
    bookmark: false
  },
  {
    recipeId: "4",
    recipeName: "클래식 아포가토",
    recipeImage:
      "https://image.istarbucks.co.kr/upload/store/skuimg/2021/02/[9200000001631]_20210225090916684.jpg",
    userId: "mh9406",
    recipeLevel: "고급",
    recipeCookTime: 44,
    recipeBudget: 7000,
    ingredients: ["달걀", "스팸", "식용유", "통깨", "밥", "대파", "올리브"],
    bookmark: false
  }
];

export const myFollowings = [
  {
    userId: "mh9406",
    follow: false,
  },
];

export const myBookmarks = [
  {
    userId: "mh9406",
    bookmarks: [1, 2],
  },
];

export const ingredients = [
  {
    category: "쌀/잡곡", content: [
      "쌀1", "현미1", "보리1", "콩1",
      "쌀2", "현미2", "보리2", "콩2",
      "쌀3", "현미3", "보리3", "콩3",
      "쌀4", "현미4", "보리4", "콩4",
      "쌀5", "현미5", "보리5", "콩5",
      "쌀6", "현미6", "보리6", "콩6",
      "쌀7", "현미7", "보리7", "콩7",
      "쌀8", "현미8", "보리8", "콩8",
      "쌀9", "현미9", "보리9", "콩9",
    ]
  },
  { category: "과일", content: ["사과", "포도", "딸기", "수박"] },
  { category: "채소", content: ["당근", "양파", "피망", "양배추", "양상추"] },
  {
    category: "정육/계란",
    content: [
      "닭가슴살",
      "소등심",
      "소안심",
      "돼지삼겹살",
      "돼지목살",
      "닭다리",
      "닭날개",
    ],
  },
  { category: "우유/유제품", content: ["우유", "요구르트"] },
];

export const usersIngredients = [
  {
    userId: "mh9406",
    ingredients: [
      { category: "쌀/잡곡", content: [] },
      { category: "과일", content: [] },
      { category: "채소", content: [] },
      { category: "정육/계란", content: [] },
      { category: "우유/유제품", content: [] },
    ],
  },
];
