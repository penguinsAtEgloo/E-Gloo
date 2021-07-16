import axios from "axios";

export const state = () => ({
  loggedIn: false,
  userId: null
});

export const mutations = {
  LOGIN(state, userId) {
    state.loggedIn = true;
    state.userId = userId;
    this.$router.push("/");
  },
  LOGOUT(state) {
    state.loggedIn = false;
    state.userId = null;
    this.$router.push("/");
  }
};

export const getters = {
  isAuthenticated(state) {
    return state.loggedIn;
  },
  loggedInUser(state) {
    return state.userId;
  }
};

export const actions = {
  async login({ commit }, { userId, password }) {
    let formData = new FormData();
    formData.append("userId", userId);
    formData.append("password", password);

    axios.post("http://localhost:8080/auth/login", formData).then(res => {
      if (res.data.code != 200) {
        throw new Error(
          "로그인에 실패했습니다. 아이디와 비밀번호를 확인 해 주세요."
        );
      } else {
        commit("LOGIN", userId);
      }
    });
  },

  async register({ commit }, data) {
    let formData = new FormData();
    for (var key in data) {
      formData.append(key, data[key]);
    }
    await axios
      .post("http://localhost:8080/auth/signup", formData)
      .then(res => {
        if (res.data.code != 200) {
          throw new Error("회원가입에 실패했습니다.");
        } else {
          commit("LOGIN", data["userId"]);
        }
      });
  }
};
