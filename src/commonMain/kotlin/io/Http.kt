package io

import io.ktor.client.*

expect fun buildHttpClient(): HttpClient

expect fun runTest(block: suspend () -> Unit): Unit
