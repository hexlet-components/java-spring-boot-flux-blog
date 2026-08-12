plugins {
  jacoco
  alias(libs.plugins.spotless)
  alias(libs.plugins.versions)
  alias(libs.plugins.version.catalog.update)
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependency.management)
  alias(libs.plugins.test.logger)
  alias(libs.plugins.lombok)
  id("java")
  id("application")
}

repositories {
  mavenCentral()
}

java {
  toolchain { languageVersion = JavaLanguageVersion.of(25) }
}

dependencies {
  // Подключаем модуль Spring WebFlux
  implementation(libs.springBootStarterWebflux)
  testImplementation(libs.springBootStarterTest)
  testImplementation(libs.springBootStarterWebfluxTest)
  // Для асинхронного неблокирующего доступа к базе даных будем использовать стандарт r2dbc
  implementation(libs.springBootStarterDataR2dbc)
  // Но и jdbc все нужен для работы liquibase
  implementation(libs.springBootStarterJdbc)
  // Устанавливаем реактивный драйвер базы данных H2
  implementation(libs.r2dbcH2)
  runtimeOnly(libs.h2)
  implementation(libs.springBootStarterLiquibase)
  // Зависимость для тестирования реактивных приложений
  testImplementation(libs.reactorTest)
}

// Раньше JUnit Platform подставлял плагин Spring Boot, в 4.x этого не происходит,
// и тесты просто не обнаруживаются: «Executed 0 tests».
tasks.test {
  useJUnitPlatform()
}

tasks.jacocoTestReport { reports { xml.required.set(true) } }

application {
  mainClass.set("io.hexlet.App")
}

testlogger {
  showStandardStreams = true
}

spotless {
  java {
    importOrder()
    removeUnusedImports()
    googleJavaFormat().aosp()
    formatAnnotations()
    leadingTabsToSpaces(4)
    endWithNewline()
  }
}

// versionCatalogUpdate пишет свежие версии прямо в gradle/libs.versions.toml,
// поэтому руками их сверять не нужно. Ключи не сортируются: порядок в каталоге
// смысловой, по группам зависимостей.
versionCatalogUpdate {
  sortByKey = false
}
