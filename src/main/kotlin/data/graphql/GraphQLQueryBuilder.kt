package data.graphql

class GraphQLQueryBuilder {
    private val queryBuilder = StringBuilder()

    fun field(fieldName: String, subfieldCallback: GraphQLQueryBuilder.() -> Unit = {}) {
        queryBuilder.append(fieldName)
        val subfieldQuery = buildSubString(subfieldCallback)
        if (subfieldQuery.isNotEmpty()) {
            queryBuilder.append("{$subfieldQuery}")
        }
        queryBuilder.append(",")
    }

    fun edgesNode(
        nodeName: String,
        vararg arguments: Pair<String, Any>,
        subfieldCallback: GraphQLQueryBuilder.() -> Unit = {}
    ) {
        queryBuilder.append(nodeName)
        if (arguments.isNotEmpty()) {
            val argumentsString = buildArgumentsString(*arguments)
            queryBuilder.append("($argumentsString)")
        }
        val subfieldQuery = buildSubString(subfieldCallback)
        queryBuilder.append("{edges{node{$subfieldQuery}}},")
    }

    private fun buildArgumentsString(vararg arguments: Pair<String, Any>): String {
        val argumentsBuilder = StringBuilder()
        arguments.forEach { (key, value) ->
            val argumentValue = when (value) {
                is String -> "\"$value\""
                is List<*> -> value.joinToString(",")
                else -> value.toString()
            }
            argumentsBuilder.append("$key: $argumentValue,")
        }
        return argumentsBuilder.toString().removeSuffix(",")
    }

    private fun buildSubString(subfieldCallback: GraphQLQueryBuilder.() -> Unit): String {
        val subfieldBuilder = GraphQLQueryBuilder()
        subfieldCallback(subfieldBuilder)
        return subfieldBuilder.build()
    }

    fun build(): String {
        return queryBuilder.toString().removeSuffix(",")
    }
}

