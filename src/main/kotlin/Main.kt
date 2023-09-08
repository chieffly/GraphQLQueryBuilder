import data.graphql.QueryJson
import data.graphql.requests.SessionQueryGenerator
import java.util.*

fun main(args: Array<String>) {
    val sessionRequest = SessionQueryGenerator.generate(listOf("STARTED", "PLANNED"), Date().time)
    val query = QueryJson(sessionRequest)
    println(query.toJson())
}