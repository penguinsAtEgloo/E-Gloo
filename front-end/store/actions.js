import axios from "axios";

export const login = async ({ commit }, { userId, password }) => {
  let form = new FormData();
  form.append("userId", userId);
  form.append("password", password);

  await axios
    .post("http://localhost:8080/member/login", form)
    .then(res => {
      if (res.data.code != 200) {
        throw new Error(
          "로그인에 실패했습니다. 아이디와 비밀번호를 확인 해 주세요."
        );
      }
    })
    .then(commit("LOGIN", userId));
};

export const register = async (
  { commit },
  { name, userId, password, social, phoneNo, gender, email, address }
) => {
  let form = new FormData();
  form.append("userId", name);
  form.append("userId", userId);
  form.append("password", password);
  form.append("social", social);
  form.append("phoneNo", phoneNo);
  form.append("gender", gender);
  form.append("email", email);
  form.append("address", address);

  await axios
    .post("http://localhost:8080/member/signUp", form)
    .then(res => {
      if (res.data.code != 200) {
        throw new Error("회원가입에 실패했습니다.");
      }
    })
    .then(commit("LOGIN", userId));
};
