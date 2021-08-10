package com.painkillergis.ktor_starter.version

import com.painkillergis.ktor_starter.util.EmbeddedServerTestListener
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class VersionBTest : FunSpec({
  listeners(EmbeddedServerTestListener)

  test("get /version returns version") {
    EmbeddedServerTestListener.withEmbeddedServerHttpClient {
      get<HttpResponse>("/version").apply {
        status shouldBe HttpStatusCode.OK
        receive<Map<String, String>>()["version"] shouldMatch Regex("\\d+")
      }
    }
  }
})
