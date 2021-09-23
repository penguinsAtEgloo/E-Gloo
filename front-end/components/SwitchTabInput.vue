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
            v-model="value"
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
    label: String,
    category: String,
    rules: {
      type: [Object, String],
      default: {}
    },
    items: {
      type: Array,
      default: []
    },
    valueIn: String
  },
  data() {
    return {
      value: this.valueIn
    };
  },
  watch: {
    valueIn(newVal, val) {
      this.value = newVal;
    }
  }
};
</script>
<style scoped>
.label {
  text-align: left;
}
</style>
