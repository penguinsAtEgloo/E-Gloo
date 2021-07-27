<template>
  <div>
    <ValidationProvider :rules="rules" v-slot="{ required }">
      <label class="label" :for="category">
        <span>{{ label }}</span>
        <span>{{ required ? " *" : "" }}</span>
      </label>
      <div class="field switch-field">
        <template :items="items" v-for="item in items">
          <input
            @click="bind($event.target.value)"
            v-model="chekedValue"
            type="radio"
            :id="item.id"
            :key="item.id"
            :name="category"
            :value="item.id"
          />
          <label :for="item.id" :key="item.label">{{ item.label }}</label>
        </template>
      </div>
    </ValidationProvider>
  </div>
</template>
<script>
import { ValidationProvider } from "vee-validate";

export default {
  name: "SwitchTabInput",
  components: {
    ValidationProvider
  },
  props: {
    id: {
      default: ""
    },
    label: {
      default: ""
    },
    category: {
      default: ""
    },
    rules: {
      type: [Object, String],
      default: ""
    },
    items: {
      type: Array,
      default: ""
    }
  },
  computed: {
    chekedValue: {
      get() {
        return this.value;
      },
      set(newValue) {
        return newValue;
      }
    }
  },
  methods: {
    bind(value) {
      this.$emit("input", value);
    }
  }
};
</script>
<style scoped>
.label {
  text-align: left;
}
</style>
