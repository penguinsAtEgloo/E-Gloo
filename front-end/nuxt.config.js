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
    link: []
  },
  router: {
    middleware: ["auth"]
  },
  // Global CSS: https://go.nuxtjs.dev/config-css
  css: ["@/assets/css/common.css", "@/assets/css/transition.css"],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    { src: "~/plugins/vee-validate", mode: "client" },
    { src: "~/plugins/axios", mode: "client" },
    { src: "~/plugins/services" }
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: ["@nuxtjs/vuetify"],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://go.nuxtjs.dev/bootstrap
    "@nuxtjs/axios",
    "@nuxtjs/auth-next",
    "@nuxtjs/svg"
  ],

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
    transpile: ["vee-validate/dist/rules", "@nuxtjs/auth"]
  },
  axios: {
    proxy: true
  },
  proxy: [
    [
      "/api",
      {
        target: "http://localhost:8080",
        pathRewrite: { "^/api": "" },
        changeOrigin: true,
        secure: process.env.NODE_ENV === "production",
        headers: {
          "Content-Type": "application/json"
        }
      }
    ]
  ],
  auth: {
    redirect: {
      login: "/login",
      logout: "/",
      callback: "/login",
      home: false
    },
    rewriteRedirects: true,
    strategies: {
      local: {
        token: {
          property: "token",
          global: true,
          type: "Bearer"
        },
        endpoints: {
          login: {
            url: "/api/api/v1/auth/login",
            method: "post",
            propertyName: "token"
          },
          logout: true,
          user: {
            url: "/api/api/v1/users/me",
            method: "post"
          }
        },
        user: {
          property: "user"
        }
      },
      kakao: {
        scheme: "oauth2",
        endpoints: {
          authorization: "/api/oauth2/authorization/kakao",
          token: "/api/api/v1/auth/login",
          userInfo: "/api/api/v1/users/me",
          logout: false
        },
        token: {
          property: "token",
          type: "Bearer",
          maxAge: 1800
        },
        responseType: "token",
        grantType: "authorization_code",
        accessType: undefined,
        redirectUri: undefined,
        logoutRedirectUri: undefined,
        clientId: "SET_ME",
        scope: ["openid", "profile", "email"],
        state: "UNIQUE_AND_NON_GUESSABLE",
        codeChallengeMethod: "",
        responseMode: "",
        acrValues: ""
        // autoLogout: false
      }
    }
  }
};
