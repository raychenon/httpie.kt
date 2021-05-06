package cli

import com.github.ajalt.clikt.core.subcommands

suspend fun runHttPie(args: Array<String>) {
    val command: HttpieCommand = HttpieCommand().subcommands(Get(), Post())

    // Avoid returning status code 1 if no arguments provided
    if (args.isEmpty()) {
        println(command.getFormattedHelp())
        return
    }

    command.main(args)

    println(command.doNetworkCall())

}
