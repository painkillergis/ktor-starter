package com.painkillergis.ktor_starter

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*

fun Application.versionController() {
  install(ContentNegotiation) {
    json()
  }

  routing {
    get("/version") {
      call.respond(
        mapOf(
          "version" to javaClass.classLoader.getResource("version")!!.readText(),
        )
      )
    }
  }
}
