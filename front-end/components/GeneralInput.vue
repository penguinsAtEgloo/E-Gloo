<template>
  <ValidationProvider
    tag="div"
    class="field"
    :rules="rules"
    v-slot="{ required, errors, failedRules }"
  >
    <label class="label" :for="name">
      <span>{{ name }}</span>
      <span>{{ required ? " *" : "" }}</span>
    </label>
    <input
      class="input"
      :class="{ 'needs-value': failedRules['required'] }"
      :type="type"
      :name="name"
      :placeholder="placeholderMsg"
      :value="value"
      @input="$emit('input', $event.target.value)"
      @focus="$emit('focus')"
    />
    <span class="warning">{{ !failedRules["required"] ? errors[0] : "" }}</span>
  </ValidationProvider>
</template>

<script>
import { ValidationProvider } from "vee-validate";
import batchimEnding from "@/plugins/batchimEnding";

export default {
  name: "GeneralInput",
  components: {
    ValidationProvider
  },
  props: {
    type: String,
    name: String,
    value: String,
    rules: {
      type: [Object, String],
      default: "{}"
    }
  },
  computed: {
    placeholderMsg() {
      return this.name + batchimEnding(this.name, "을") + " 입력해주세요.";
    }
  }
};
</script>
