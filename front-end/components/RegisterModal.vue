<template>
  <div class="modal-wrapper">
    <div class="modal-container">
      <div class="modal-body">
        <div class="modal-header">
          <multiply-icon @click="$emit('close')" />
          <span class="title">회원가입</span>
        </div>
        <ValidationObserver ref="observer">
          <form method="post" @submit.prevent="register">
            <ValidationProvider rules="required">
              <v-text-field
                placeholder="닉네임"
                v-model="registerData.username"
              />
            </ValidationProvider>
            <general-input
              rules="required|min:8,12|max:12|complex"
              name="비밀번호"
              placeholder="비밀번호 입력"
              type="password"
              v-model="registerData.password"
            />
            <general-input
              rules="required|confirmed:비밀번호"
              name="비밀번호 확인"
              placeholder="비밀번호 확인"
              type="password"
              v-model="registerData.passwordCheck"
            />
            <switch-tab-input
              :items="registerData.genders"
              :valueIn="registerData.gender"
              category="gender"
              v-model="registerData.gender"
              label="성별"
              rules=""
            />
            <general-input
              rules="email"
              name="이메일"
              v-model="registerData.email"
              placeholder="ID@example.com"
            />
            <general-input
              name="주소입력"
              v-model="registerData.address1"
              @focus="searchAddress"
              placeholder="우편번호 찾기"
              :initDisabled="address_disabled"
            >
              <MultiplyIcon
                style="width:1rem;height:1rem"
                v-show="address_disabled"
                @click="address_disabled = !address_disabled"
              />
            </general-input>
            <div v-show="address_disabled" ref="embed"></div>
            <general-input
              name="상세주소"
              v-model="registerData.address2"
              v-show="registerData.address1 != ''"
            />
            <div class="control">
              <button type="submit" class="button is-dark is-fullwidth">
                입력완료
              </button>
            </div>
          </form>
        </ValidationObserver>
      </div>
    </div>
  </div>
</template>
<script>
import Notification from "~/components/Notification";
import { ValidationObserver } from "vee-validate";
import SwitchTabInput from "~/components/SwitchTabInput";
import MultiplyIcon from "~/assets/images/multiply.svg?inline";
import daumMaps from "@/services/daumMaps";

export default {
  components: {
    Notification,
    ValidationObserver,
    SwitchTabInput,
    MultiplyIcon
  },
  data() {
    return {
      registerData: {
        social: "LOCAL",
        username: "",
        password: "",
        passwordCheck: "",
        gender: "",
        email: "",
        zonecode: "",
        address1: "",
        address2: "",
        genders: [
          { label: "남성", id: "MALE" },
          { label: "여성", id: "FEMALE" }
        ]
      },
      error: null,
      address_disabled: false
    };
  },
  head() {
    return daumMaps;
  },
  methods: {
    async register() {
      if (await this.$refs.observer.validate()) {
        this.$store
          .dispatch("register", {
            data: {
              username: this.registerData.username,
              email: this.registerData.email,
              password: this.registerData.password
            }
          })
          .then(
            await this.$store.dispatch("login", {
              data: {
                email: this.registerData.email,
                password: this.registerData.password
              }
            })
          )
          .catch(e => {
            // this.$refs.form.setErrors({
            //   이메일: ["이미 존재하는 이메일입니다."],
            //   닉네임: ["중복된 아이디가 존재합니다."]
            // });
          });
      }
    },
    searchAddress() {
      if (!this.postCode) {
        this.postCode = new window.daum.Postcode({
          width: "100%",
          oncomplete: data => {
            //roadAddress : 도로명주소, zonecode : 우편번호, buildingName : 건물명
            const { roadAddress, zonecode, buildingName } = data;

            this.registerData.zonecode = zonecode;
            this.registerData.address1 =
              roadAddress +
              (buildingName != "" ? " (" + buildingName + ")" : "");
            this.address_disabled = false;
          }
        });
      }
      this.postCode.embed(this.$refs.embed);
      this.address_disabled = true;
    }
  }
};
</script>
<style scoped>
.modal-wrapper {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: -webkit-fill-available;
  height: -webkit-fill-available;
  overflow: hidden;
}

.modal-container {
  margin: 0;
  height: 100%;
  width: 100%;
  padding: 0 2rem;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  overflow: scroll;
}
.modal-header {
  display: flex;
  margin-top: 1em;
  margin-bottom: 2em;
  color: black;
  font-style: normal;
  font-weight: 900;
  line-height: 35px;
  align-items: center;
}
.modal-body {
  margin: 20px 0;
}
.title {
  margin-left: 1rem;
}
</style>
