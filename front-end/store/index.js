const recipes = [];
for (let i = 1; i <= 23; i++) {
  recipes.push({
    recipeName: "요리 넘버 " + i,
    recipeId: i,
    bookmarked: Math.random() >= 0.5,
    imageUrl: "",
    duration: Math.floor(Math.random() * 60) + "분",
    difficulty: i + "급"
  });
}

recipes[0].imageUrl =
  "https://recipe1.ezmember.co.kr/cache/recipe/2021/01/03/ff04026cccdfaf4a6781f3f6a3e76fa21.jpg";
recipes[0].bookmarked = true;
recipes[1].imageUrl =
  "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYVZCxIDN1Q05Z2iN9yvhJLcAFR3EdbFyeug&usqp=CAU";
recipes[1].bookmarked = false;

export const state = () => ({
  loggedIn: false,
  userId: null,
  recipes: recipes
});
