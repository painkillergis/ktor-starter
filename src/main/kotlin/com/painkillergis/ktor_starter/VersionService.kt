package com.painkillergis.ktor_starter

class VersionService {
  fun get() = javaClass.classLoader.getResource("version")!!.readText()
}
