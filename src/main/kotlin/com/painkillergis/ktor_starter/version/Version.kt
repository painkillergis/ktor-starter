package com.painkillergis.ktor_starter.version

import kotlinx.serialization.Serializable

@Serializable
data class Version(
  val sha: String,
  val version: String,
)