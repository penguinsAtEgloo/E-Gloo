export class LoggingService {
  constructor(prefix) {
    this.prefix = prefix;
  }

  log(message) {
    console.log(`[${this.prefix}] ${message}`);
  }

  debug(message) {
    console.debug(`[${this.prefix}] ${message}`);
  }

  warn(message) {
    console.warn(`[${this.prefix}] ${message}`);
  }
}
