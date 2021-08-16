import { extend, setInteractionMode } from "vee-validate";
import {
  required,
  max,
  alpha_dash,
  confirmed,
  email
} from "vee-validate/dist/rules";
import batchimEnding from "@/plugins/batchimEnding";

setInteractionMode("aggressive");

extend("required", {
  ...required,
  message: "{_field_}" + batchimEnding("{_field_}", "을") + " 입력해주세요.",
  computesRequired: true
});

extend("alpha_dash", {
  ...alpha_dash,
  message:
    "{_field_}" +
    batchimEnding("{_field_}", "은") +
    " 알파벳이나 '-', '_', 숫자의 조합으로만 입력할 수 있습니다."
});

extend("max", {
  ...max,
  message: "{length}자 이상 입력 불가합니다."
});

extend("min", {
  validate(value, { min }) {
    return value.length >= min;
  },
  params: ["min", "max"],
  message:
    "{_field_}" +
    batchimEnding("{_field_}", "은") +
    " 최소 {min}자리 이상, {max}자리 이하여야 합니다."
});

extend("complex", {
  validate(value) {
    const regexp = /^((?=.*[a-z0-9])(?=.*[A-Z]).{8,20})|((?=.*[a-z0-9])(?=.*[^a-zA-Z0-9가-힣]).{8,20})$/g;
    return value.match(regexp) != null;
  },
  message: "영문 대소문자, 숫자, 특수기호를 포함한 두 가지 조합이 필요합니다."
});

extend("confirmed", {
  ...confirmed,
  message: "비밀번호가 일치하지 않습니다."
});

extend("email", {
  ...email,
  message: "올바른 이메일 형식이 아닙니다."
});
