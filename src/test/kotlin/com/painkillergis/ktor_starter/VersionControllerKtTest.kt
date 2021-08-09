package com.painkillergis.ktor_starter

import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class VersionControllerKtTest {
  @Test
  fun testVersion() {
    withTestApplication({ versionController() }) {
      handleRequest(HttpMethod.Get, "/version").apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals("""{"version":"0.0.1"}""", response.content)
      }
    }
  }
}