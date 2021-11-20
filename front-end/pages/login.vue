<template>
  <div class="padding-top-20-percent">
    <div class="login-page-wrapper">
      <transition name="slideDown">
        <register-modal
          v-if="showRegisterModal"
          @close="showRegisterModal = false"
        />
      </transition>
      <div class="title-section">
        <h2 class="title">
          <span class="egloo">Egloo<br /></span>로그인 하기.
        </h2>
        <logo />
      </div>
      <div class="login-section">
        <Notification :message="error" v-if="error" />
        <ValidationObserver ref="observer" v-slot="{ invalid }">
          <form method="post" @submit.prevent="login">
            <ValidationProvider ref="provider" rules="required">
              <v-text-field
                label="이메일"
                type="email"
                v-model="loginData.userEmail"
              />
            </ValidationProvider>
            <ValidationProvider ref="provider" rules="required">
              <v-text-field
                label="비밀번호"
                type="password"
                v-model="loginData.password"
              />
            </ValidationProvider>
            <button type="submit" :disabled="invalid">
              로그인 하기
            </button>
          </form>
        </ValidationObserver>
      </div>
      <div class="social-section">
        <div class="hl-part">
          <span class="hl"></span><span class="guide">소셜 로그인</span
          ><span class="hl"></span>
        </div>
        <div class="social" @click="clickedSocial = '카카오톡'">
          <kakaotalk />
          <span class="vl"></span>
          <span class="text">카카오톡으로 로그인</span>
        </div>
        <div class="social" @click="clickedSocial = '네이버'">
          <naver />
          <span class="vl"></span>
          <span class="text">네이버로 로그인&emsp;&emsp;</span>
        </div>
        <div class="social" @click="clickedSocial = '구글'">
          <facebook />
          <span class="vl"></span>
          <span class="text">구글로 로그인</span>
        </div>
      </div>
      <div class="bottom-section">
        <span @click="showRegisterModal = true">
          회원가입
        </span>
        <span @click="showRegisterModal">
          아이디/비밀번호 찾기
        </span>
      </div>
    </div>

    <!--Modal-->
    <modal v-if="clickedSocial !== ''">
      <template slot="body">
        이글루에서 {{ socialMessage }}<br />열려고 합니다.
      </template>
      <template slot="footer">
        <button
          class="open-social-button"
          @click="$auth.loginWith(map[clickedSocial])"
        >
          열기
        </button>
        <button class="close-button" @click="clickedSocial = ''">
          닫기
        </button>
      </template>
    </modal>
  </div>
</template>

<script>
import Notification from "~/components/Notification";
import { ValidationObserver, ValidationProvider } from "vee-validate";
import RegisterModal from "@/components/RegisterModal";
import Modal from "@/components/Modal.vue";
import Logo from "~/assets/images/logo.svg?inline";
import Kakaotalk from "~/assets/images/kakaotalk.svg?inline";
import Naver from "~/assets/images/naver.svg?inline";
import Google from "~/assets/images/facebook.svg?inline";
import batchimEnding from "@/plugins/batchimEnding";

export default {
  // beforeRouteEnter(from, to, next) {
  //   // attempt to store the route full path (including parameters) so that we may redirect to it after a successful login.
  //   next(vm => vm.setRedirectUrl(to));
  // },
  components: {
    Notification,
    ValidationObserver,
    ValidationProvider,
    RegisterModal,
    Modal,
    Logo,
    Kakaotalk,
    Naver,
    Google
  },
  data() {
    return {
      loginData: {
        userEmail: "",
        password: ""
      },
      error: null,
      showRegisterModal: false,
      clickedSocial: "",
      redirectUrl: null,
      map: {
        네이버: "naver",
        카카오톡: "kakao",
        구글: "google"
      }
    };
  },
  computed: {
    socialMessage() {
      return this.clickedSocial + batchimEnding(this.clickedSocial, "을");
    }
  },
  methods: {
    setRedirectUrl(toRoute) {
      if (!this.redirectUrl) {
        this.redirectUrl = toRoute.path;
      }
      if (!["/", "/login", "/logout", "/sign-up"].includes(toRoute.path)) {
        this.redirectUrl = toRoute.fullPath;
      }
    },
    async login() {
      const isValid = await this.$refs.observer.validate();
      if (isValid) {
        this.$auth
          .loginWith("local", {
            data: this.loginData
          })
          .then(() => {
            if (this.redirectUrl) {
              this.$router.push(this.redirectUrl);
            } else {
              this.$router.push("/");
            }
          })
          .catch(e => (this.error = e.message));
      }
    },
    async social_login(socialType) {
      this.clickedSocial = "";
      this.$auth.loginWith(socialType);
    }
  }
};
</script>
<style>
div#__nuxt,
#__layout,
#__layout > div,
#app,
main,
main > div {
  height: 100vh;
}
.padding-top-20-percent {
  padding-top: 20vh;
  padding-bottom: 5vh;
}
.login-page-wrapper {
  margin: 0 auto;
  padding: 0;
  height: 75vh;
  width: 65%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  flex-wrap: wrap;
}
.login-page-wrapper .title-section {
  height: 12%;
  display: flex;
  flex-direction: row;
  align-items: flex-end;
}
.login-page-wrapper .title {
  font-style: normal;
  font-weight: bold;
  height: 100%;
  line-height: 100%;
  text-align: left;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.title > .egloo {
  color: #f98988;
}
.login-section {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.login-section button {
  color: #f98988;
  border: 1.5px solid #f98888;
  box-sizing: border-box;
  border-radius: 50px;
  height: 30%;
  width: 100%;

  font-style: normal;
  font-weight: bold;
  line-height: 300%;

  text-align: center;
  letter-spacing: 0.05rem;
}
.login-section button:active {
  background: #f98888;
  border-radius: 50px;
  color: white;
}
.social {
  display: flex;
  flex-direction: row;
  align-items: center;
  border: 1px solid #b9b9b9;
  box-sizing: border-box;
  border-radius: 5px;
  text-align: left;
  /* box-shadow: 1px 1px grey; */
  justify-content: center;
}
.social:active {
  background: #717171;
  box-shadow: none;
  border: none;
}
.social svg {
  filter: grayscale(100%);
}
.social:active svg {
  filter: contrast(103%) grayscale(100%);
}
.social:active .text {
  color: #c7c7c7;
}
.vl {
  border-left: 1px solid #e6e6e6;
  margin: 0 0.5rem;
  height: 1.3rem;
}
.social:active .vl {
  border-left: 2px solid #c7c7c7;
}
/* .hl {
} */
.text {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 1000;
  font-size: 0.75rem;
  line-height: 2.5rem;
  color: #c7c7c7;
}
.social-section {
  height: 40%;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}

.bottom-section {
  display: flex;
  justify-content: space-between;
}
.bottom-section span {
  font-weight: 500;
  font-size: 12px;
  line-height: 17px;

  color: #898989;
}
.modal-footer {
  display: flex;
  justify-content: space-between;
}
.hl-part {
  width: 100%;
  display: flex;
  flex-direction: row;
  color: #aeaeae;
  font-size: x-small;
  align-items: center;
}
.hl-part .guide {
  text-align: center;
  width: 100%;
}
.hl {
  width: 100%;
  padding: 0;
  margin: 0;
  height: 0px;
  border: 1px solid #c9c9c9;
}
form * {
  font-size: x-small !important;
  display: block !important;
  margin: 0 !important;
}
</style>
