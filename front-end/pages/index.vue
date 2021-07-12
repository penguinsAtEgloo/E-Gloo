<template>
  <section class="section">
    <div class="container">
      <div class="columns">
        <div class="column is-4 is-offset-4">
          <h2 class="title has-text-centered">Welcome back!</h2>

          <Notification :message="error" v-if="error"/>

          <form method="post" @submit.prevent="login">
            <div class="field">
              <label class="label">ID</label>
              <div class="control">
                <input
                  type="userId"
                  class="input"
                  name="userId"
                  v-model="userId"
                />
              </div>
            </div>
            <div class="field">
              <label class="label">Password</label>
              <div class="control">
                <input
                  type="password"
                  class="input"
                  name="password"
                  v-model="password"
                />
              </div>
            </div>
            <div class="control">
              <button type="submit" class="button is-dark is-fullwidth">Log In</button>
            </div>
          </form>
          <div class="has-text-centered" style="margin-top: 20px">
            <p>
              Don't have an account? <nuxt-link to="/register">Register</nuxt-link>
            </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Notification from '~/components/Notification'

export default {
  middleware: 'authenticated',

  components: {
    Notification,
  },

  data() {
    return {
      userId: '',
      password: '',
      error: null
    }
  },

  methods: {
    login() {
      try {
        //토큰던져주게되면 auth로 변경할것 TODO
        // await this.$auth.loginWith('local', {
        //   data: form
        // })
        // .then(() => this.$toast.success('Logged In!'));
        this.$store.dispatch('login', {
          userId: this.userId,
          password: this.password
        }).then(() => this.$router.push('/profile'))
      } catch (e) {
        this.error = e.message
      }
    }
  }
}
</script>