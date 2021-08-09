package com.painkillergis.ktor_starter.version

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.versionController(versionService: VersionService) {
  routing {
    get("/version") {
      call.respond(
        mapOf(
          "version" to versionService.get()
        )
      )
    }
  }
}
