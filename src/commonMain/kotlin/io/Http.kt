package io

import cli.CliConfig
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.Serializable

expect fun buildHttpClient(): HttpClient
