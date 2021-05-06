package cli

import com.github.ajalt.clikt.core.subcommands
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import io.runTest

class CliCommandTest {


    @Test
    fun `get httpbin`() = runTest {
        val command: HttpieCommand = HttpieCommand().subcommands(Get(), Post())
        command.main(listOf("get", "--url", "http://httpbin.org/get"))

        val response = command.doNetworkCall()
        println(response)
        assertTrue(response.contains("http://httpbin.org/get"))
    }
}
