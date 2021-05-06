import cli.runHttPie
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    runBlocking {
        runHttPie(args)
    }
}
