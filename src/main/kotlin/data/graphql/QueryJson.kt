package data.graphql

import com.google.gson.Gson

data class QueryJson(val query: String, val variables: String = "") {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}