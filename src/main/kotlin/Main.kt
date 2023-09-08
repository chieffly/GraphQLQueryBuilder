import data.graphql.requests.SessionQueryGenerator
import java.util.*

fun main(args: Array<String>) {
    val sessionRequest = SessionQueryGenerator.generate(listOf("STARTED", "PLANNED"), Date().time)
    println(sessionRequest)
}