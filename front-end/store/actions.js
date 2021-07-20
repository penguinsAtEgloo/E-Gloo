import axios from "axios";

export const login = async ({ commit }, { userId, password }) => {
  let form = new FormData();
  form.append("userId", userId);
  form.append("password", password);

  await axios.post("http://localhost:8080/auth/login", form).then(res => {
    if (res.status == 200) {
      commit("LOGIN", userId);
    } else {
      throw new Error(
        "로그인에 실패했습니다. 아이디와 비밀번호를 확인 해 주세요."
      );
    }
  });
};

export const register = async (
  { commit },
  { name, userId, password, social, phoneNo, gender, email, address }
) => {
  let form = new FormData();
  form.append("userId", name);
  form.append("name", userId);
  form.append("password", password);
  form.append("social", social);
  form.append("phoneNo", phoneNo);
  form.append("gender", gender);
  form.append("email", email);
  form.append("address", address);

  await axios.post("http://localhost:8080/auth/signup", form).then(res => {
    if (res.status == 200) {
      commit("LOGIN", userId);
    } else {
      throw new Error("회원가입에 실패했습니다.");
    }
  });
};
