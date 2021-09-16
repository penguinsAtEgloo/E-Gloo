plugins {
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
}

group = "com.project"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    val jsonwebtoken = "0.11.2"
    val springfoxSwagger = "2.9.2"
    val modelMapper = "2.4.4"

    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("io.springfox:springfox-swagger-ui:$springfoxSwagger")
    implementation("io.springfox:springfox-swagger2:$springfoxSwagger")
    implementation("org.modelmapper:modelmapper:$modelMapper")
    implementation("io.jsonwebtoken:jjwt-api:$jsonwebtoken")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jsonwebtoken")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jsonwebtoken")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}