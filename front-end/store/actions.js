export const login = async function({ commit }, { data }) {
  // data = qs.stringify(data);
  await this.$auth
    .loginWith("local", {
      data
    })
    .then(res => {
      if (res.status == 200) {
        console.log(res);
      } else {
        this.$logging.log(res);
        throw new Error(
          "로그인에 실패했습니다. 아이디와 비밀번호를 확인 해 주세요."
        );
      }
    })
    .catch(e => {
      this.$logging.log(e.message);
      throw new Error("ERROR");
    });
};

export const register = async function({ commit }, { data }) {
  // data = qs.stringify(data);
  await this.$axios
    .post("register", data)
    .then(res => {
      if (res.status == 200) {
        console.log(res);
      } else {
        this.$logging.log(res);
        throw new Error(
          "로그인에 실패했습니다. 아이디와 비밀번호를 확인 해 주세요."
        );
      }
    })
    .catch(e => {
      this.$logging.log(e.message);
      throw new Error("ERROR");
    });
};
