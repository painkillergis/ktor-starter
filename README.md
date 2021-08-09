# ktor-starter
## Building a jarfile
```sh
./gradlew clean shadowJar
```
A jarfile named `ktor-starter.jar` will be placed in `build/libs`
## Updating dependencies
```sh
./gradlew dependencies --write-locks
```
