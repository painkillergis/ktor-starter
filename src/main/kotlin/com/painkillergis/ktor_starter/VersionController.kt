package com.painkillergis.ktor_starter

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.versionController() {
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
