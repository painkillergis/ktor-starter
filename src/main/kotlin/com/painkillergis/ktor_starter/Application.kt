package com.painkillergis.ktor_starter

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun Application.globalModules() {
  install(ContentNegotiation) {
    json()
  }
}

fun Application.controllers() {
  versionController(
    VersionService(),
  )
}

fun main() {
  embeddedServer(
    Netty,
    port = 8080,
    host = "0.0.0.0",
  ) {
    globalModules()
    controllers()
  }
    .start(wait = true)
}
