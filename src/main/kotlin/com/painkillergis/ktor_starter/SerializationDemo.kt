package com.painkillergis.ktor_starter

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*

fun Application.configureSerialization() {
  install(ContentNegotiation) {
    json()
  }

  routing {
    get("/serialization-demo") {
      call.respond(mapOf("hello" to "world"))
    }
  }
}
