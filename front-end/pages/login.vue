<template>
  <div>
    <transition name="slideDown">
      <register-modal v-if="showModal" @close="showModal = false" />
    </transition>
    <div class="section has-text-centered">
      <h2 class="title">로그인</h2>
      <Notification :message="error" v-if="error" />
      <ValidationObserver ref="observer" class="has-text-left">
        <form method="post" @submit.prevent="login">
          <general-input
            name="이메일"
            v-model="loginData.userEmail"
            rules="required"
            classIn="input"
          />
          <general-input
            name="비밀번호"
            type="password"
            v-model="loginData.password"
            rules="required"
            classIn="input"
          />
          <div class="control">
            <button type="submit" class="button is-dark is-fullwidth">
              입력완료
            </button>
          </div>
        </form>
      </ValidationObserver>
      <div
        class="button"
        id="btn-open-register"
        @click="showModal = true"
        style="margin-top:20px"
      >
        회원가입
      </div>
    </div>
  </div>
</template>

<script>
import Notification from "~/components/Notification";
import { ValidationObserver } from "vee-validate";
import GeneralInput from "~/components/GeneralInput";
import RegisterModal from "@/components/RegisterModal";

export default {
  components: {
    Notification,
    ValidationObserver,
    GeneralInput,
    RegisterModal
  },

  data() {
    return {
      loginData: {
        userEmail: "",
        password: ""
      },
      error: null,
      showModal: false
    };
  },

  methods: {
    async login() {
      const isValid = await this.$refs.observer.validate();
      if (isValid) {
        this.$store
          .dispatch("login", { data: this.loginData })
          .catch(e => (this.error = e.message));
      }
    }
  }
};
</script>
