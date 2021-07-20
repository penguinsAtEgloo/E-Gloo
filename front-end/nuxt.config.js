export default {
  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: "front-end",
    htmlAttrs: {
      lang: "en"
    },
    meta: [
      { charset: "utf-8" },
      { name: "viewport", content: "width=device-width, initial-scale=1" },
      { hid: "description", name: "description", content: "" },
      { name: "format-detection", content: "telephone=no" }
    ],
    link: [
      { rel: "icon", type: "image/x-icon", href: "/favicon.ico" },
      {
        rel: "stylesheet",
        href:
          "https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css"
      }
    ]
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: ["@/assets/css/common.css"],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    "~/plugins/vee-validate",
    { src: "~/plugins/vs-modal", mode: "client" }
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://go.nuxtjs.dev/bootstrap
    "@nuxtjs/axios",
    // "@nuxtjs/auth",
    "@nuxtjs/svg"
  ],

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
    transpile: ["vee-validate/dist/rules"],
    /*
     ** You can extend webpack config here
     */
    extend(config, ctx) {}
  },

  axios: {
    baseURL: "http://localhost:8080"
  }

  // auth: {
  //   strategies: {
  //     local: {
  //       endpoints: {
  //         login: { url: "member/login", method: "post", propertyName: "data.token" },
  //         // user: { url: "me", method: "get", propertyName: "data" },
  //         logout: false
  //       }
  //     }
  //   }
  // }
};
