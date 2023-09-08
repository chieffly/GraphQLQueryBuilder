package data.graphql.requests

import data.graphql.GraphQLQueryBuilder

object SessionQueryGenerator {

    fun generate(statuses: List<String>, startedFrom: Long): String {

        val queryBuilder = GraphQLQueryBuilder()

        queryBuilder
            .edgesNode(
                nodeName = "session",
                "status" to statuses,
                "startedFrom" to startedFrom
            ) {
                field("id")
                field("isDefault")
                edgesNode("spot") {
                    field("id")
                    field("title")
                    field("address") {
                        field("city")
                        field("street")
                        field("building")
                    }
                }
                edgesNode("settings") {
                    field("isEarlyEndingAvailable")
                    field("isShowFastBuyButton")
                    field("isShowQuiz")
                }
            }

        return queryBuilder.build()
    }

}