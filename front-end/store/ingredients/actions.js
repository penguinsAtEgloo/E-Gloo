import { ingredients, usersIngredients } from "~/assets/data/db";

export const getIngredients = ({ commit }, { }) => {
  //Ingredients get : axios.get()으로 변경
  const fullIngredients = [...ingredients];

  commit("getIngredients", { ingredients: fullIngredients });
};

export const getUserIngredients = ({ commit }, { userId }) => {
  //UserIngredients get : axios.get()으로 변경
  //const userIngredients = userIngredients.filter((ig) => ig.userId === userId);
  const userIngredients = usersIngredients.filter(
    (ig) => ig.userId === "mh9406"
  );

  commit("getUserIngredients", { userIngredients });
};

//모든 재료 선택 완료 후 post하는지
//개별 재료 선택마다 post하는지 확인 필요
export const updateIngredients = async (
  { dispatch },
  { userId }
) => {
  //UserIngredients update : await axios.post()로 변경
};

export const selectIngredients = async (
  { state, commit },
  { category, content }
) => {
  const selectedIngredients = [...state.selectedIngredients];
  selectedIngredients.push({ category, content })
  commit("updateSelectedIngredients", { selectedIngredients });
};

export const exceptIngredients = async (
  { state, commit },
  { category, content }
) => {
  const selectedIngredients = [...state.selectedIngredients].filter(
    ig => !(ig.category === category && ig.content === content)
  )

  commit("updateSelectedIngredients", { selectedIngredients });
};
