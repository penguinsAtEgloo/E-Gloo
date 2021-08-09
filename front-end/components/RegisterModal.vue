<template>
  <div class="modal-wrapper">
    <div class="modal-container">
      <div class="modal-body">
        <div class="modal-header">
          <multiply-icon @click="$emit('close')" />
          <span class="title">회원가입</span>
        </div>
        <Notification :message="error" v-if="error" />
        <ValidationObserver>
          <form method="post" @submit.prevent="register">
            <general-input
              rules="required"
              label="이름"
              name="name"
              v-model="name"
              placeholder="이름을 입력해주세요."
            />
            <general-input
              rules="required"
              label="ID"
              name="userId"
              v-model="userId"
              placeholder="아이디를 입력해주세요."
            />
            <general-input
              rules="required"
              label="연락처"
              name="phoneNo"
              v-model="phoneNo"
              placeholder="연락처 입력"
            />
            <general-input
              rules="required"
              label="비밀번호"
              name="password"
              type="password"
              v-model="password"
              placeholder="비밀번호 입력"
            />
            <general-input
              rules="required"
              label="비밀번호 확인"
              name="passwordCheck"
              type="password"
              v-model="passwordCheck"
              placeholder="비밀번호 확인"
            />
            <general-input
              rules=""
              label="이메일"
              name="email"
              v-model="email"
              placeholder="ID@example.com"
            />
            <general-input
              rules=""
              label="주소입력"
              name="address1"
              v-model="address1"
              placeholder="우편번호 찾기"
              @focus="searchAddress"
            />
            <div ref="embed"></div>
            <general-input
              rules=""
              label="상세주소"
              name="address2"
              v-model="address2"
              placeholder="상세주소 입력"
            />
            <switch-tab-input
              :items="genders"
              category="gender"
              v-model="gender"
              label="성별"
              required=""
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
<script scr="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
import Notification from "~/components/Notification";
import { ValidationObserver } from "vee-validate";
import GeneralInput from "~/components/GeneralInput";
import SwitchTabInput from "~/components/SwitchTabInput";
import MultiplyIcon from "~/assets/images/multiply.svg?inline";

export default {
  components: {
    Notification,
    ValidationObserver,
    GeneralInput,
    SwitchTabInput,
    MultiplyIcon
  },
  data() {
    return {
      name: "",
      userId: "",
      phoneNo: "",
      password: "",
      social: "LOCAL",
      passwordCheck: "",
      gender: "MALE",
      email: "",
      error: null,
      zonecode:"",
      address1:"",
      address2:"",
      genders: [
        { label: "남성", id: "MALE" },
        { label: "여성", id: "FEMALE" }
      ]
    };
  },
  head () {
    return {
      script: [
        {
          hid: 'daumMap',
          src: '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js',
          defer: true,
        }
      ]
    }
  },
  methods: {
    register() {
      this.validateValues();

      var data = {
        userId: this.userId,
        name: this.name,
        phoneNo: this.phoneNo,
        password: this.password,
        social: this.social,
        gender: this.gender,
        email: this.email,
        address: "[" + this.zonecode + "]" + this.address1 + " " + this.address2
      };

      this.$store.dispatch("register", data).catch(e => {
        if (e.response) alert(e.response.data.message);
        else alert(e.message);
      });

      // this.$store.dispatch('login', {
      //   userId: this.userId,
      //   password: this.password
      // }).then(() => this.$router.push('/profile'))
    },

    validateValues() {
      var errorNum = 0;
      //아이디중복체크 TODO

      //번호 유효성 체크 TODO

      //비밀번호 일치여부 체크
      if (this.password != this.passwordCheck) {
        errorNum++;
        //TODO 비밀번호 일치오류 보여주는 부분
        alert("비밀번호가 불일치합니다.");
      }

      //추가 유효성 체크 부분

      if (errorNum > 0) return false;

      return true;
    },

    searchAddress() { 
      const postCode = new window.daum.Postcode({ 
        width:"100%",
        oncomplete: (data) => {
          //roadAddress : 도로명주소, zonecode : 우편번호, buildingName : 건물명
          const {roadAddress, zonecode, buildingName} = data;

          this.zonecode = zonecode;
          this.address1 = roadAddress + 
            (buildingName != "" ? 
                " (" + buildingName + ")" : "");
        }
      });
      postCode.embed(this.$refs.embed)
    },
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
