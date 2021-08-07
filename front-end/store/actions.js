import qs from "qs";

export const login = async function({ commit }, { data }) {
  data = qs.stringify(data);
  await this.$auth
    .loginWith("local", {
      data
    })
    .then(res => {
      if (res == 200) {
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

// export const register = async function(
//   { commit },
//   { name, userId, password, social, phoneNo, gender, email, address }
// ) {
//   let form = new FormData();
//   form.append("userId", name);
//   form.append("name", userId);
//   form.append("password", password);
//   form.append("social", social);
//   form.append("phoneNo", phoneNo);
//   form.append("gender", gender);
//   form.append("email", email);
//   form.append("address", address);

//   await this.$axios.post("auth/signup", form).then(res => {
//     if (res.status == 200) {
//       if (res.data.code == 200 && res.data.accessToken) {
//         commit("LOGIN", userId);
//       }
//     }
//     throw new Error(
//       "로그인에 실패했습니다. 아이디와 비밀번호를 확인 해 주세요."
//     );
//   });
// };
