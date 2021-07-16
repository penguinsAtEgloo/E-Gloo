<template>
  <div class="columns">
    <div class="column is-4 is-offset-4">
      <div class="button" @click="openModal('modal-full')">회원가입</div>
      <vs-modal
        ref="modal-full"
        remove-close-button
        removeHeader
        size="fullscreen"
        style="display:none"
      >
        <div class="button" @click="closeModal('modal-full')">X</div>
        <h2 class="title has-text-centered">회원가입</h2>
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
      </vs-modal>
    </div>
  </div>
</template>

<script>
import Notification from "~/components/Notification";
import { ValidationObserver } from "vee-validate";
import GeneralInput from "~/components/GeneralInput";
import SwitchTabInput from "~/components/SwitchTabInput";

export default {
  components: {
    Notification,
    ValidationObserver,
    GeneralInput,
    SwitchTabInput
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
      address: "",
      error: null,
      genders: [
        { label: "남성", id: "MALE" },
        { label: "여성", id: "FEMALE" }
      ]
    };
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
        address: this.address
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
    openModal(ref) {
      this.$refs[ref].open();
    },

    closeModal(ref) {
      this.$refs[ref].close();
    }
  }
};
</script>
