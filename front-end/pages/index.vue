<template>
  <section class="section">
    <div class="container">
      <div class="columns">
        <div class="column is-4 is-offset-4">
          <h2 class="title has-text-centered">Welcome back!</h2>

          <Notification :message="error" v-if="error" />

          <form method="post" @submit.prevent="login">
            <div class="field">
              <ValidationProvider rules="alpha_dash" v-slot="{ errors }">
                <label class="label">ID</label>
                <input
                  name="userId"
                  class="input"
                  id="userId"
                  v-model="userId"
                  type="text"
                  required
                />
              </ValidationProvider>
            </div>
            <div class="field">
              <ValidationProvider rules="" v-slot="{ errors }">
                <label class="label">비밀번호</label>
                <input
                  name="password"
                  class="input"
                  id="password"
                  type="password"
                  v-model="password"
                  required
                />
              </ValidationProvider>
            </div>
            <div class="control">
              <button type="submit" class="button is-dark is-fullwidth">
                Log In
              </button>
            </div>
          </form>
          <div class="has-text-centered control" style="margin-top: 20px">
            <div class="button" @click="openModal('modal-full')">회원가입</div>
            <vs-modal
              ref="modal-full"
              remove-close-button
              removeHeader
              size="fullscreen"
            >
              <div class="button" @click="closeModal('modal-full')">X</div>
              <register></register>
            </vs-modal>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Notification from "~/components/Notification";
import { ValidationProvider } from "vee-validate";
import Register from "~/components/Register";

export default {
  components: {
    Notification,
    ValidationProvider,
    Register
  },

  data() {
    return {
      userId: "",
      password: "",
      error: null
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
        .then(() => this.$router.push("/profile"))
        .catch(e => (this.error = e.message));
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
