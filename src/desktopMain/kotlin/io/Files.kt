package io

import java.io.File
import kotlinx.coroutines.runBlocking

actual fun readAllText(filePath: String): String =
    File(filePath).readText()

actual fun writeAllText(filePath: String, text: String) {
    File(filePath).writeText(text)
}

actual fun writeAllLines(filePath: String, lines: List<String>) =
    File(filePath).writeText(lines.joinToString(separator = "\n"))

actual fun fileIsReadable(filePath: String): Boolean =
    File(filePath).canRead()

actual fun executeCommandAndCaptureOutput(
    command: List<String>,
    options: ExecuteCommandOptions
): String {
    val builder = ProcessBuilder()
    builder.command(command.filter { it.isNotBlank() })
    builder.directory(File(options.directory))
    val process = builder.start()
    val stdout = process.inputStream.bufferedReader().use { it.readText() }
    val stderr = process.errorStream.bufferedReader().use { it.readText() }
    val exitCode = process.waitFor()
    if (options.abortOnError) assert(exitCode == 0)
    val output = if (stderr.isBlank()) stdout else "$stdout $stderr"
    return if (options.trim) output.trim() else output
}


actual fun findExecutable(executable: String): String =
    executeCommandAndCaptureOutput(listOf("which", executable), ExecuteCommandOptions(".", true, false, true))

actual fun runTest(block: suspend () -> Unit): Unit =
    runBlocking { block() }