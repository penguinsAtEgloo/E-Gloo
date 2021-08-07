<template>
  <div>
    <nuxt-link to="/recipes">Button</nuxt-link>
    <nuxt-link to="/ingredients">Button2</nuxt-link>
    <login v-if="!isAuthenticated"></login>
    <profile v-else></profile>
  </div>
</template>

<script>
import Profile from "./profile";
import Login from "./login";
import { mapGetters } from "vuex";

export default {
  created() {
    this.$logging.log("index created!");
  },
  components: {
    Login,
    Profile
  },
  computed: {
    ...mapGetters(["isAuthenticated"])
  }
};
</script>
