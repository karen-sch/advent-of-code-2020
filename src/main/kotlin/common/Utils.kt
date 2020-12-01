package common

import java.io.File

fun Any.fileFromResources(filePath: String) = this::class.java.classLoader.getResource(filePath)?.toURI()?.let { File(it) }