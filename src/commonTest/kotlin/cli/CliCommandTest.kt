package cli

import kotlin.test.Test
import kotlin.test.fail

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
