<template>
  <div>
    <form @submit.prevent>
      <label for="name">성별</label>
      <input v-model="user.name" id="name" type="text" />
      <label for="password">이메일</label>
      <input v-model="user.password" id="password" type="text" />
      <label for="password2">주소입력</label>
      <NuxtLink to="/profile" class="button">건너뛰기</NuxtLink>
    </form>
  </div>
</template>
<script>
export default {
  layout: 'auth',
  data() {
    return {
      user: {
        name: '',
        password: ''
      },
      selected: [], // Must be an array reference!
      options: [
        { text: 'Orange', value: 'orange' },
        { text: 'Apple', value: 'apple' },
        { text: 'Pineapple', value: 'pineapple' },
        { text: 'Grape', value: 'grape' }
      ]
    }
  }
}
</script>
<style scoped>
div {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

form {
  display: flex;
  flex-direction: column;
}

label {
  font-size: 14px;
  margin: 0 0.5rem;
  margin-bottom: 0.25rem;
  text-align: left;
}

input {
  font-size: 16px;
  padding: 0.75rem 1rem;
  margin: 0 0.5rem;
  margin-bottom: 0.5rem;
  border: 1px solid #dedede;
  border-radius: 0.5rem;
  box-shadow: none;
  box-sizing: border-box;
}

.button {
  margin-top: 0.5rem;
}

.button[disabled] {
  cursor: default;
  opacity: 0.5;
  cursor: not-allowed;
}
.button {
  background: var(--primary-color);
  padding: 0.75rem 1.5rem;
  outline: none;
  border-radius: 0.5rem;
  font-size: 1rem;
  border: none;
  cursor: pointer;
  color: white;
  font-weight: 500;
  text-transform: uppercase;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  letter-spacing: 0.5px;
  margin: 0 0.5rem;
}

button:hover {
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1),
    0 2px 4px -1px rgba(0, 0, 0, 0.06);
  opacity: 0.8;
}
.custom-checkbox {
  min-height: 0vh;
  justify-content: left;
}
.container {
  min-height: 0vh;
  justify-content: left;
  border-color: black;
  border: 1px solid #dedede;
  border-radius: 0.5rem;
  padding: 0.75rem 0.75rem;
  margin: 0 0.5rem;
}
</style>
