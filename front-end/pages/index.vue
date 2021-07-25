<template>
  <div>
    <nuxt-link to="/recipes">Button</nuxt-link>
    <nuxt-link to="/ingredients">Button2</nuxt-link>
    <div class="container has-text-centered">
      <login v-if="!isAuthenticated"></login>
      <profile v-else></profile>
    </div>
  </div>
</template>

<script>
import Profile from "./profile";
import Login from "./login";
import { mapGetters } from "vuex";

export default {
  components: {
    Login,
    Profile
  },
  computed: {
    ...mapGetters(["isAuthenticated"])
  }
};
</script>
