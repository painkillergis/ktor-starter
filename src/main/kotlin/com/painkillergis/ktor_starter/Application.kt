package com.painkillergis.ktor_starter

import com.painkillergis.ktor_starter.version.VersionService
import com.painkillergis.ktor_starter.version.versionController
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun Application.globalModules() {
  install(ContentNegotiation) {
    jackson()
  }
}

fun Application.controllers() {
  versionController(
    VersionService(),
  )
}

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
  globalModules()
  controllers()
}
