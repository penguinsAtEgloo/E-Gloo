<template>
  <section class="section">
    <div class="container has-text-centered">
      <login v-if="!isAuthenticated"></login>
      <profile v-else></profile>
    </div>
  </section>
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
