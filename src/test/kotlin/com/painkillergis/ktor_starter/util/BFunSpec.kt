package com.painkillergis.ktor_starter.util

import io.kotest.core.spec.style.FunSpec
import io.ktor.client.*

abstract class BFunSpec(val body: FunSpec.(HttpClient) -> Unit) : FunSpec({
  listeners(EmbeddedServerTestListener.ServerStart)
  body(this, EmbeddedServerTestListener.httpClient)
})