<template>
  <nav class="navbar is-light">
    <div class="container">
      <div class="navbar-brand">
        <NuxtLink class="navbar-item" to="/">Home</NuxtLink>
        <!-- <div
          class="navbar-burger"
          v-on:click="showNav = !showNav"
          :class="{ 'is-active': showNav }"
        >
          <span></span>
          <span></span>
          <span></span>
        </div>
      </div>
      <div class="navbar-menu" :class="{ 'is-active': showNav }">
        <div class="navbar-end">
          <div
            class="navbar-item has-dropdown is-hoverable"
            v-if="isAuthenticated"
          >
            <a class="navbar-link">
              {{ loggedInUser.userId }}
            </a>
            <div class="navbar-dropdown">
              <NuxtLink class="navbar-item" to="/profile">My Profile</NuxtLink>
              <hr class="navbar-divider" />
              <a class="navbar-item" v-on:click="logout">Logout</a>
            </div>
          </div>
          <template v-else>
            <NuxtLink class="navbar-item" to="/login">Log In</NuxtLink>
          </template> -->
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";

export default {
  data() {
    return {
      showNav: false
    };
  },
  computed: {
    ...mapGetters(["isAuthenticated", "loggedInUser"])
  },
  methods: {
    ...mapMutations({
      logout: "LOGOUT"
    })
  }
};
</script>
<style scoped>
.navbar-item {
  color: #4a4a4a !important;
}
</style>
