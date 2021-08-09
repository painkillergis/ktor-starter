package com.painkillergis.ktor_starter.util

import com.painkillergis.ktor_starter.controllers
import com.painkillergis.ktor_starter.globalModules
import io.ktor.server.testing.*

fun withConfiguredTestApplication(block: TestApplicationEngine.() -> Unit) =
  withTestApplication({
    globalModules()
    controllers()
  }, block)