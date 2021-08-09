package com.painkillergis.ktor_starter.version

class VersionService {
  fun get() = javaClass.classLoader.getResource("version")!!.readText()
}
