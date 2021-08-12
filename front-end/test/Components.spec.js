import { mount, shallowMount, createLocalVue } from "@vue/test-utils";
import GeneralInput from "@/components/GeneralInput.vue";
import Login from "@/pages/Login.vue"
import Grid from "@/components/Grid.vue";
import Ingredient from "@/components/Ingredient.vue";
import Navbar from "@/components/Navbar.vue";
import Notification from "@/components/Notification.vue";
import RegisterModal from "@/components/RegisterModal.vue";
import SwitchTabInput from "@/components/SwitchTabInput.vue";
import TabItem from "@/components/TabItem.vue";
import Recipe from "@/components/Recipe.vue";

import Index from "@/pages/Index.vue"
import axios from "axios"
import Vuex from "vuex"

const localVue = createLocalVue()
localVue.use(Vuex)

describe("GeneralInput", () => {
  test("is a Vue instance", () => {
    const wrapper = mount(GeneralInput);
    expect(wrapper.vm).toBeTruthy();
  });
});

describe("Login", () => {
  let actions
  let getters
  let store

  beforeEach(() => {
    actions = {
      //action함수 : jest.fn()
    }
    getters = {
      isAuthenticated: () => true
    }
    store = new Vuex.Store({
      getters,
      actions
    });
  })

  test("is a Vue instance", () => {
    const wrapper = mount(Login);
    expect(wrapper.vm).toBeTruthy();
  });

  test("correct ID/PW login", async () =>{ 
    await axios.post(
      "http://127.0.0.1:3333/api/login", 
      {
        email : "abcd@email.com",
        password : "1234"
      }
    ).then(res => {
      expect(res.status).toBe(200);
    }).catch(e => {
      throw new Error("server down");
    })
  })

  test("Incorrect ID/PW login", async () =>{ 
    await axios.post(
      "http://127.0.0.1:3333/api/login", 
      {
        email : "abcd@email.com",
        password : "wrong_password"
      }
    ).then(res => {

    }).catch(e => {
      expect(e.response.status).toBe(400);
    })
  })
  
  test("rendering with authorized", ()=>{
    const wrapper = shallowMount(Index, {
      mocks:{
        //nuxt-auth mocking
        $auth:{
          isAuthenticated : true
        },
        //services/logging mocking
        $logging : {log : jest.fn()}
      },
      store,
      localVue
    });
    expect(wrapper.vm).toBeTruthy();
  })

  test("rendering with not authorized", ()=>{
    const wrapper = shallowMount(Index, {
      mocks:{
        //nuxt-auth mocking
        $auth:{
          isAuthenticated : false
        },
        //services/logging mocking
        $logging : {log : jest.fn()}
      },
      store,
      localVue
    });
    expect(wrapper.vm).toBeTruthy();
  })
});

describe("Grid", () => {
  test("is a Vue instance", () => {
    const wrapper = mount(Grid);
    expect(wrapper.vm).toBeTruthy();
  });
});

describe("Navbar", () => {
  test("is a Vue instance", () => {
    const wrapper = mount(Navbar);
    expect(wrapper.vm).toBeTruthy();
  });
});

describe("Notification", () => {
  test("is a Vue instance", () => {
    const wrapper = mount(Notification);
    expect(wrapper.vm).toBeTruthy();
  });
});

describe("RegisterModal", () => {
  test("is a Vue instance", () => {
    const wrapper = mount(RegisterModal);
    expect(wrapper.vm).toBeTruthy();
  });
});

describe("SwitchTabInput", () => {
  test("is a Vue instance", () => {
    const wrapper = mount(SwitchTabInput);
    expect(wrapper.vm).toBeTruthy();
  });
});

describe("TabItem", () => {
  test("is a Vue instance", () => {
    const wrapper = mount(TabItem);
    expect(wrapper.vm).toBeTruthy();
  });
});

describe("Ingredient", () => {
  test("is a Vue instance", () => {
    const wrapper = mount(Ingredient);
    expect(wrapper.vm).toBeTruthy();
  });
  test("has imageUrl", () => {
    const wrapper = mount(Ingredient, {
      propsData: {
        ingredient: {
          name: "재료 테스트",
          id: 1,
          bookmarked: false,
          imageUrl: ""
        }
      }
    });
    expect(wrapper.vm).toBeTruthy();
    expect(wrapper.vm.cssVars).toEqual({ "--image-url": "#e0e0e0" });
  });
  test("does not have imageUrl", () => {
    const wrapper = mount(Ingredient, {
      propsData: {
        ingredient: {
          name: "재료 테스트",
          id: 1,
          bookmarked: false,
          imageUrl: "test.com"
        }
      }
    });
    expect(wrapper.vm.cssVars).toEqual({ "--image-url": "url(test.com)" });
  });
});

describe("Recipe", () => {
  test("is a Vue instance", () => {
    const wrapper = mount(Recipe);
    expect(wrapper.vm).toBeTruthy();
  });
});
// var componentList = [
//   GeneralInput,
//   Grid,
//   Ingredient,
//   Navbar,
//   Notification,
//   Recipe,
//   RegisterModal,
//   SwitchTabInput,
//   TabItem
// ];

// componentList.forEach(component => {
//   describe(component.toString, () => {
//     test("is a Vue instance", () => {
//       const wrapper = mount(GeneralInput);
//       expect(wrapper.vm).toBeTruthy();
//     });
//   });
// });
