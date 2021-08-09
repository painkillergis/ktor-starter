package com.painkillergis.ktor_starter

import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class SerializationDemoTest {
  @Test
  fun testRoot() {
    withTestApplication({ configureSerialization() }) {
      handleRequest(HttpMethod.Get, "/serialization-demo").apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals("""{"hello":"world"}""", response.content)
      }
    }
  }
}