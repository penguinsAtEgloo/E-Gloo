<template>
  <div class="field">
    <ValidationProvider :rules="rules" v-slot="{ required }">
      <label class="label" :for="name">
        <span>{{ label || name }}</span>
        <span>{{ required ? " *" : "" }}</span>
      </label>
      <input
        :type="type"
        class="input"
        :name="name"
        :placeholder="placeholder"
        v-bind:value="value"
        :required="required"
        @input="$emit('input', $event.target.value)"
        @focus="$emit('focus')"
      />
    </ValidationProvider>
  </div>
</template>

<script>
import { ValidationProvider } from "vee-validate";

export default {
  name: "GeneralInput",
  components: {
    ValidationProvider
  },
  props: {
    type: {
      default: "text"
    },
    name: {
      default: ""
    },
    label: {
      default: ""
    },
    rules: {
      type: [Object, String],
      default: ""
    },
    placeholder: {
      default: ""
    },
    value: {
      default: ""
    }
  }
};
</script>
<style scoped>
.label {
  text-align: left;
}
</style>
