import { extend, validate } from "vee-validate";
import { required, alpha, numeric, min, max } from "vee-validate/dist/rules";

extend("required", {
  ...required,
});

extend("alpha_dash", {
  ...alpha,
  message:
    "{_field_}는 알파벳이나 '-', '_', 숫자의 조합으로만 입력할 수 있습니다."
});

extend("min", min);
extend("max", max);
extend("numeric", numeric);

extend("digits_between", {
  async validate(value, { min, max }) {
    const res = await validate(value, `numeric|min:${min}|max:${max}`);
    return res.valid;
  },
  params: ["min", "max"],
  message: "{_field_}는 최소 {min}자리 이상, {max}자리 이하여야 합니다."
});
