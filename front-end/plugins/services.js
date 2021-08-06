import { LoggingService } from "@/services/logging.service";

export default ({ app }, inject) => {
  const allMethods = {
    logging: new LoggingService("Egloo")
  };

  const methods = Object.keys(allMethods);
  methods.forEach(method => {
    inject(method, allMethods[method]);
  });
};
