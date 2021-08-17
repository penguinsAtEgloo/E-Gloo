<template>
  <ValidationProvider
    tag="div"
    class="field"
    :rules="rules"
    v-slot="{ required, errors, failedRules }"
    :vid="name"
  >
    <label class="label" :for="name">
      <span>{{ name }}</span>
      <span>{{ required ? " *" : "" }}</span>
      <span><slot></slot></span>
    </label>
    <input
      class="input"
      :class="{ 'required-warning': failedRules['required'] }"
      :type="type"
      :name="name"
      :placeholder="placeholder"
      :value="value"
      :disabled="isDisabled"
      @input="$emit('input', $event.target.value)"
      @focus="$emit('focus')"
    />
    <span class="warning">{{ !failedRules["required"] ? errors[0] : "" }}</span>
  </ValidationProvider>
</template>

<script>
import { ValidationProvider } from "vee-validate";

export default {
  name: "GeneralInput",
  components: {
    ValidationProvider
  },
  props: {
    type: String,
    name: String,
    value: String,
    placeholder: String,
    rules: {
      type: [Object, String],
      default: "{}"
    },
    initDisabled: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      disabled: this.initDisabled
    };
  },
  computed: {
    isDisabled() {
      return this.disabled ? this.disabled : undefined;
    }
  },
  watch: {
    initDisabled(newVal, val) {
      this.disabled = newVal;
    }
  }
};
</script>
