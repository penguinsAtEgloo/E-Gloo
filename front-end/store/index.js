import axios from "axios";

export const state = () => ({
  loggedIn: false,
  userId: null
});

export const mutations = {
  LOGIN(state, userId) {
    state.loggedIn = true;
    state.userId = userId;
  },
  LOGOUT(state) {
    state.loggedIn = false;
    state.userId = null;
    this.$router.push('/');
  }
};

export const getters = {
  isAuthenticated(state) {
    return state.loggedIn;
  },
  loggedInUser(state) {
    return state;
  }
};

export const actions = {
  async login({ commit }, { userId, password }) {
    let form = new FormData();
    form.append("userId", userId);
    form.append("password", password);

    let { status } = await axios.post("http://localhost:8080/member/login", form);
    if (status != 200) {
      throw new Error("로그인에 실패했습니다.");
    }
    commit("LOGIN", userId);
  }
};
