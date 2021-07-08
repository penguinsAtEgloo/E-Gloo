<template>
  <section class="section">
    <div class="container">
      <div class="columns">
        <div class="column is-4 is-offset-4">
          <!-- TODO: X버튼 만들기 -->
          <h2 class="title has-text-centered">회원가입</h2>

          <Notification :message="error" v-if="error"/>

          <form method="post" @submit.prevent="register">
            <div class="field">
              <label class="label">이름 *</label>
              <div class="control">
                <input
                  type="text"
                  class="input"
                  name="username"
                  placeholder="이름을 입력해주세요"
                  v-model="username"
                  required
                />
              </div>
            </div>
            <div class="field">
              <label class="label">연락처 *</label>
              <div class="control">
                <input
                  type="tel"
                  class="input"
                  name="phoneNo"
                  placeholder="연락처 입력"
                  v-model="phoneNo"
                  required
                />
              </div>
            </div>
            <div class="field">
              <label class="label">비밀번호 *</label>
              <div class="control">
                <input
                  type="password"
                  class="input"
                  name="password"
                  placeholder="비밀번호 입력"
                  v-model="password"
                  required
                />
              </div>
            </div>
            <div class="field">
              <label class="label">비밀번호 확인 *</label>
              <div class="control">
                <input
                  type="password"
                  class="input"
                  name="passwordAuth"
                  placeholder="비밀번호 확인"
                  v-model="passwordAuth"
                  required
                />
              </div>
            </div>

            <div class="control">
              <button type="submit" class="button is-dark is-fullwidth">입력완료</button>
            </div>
          </form>

          <div class="has-text-centered" style="margin-top: 20px">
            Already got an account? <nuxt-link to="/">Login</nuxt-link>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Notification from '~/components/Notification'

export default {
  components: {
    Notification,
  },

  data() {
    return {
      username: '',
      phoneNo: '',
      password: '',
      passwordAuth: '',
      gender: '',
      email: '',
      address: '',
      error: null
    }
  },

  methods: {

    async register() {
        try {
          await this.$axios.post('register', {
            username: this.username,
            phoneNo: this.phoneNo,
            password: this.password,
            gender: this.gender,
            email: this.email,
            address: this.address
          })

          await this.$auth.loginWith('local', {
            data: {
              username: this.username,
              password: this.password,
            },
          })

          this.$router.push('/profile')
        } catch (e) {
          this.error = e.response.data.message
        }
    },

    
  }
}
</script>
<style scoped>
div + p {
     color: red;
}

input[type=radio] {
    display:none; 
    margin:10px;
}

input[type=radio] + label {
    display:inline-block;
    margin:-2px;
    padding: 4px 12px;
    background-color: #e7e7e7;
    border-color: #ddd;
}

input[type=radio]:checked + label { 
   background-image: none;
    background-color:#d0d0d0;
}
</style>