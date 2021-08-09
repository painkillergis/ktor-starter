import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  application
  id("com.github.johnrengelman.shadow") version "7.0.0"
  kotlin("jvm") version "1.5.21"
  kotlin("plugin.serialization") version "1.5.21"
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
  implementation("ch.qos.logback:logback-classic:1.2.3")
  implementation("io.ktor:ktor-serialization:1.6.2")
  implementation("io.ktor:ktor-server-core:1.6.2")
  implementation("io.ktor:ktor-server-netty:1.6.2")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
  testImplementation("io.kotest:kotest-assertions-core:4.6.1")
  testImplementation("io.ktor:ktor-server-tests:1.6.2")
  testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.21")
}

tasks.jar {
  enabled = false
}

tasks.withType<ShadowJar> {
  archiveBaseName.set("ktor-starter")
  archiveClassifier.set("")
  archiveVersion.set("")
}

tasks.processResources {
  doLast {
    File("$buildDir/resources/main/version").writeText(version.toString())
  }
}