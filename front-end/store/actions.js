export const login = async function({ commit }, { data }) {
  this.$auth
    .loginWith("local", {
      data
    })
    .then(res => {
      if (res.status == 200) {
        // console.log(res);
        return res;
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
    .post("/api/auth/register", data)
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

export const socialLogin = async function({ commit }, { socialType }) {
  switch (socialType) {
    case "kakao":
      const response = await this.$axios.get("/api/oauth2/authorization/kakao");
      console.log(response);
      break;
    case "naver":
      // this.$auth.loginWith("social");
      response = await this.$axios.get("/api/oauth2/authorization/naver");
      console.log(response);
      break;
    case "facebook":
      response = await this.$axios.get("/api/oauth2/authorization/facebook");
      console.log(response);
  }
};
