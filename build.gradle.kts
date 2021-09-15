import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  application
  id("com.github.johnrengelman.shadow") version "7.0.0"
  kotlin("jvm") version "1.5.21"
}

group = "com.painkillergis"
version = ProcessBuilder("sh", "-c", "git rev-list --count HEAD")
  .start()
  .apply { waitFor() }
  .inputStream.bufferedReader().readText().trim()

application {
  mainClass.set("com.painkillergis.ktor_starter.ApplicationKt")
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("ch.qos.logback:logback-classic:+")
  implementation("io.ktor:ktor-jackson:+")
  implementation("io.ktor:ktor-serialization:+")
  implementation("io.ktor:ktor-server-core:+")
  implementation("io.ktor:ktor-server-netty:+")
  testImplementation("com.cruftbusters:ktor-baseurl-util:+")
  testImplementation("io.kotest:kotest-assertions-core:+")
  testImplementation("io.kotest:kotest-runner-junit5:+")
  testImplementation("io.ktor:ktor-client-cio:+")
  testImplementation("io.ktor:ktor-client-core:+")
  testImplementation("io.ktor:ktor-client-jackson:+")
  testImplementation("io.ktor:ktor-server-tests:+")
  testImplementation("io.mockk:mockk:+")
  testImplementation("org.jetbrains.kotlin:kotlin-test:+")
}

configurations.all {
  resolutionStrategy {
    activateDependencyLocking()
    componentSelection
      .all(object : Action<ComponentSelection> {
        @Mutate
        override fun execute(selection: ComponentSelection) {
          val version = selection.candidate.version
          when {
            version.matches(
              Regex(
                ".*[-.]rc\\d*$",
                RegexOption.IGNORE_CASE
              )
            ) -> selection.reject("Release candidates are excluded")
            version.matches(Regex(".*-M\\d+$")) -> selection.reject("Milestones are excluded")
            version.matches(Regex(".*-alpha\\d+$")) -> selection.reject("Alphas are excluded")
            version.matches(Regex(".*-beta\\d+$")) -> selection.reject("Betas are excluded")
          }
        }
      })
  }
}

tasks.jar {
  enabled = false
}

tasks.withType<ShadowJar> {
  archiveBaseName.set(rootProject.name)
  archiveClassifier.set("")
  archiveVersion.set("")
}

tasks.processResources {
  doLast {
    File("$buildDir/resources/main/version").writeText(version.toString())
  }
}

tasks.test {
  useJUnitPlatform()
  systemProperty("baseUrl", System.getProperty("baseUrl"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}
