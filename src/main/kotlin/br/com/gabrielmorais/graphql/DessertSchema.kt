package br.com.gabrielmorais.graphql

import br.com.gabrielmorais.models.Dessert
import br.com.gabrielmorais.models.DessertInput
import br.com.gabrielmorais.models.User
import br.com.gabrielmorais.services.DessertService
import com.apurebase.kgraphql.Context
import com.apurebase.kgraphql.schema.dsl.SchemaBuilder

fun SchemaBuilder.dessertSchema(dessertService: DessertService) {

  inputType<DessertInput> {
    description = "The input of the dessert without identifier"
  }

  type<Dessert> {
    description = "Dessert object with attributes name, description and imageUrl"
  }

  query("dessert") {
    resolver { dessertId: String ->
      try {
        dessertService.getDessert(dessertId)
      } catch (e: Exception) {
        null
      }
    }
  }

  query("desserts") {
    resolver { page: Int?, size: Int? ->
      try {
        dessertService.getDessertsPage(page ?: 0, size ?: 10)
      } catch (e: Exception) {
        null
      }
    }
  }

  mutation("createDessert") {
    description = "Create a new dessert"
    resolver { dessertInput: DessertInput, ctx: Context ->
      try {
        val userId = ctx.get<User>()?.id ?: error("not signed in")
        dessertService.createDessert(dessertInput, userId)
      } catch (e: Exception) {
        null
      }
    }
  }

  mutation("deleteDessert") {
    resolver { dessertId: String, ctx: Context ->
      try {
        val userId = ctx.get<User>()?.id ?: error("not signed in")
        dessertService.deleteDessert(userId, dessertId)
      } catch (e: Exception) {
        null
      }
    }
  }

  mutation("updateDessert") {
    resolver { dessertId: String, dessertInput: DessertInput, ctx: Context ->
      try {
        val userId = ctx.get<User>()?.id ?: error("not signed in")
        dessertService.updateDessert(userId, dessertId, dessertInput)
      } catch (e: Exception) {
        null
      }
    }
  }
}