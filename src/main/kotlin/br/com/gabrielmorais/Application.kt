package br.com.gabrielmorais

import br.com.gabrielmorais.graphql.dessertSchema
import com.apurebase.kgraphql.GraphQL
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
@JvmOverloads
fun Application.module(testing: Boolean = false) {
  install(GraphQL) {
    playground = true
    schema {
      dessertSchema()
    }
  }
}

