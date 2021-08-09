import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

plugins {
  application
  id("com.github.johnrengelman.shadow") version "7.0.0"
  kotlin("jvm") version "1.5.21"
  kotlin("plugin.serialization") version "1.5.21"
}

group = "com.painkillergis"
version = ProcessBuilder("sh", "-c", "git rev-list --count HEAD")
  .start()
  .apply {waitFor()}
  .inputStream.bufferedReader().readText().trim()

application {
  mainClass.set("com.painkillergis.ktor_starter.ApplicationKt")
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("ch.qos.logback:logback-classic:$logbackVersion")
  implementation("io.ktor:ktor-serialization:$ktorVersion")
  implementation("io.ktor:ktor-server-core:$ktorVersion")
  implementation("io.ktor:ktor-server-netty:$ktorVersion")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
  testImplementation("io.kotest:kotest-assertions-core:4.6.1")
  testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
  testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
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