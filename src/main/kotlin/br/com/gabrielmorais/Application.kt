package br.com.gabrielmorais

import br.com.gabrielmorais.di.mainModule
import br.com.gabrielmorais.graphql.authSchema
import br.com.gabrielmorais.graphql.dessertSchema
import br.com.gabrielmorais.graphql.profileSchema
import br.com.gabrielmorais.graphql.reviewSchema
import br.com.gabrielmorais.services.AuthService
import br.com.gabrielmorais.services.DessertService
import br.com.gabrielmorais.services.ProfileService
import br.com.gabrielmorais.services.ReviewService
import com.apurebase.kgraphql.GraphQL
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
@JvmOverloads
fun Application.module(testing: Boolean = false) {

  startKoin {
    modules(mainModule)
  }
  install(GraphQL) {
    val dessertService = DessertService()
    val reviewService = ReviewService()
    val authService = AuthService()
    val profileService = ProfileService()
    playground = true
    context { call ->
      authService.verifyToken(call)?.let { +it }
      +log
      +call
    }
    schema {
      profileSchema(profileService)
      dessertSchema(dessertService)
      reviewSchema(reviewService)
      authSchema(authService)
    }
  }
}

