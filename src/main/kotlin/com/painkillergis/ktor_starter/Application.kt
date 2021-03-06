package com.painkillergis.ktor_starter

import com.painkillergis.ktor_starter.version.VersionService
import com.painkillergis.ktor_starter.version.versionController
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.server.netty.EngineMain.main

fun main(args: Array<String>) = main(args)

fun Application.applicationModule() {
  versionController(
    VersionService(),
  )
  globalModules()
}

fun Application.globalModules() {
  install(ContentNegotiation) {
    jackson()
  }
}
