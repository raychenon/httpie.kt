package cli

import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    runBlocking {
        runHttPie(args)
    }
}
