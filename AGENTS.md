# Developer Notes

## Commands
- `make build` - Clean build (gradle clean build)
- `make run` - Run application (gradle bootRun)
- `make test` - Run tests
- `make lint` - Run checkstyle
- `make report` - JaCoCo coverage report

## Requirements
- Java 24+
- Gradle 9.1.0 (wrapper)
- H2 database (file-based)

## Architecture
- Spring Boot WebFlux (reactive)
- Entry point: `io.hexlet.App`
- Controllers: `io.hexlet.controller.*`

## Database Config Quirk
- R2DBC uses: `r2dbc:h2:file:///./hexlet`
- JDBC (Liquibase) uses: `jdbc:h2:file:./hexlet`
- Same file, different path formats

## Testing
- Uses reactor-test for reactive assertions
- Tests located: `src/test/java/io/hexlet/`
