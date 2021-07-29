<template>
  <div>
    <transition name="slideDown">
      <register-modal v-if="showModal" @close="showModal = false" />
    </transition>
    <div class="section has-text-centered">
      <h2 class="title">로그인</h2>
      <Notification :message="error" v-if="error" />
      <ValidationObserver>
        <form method="post" @submit.prevent="login">
          <general-input
            name="userId"
            v-model="userId"
            placeholder="아이디 입력"
            label="ID"
          />
          <general-input
            name="password"
            type="password"
            v-model="password"
            placeholder="비밀번호 입력"
            label="비밀번호"
          />
          <div class="control">
            <button type="submit" class="button is-dark is-fullwidth">
              입력완료
            </button>
          </div>
        </form>
      </ValidationObserver>
      <div class="button" @click="showModal = true" style="margin-top:20px">
        회원가입
      </div>
    </div>
  </div>
</template>

<script>
import Notification from "~/components/Notification";
import { ValidationObserver } from "vee-validate";
import GeneralInput from "~/components/GeneralInput";

export default {
  components: {
    Notification,
    ValidationObserver,
    GeneralInput
  },

  data() {
    return {
      userId: "",
      password: "",
      error: null,
      showModal: false
    };
  },

  methods: {
    login() {
      //토큰던져주게되면 auth로 변경할것 TODO
      // await this.$auth.loginWith('local', {
      //   data: form
      // })
      // .then(() => this.$toast.success('Logged In!'));
      this.$store
        .dispatch("login", {
          userId: this.userId,
          password: this.password
        })
        .catch(e => (this.error = e.message));
    }
  }
};
</script>
