package cli

import cli.CliConfig.COMMAND_NAME
import com.github.ajalt.clikt.completion.completionOption
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import io.buildHttpClient
import io.ktor.client.request.*
import io.ktor.utils.io.core.*

class HttpieCommand : CliktCommand(
    help = """
       HTTPie—aitch-tee-tee-pie—is a user-friendly command-line HTTP client for the API era. 
       It comes with JSON support, syntax highlighting, persistent sessions, wget-like downloads, plugins, and more.
    """.trimIndent(),
    epilog = """
        Examples:
        
        http get --url  https://got-quotes.herokuapp.com/quotes
        
        http post --url http://httpbin.org/post
    """.trimIndent(),
    name = COMMAND_NAME
) {
    init {
        completionOption()
    }

    val help by option("-h", "--help", help = "Print this message").flag()

    override fun run() {
    }

    suspend fun doNetworkCall(): String {
        buildHttpClient().use { client ->
            val subcommand: CliktCommand? = this.currentContext.invokedSubcommand
            val result = when(subcommand){
                is Get -> client.get(subcommand.url!!)
                is Post -> client.post<String>(subcommand.url!!)
                else -> error("Unknown command $subcommand")
            }
            return result
        }
    }

}

// https://ajalt.github.io/clikt/arguments/
class Get : CliktCommand() {

    val url by option(help = "Specify URL")
    override fun run() {

    }


}

class Post : CliktCommand() {
    val url by option(help = "Specify URL")
    override fun run() {
    }
}



