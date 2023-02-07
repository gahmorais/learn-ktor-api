package br.com.gabrielmorais.graphql

import br.com.gabrielmorais.models.Dessert
import br.com.gabrielmorais.models.DessertInput
import br.com.gabrielmorais.repository.DessertRepository
import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import java.lang.Exception
import java.util.*

fun SchemaBuilder.dessertSchema() {
  val repository = DessertRepository()
  inputType<DessertInput> {
    description = "The input of the dessert without identifier"
  }

  type<Dessert> {
    description = "Dessert object with attributes name, description and imageUrl"
  }

  query("dessert") {
    resolver { dessertId: String ->
      try {
        repository.getById(dessertId)
      } catch (e: Exception) {
        null
      }
    }
  }

  query("desserts") {
    resolver { ->
      try {
        repository.getAll()
      } catch (e: Exception) {
        emptyList()
      }
    }
  }

  mutation("createDessert") {
    description = "Create a new dessert"
    resolver { dessertInput: DessertInput ->
      try {
        val uid = UUID.randomUUID().toString()
        val dessert = Dessert(uid, dessertInput.name, dessertInput.description, dessertInput.imageUrl)
        repository.add(dessert)
        dessert
      } catch (e: Exception) {
        null
      }
    }
  }

  mutation("deleteDessert") {
    resolver { dessertId: String ->
      try {
        repository.delete(dessertId)
        true
      } catch (e: Exception) {
        null
      }
    }
  }

  mutation("updateDessert") {
    resolver { dessertId: String, dessertInput: DessertInput ->
      try {
        val dessert = Dessert(dessertId, dessertInput.name, dessertInput.description, dessertInput.imageUrl)
        repository.update(dessert)
        dessert
      } catch (e: Exception) {
        null
      }
    }
  }
}