module.exports = {
  moduleNameMapper: {
    "^.+\\.svg(\\?inline)?$": "jest-svg-transformer",
    "^@/(.*)$": "<rootDir>/$1",
    "^~/(.*)$": "<rootDir>/$1",
    "^vue$": "vue/dist/vue.common.js"
  },
  moduleFileExtensions: ["js", "vue", "json"],
  transform: {
    "^.+\\.js$": "babel-jest",
    ".*\\.(vue)$": "vue-jest",
    "vee-validate/dist/rules": "babel-jest",
    "vee-validate.js": "babel-jest"
  },
  transformIgnorePatterns: [
    "<rootDir>/node_modules/(?!vee-validate/dist/rules)",
    "<roodDir>/plugins/vee-validate.js"
  ],
  collectCoverage: true,
  collectCoverageFrom: [
    "<rootDir>/components/**/*.vue",
    "<rootDir>/pages/**/*.vue"
  ],
  testEnvironment: "jsdom",
  setupFilesAfterEnv: ["<rootDir>/test/jest.setup.js"]
};
