package com.painkillergis.ktor_starter

import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class VersionControllerKtTest {
  @Test
  fun testVersion() {
    withTestApplication({
      globalModules()
      versionController()
    }) {
      handleRequest(HttpMethod.Get, "/version").apply {
        response.status() shouldBe HttpStatusCode.OK
        Json.decodeFromString<Map<String, String>>(response.content!!)["version"] shouldMatch Regex("\\d+")
      }
    }
  }
}