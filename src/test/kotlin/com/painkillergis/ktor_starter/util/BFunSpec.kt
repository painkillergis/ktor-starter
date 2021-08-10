package com.painkillergis.ktor_starter.util

import io.kotest.core.spec.style.FunSpec

abstract class BFunSpec(val body: FunSpec.() -> Unit) : FunSpec({
  listeners(EmbeddedServerTestListener.ServerStart)
  body(this)
})