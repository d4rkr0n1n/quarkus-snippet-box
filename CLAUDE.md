# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project state

This is an early-stage Quarkus project (`dev.you:snippet-box`), currently just past the default quickstart scaffold. There are only two REST resources and no persistence, service, or web layer yet — expect to be building most of the architecture from scratch rather than following an established pattern.

## Commands

Use the Maven wrapper (`mvnw.cmd` on Windows, `./mvnw` on Unix); no local Maven install is required.

- Dev mode (live coding, Dev UI at http://localhost:8080/q/dev/): `mvnw.cmd quarkus:dev`
- Run all tests: `mvnw.cmd test`
- Run a single test class: `mvnw.cmd test -Dtest=GreetingResourceTest`
- Run a single test method: `mvnw.cmd test -Dtest=GreetingResourceTest#testHelloEndpoint`
- Package (produces `target/quarkus-app/quarkus-run.jar`): `mvnw.cmd package`
- Package as über-jar: `mvnw.cmd package -Dquarkus.package.jar.type=uber-jar`
- Native executable build: `mvnw.cmd package -Dnative`
- Native executable build in container (no local GraalVM needed): `mvnw.cmd package -Dnative -Dquarkus.native.container-build=true`
- Integration tests (`*IT` classes, run against the packaged app via `@QuarkusIntegrationTest`) are bound to the Failsafe `integration-test`/`verify` goals and are skipped by default (`skipITs=true`); the `native` Maven profile flips `skipITs` to `false` so they run as part of a native build/verify.

There is no linter or formatter plugin configured in `pom.xml`.

## Architecture

- Java 21, Quarkus 3.38.0, packaging type `quarkus` (not a plain jar).
- REST endpoints use Jakarta REST (JAX-RS) via the `quarkus-rest` extension — resources are plain classes annotated with `@Path`/`@GET`/`@Produces`, no `Application` subclass needed.
- CDI (`quarkus-arc`) is available but not yet used by any resource.
- All source lives under the single package `dev.you` (`src/main/java/dev/you`); there is no layering (controller/service/repository) yet.
- `src/main/resources/application.properties` exists but is currently empty.
- Tests use `@QuarkusTest` + REST Assured (`given()/when()/then()`) for HTTP-level assertions; the corresponding `@QuarkusIntegrationTest` subclass (e.g. `GreetingResourceIT`) reruns the same test body against the packaged artifact — when adding a new resource test, follow this same Test/IT pair pattern.
- Docker build files for JVM, legacy-jar, native, and native-micro images live under `src/main/docker/`.