import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
  jacoco
  checkstyle
  id("org.springframework.boot") version "3.4.5"
  id("io.spring.dependency-management") version "1.1.7"
  id ("com.adarshr.test-logger") version "4.0.0"
  id("io.freefair.lombok") version "8.14"
  id("java")
  id("application")
}

repositories {
  mavenCentral()
}

tasks.compileJava {
  options.release.set(24)
}

dependencies {
  // Подключаем модуль Spring WebFlux
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  // Для асинхронного неблокирующего доступа к базе даных будем использовать стандарт r2dbc
  implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
  // Но и jdbc все нужен для работы liquibase
  implementation("org.springframework.boot:spring-boot-starter-jdbc")
  // Устанавливаем реактивный драйвер базы данных H2
  implementation("io.r2dbc:r2dbc-h2")
  runtimeOnly("com.h2database:h2:2.1.214")
  implementation("org.liquibase:liquibase-core")
  // Зависимость для тестирования реактивных приложений
  testImplementation("io.projectreactor:reactor-test")
}

tasks.jacocoTestReport { reports { xml.required.set(true) } }

application {
  mainClass.set("io.hexlet.App")
}

testlogger {
  showStandardStreams = true
}
