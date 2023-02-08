package br.com.gabrielmorais.graphql

import br.com.gabrielmorais.models.User
import br.com.gabrielmorais.services.ProfileService
import com.apurebase.kgraphql.Context
import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import java.lang.Exception

fun SchemaBuilder.profileSchema(profileService: ProfileService) {
  query("getProfile") {
    resolver { ctx: Context ->
      try {
        val userId = ctx.get<User>()?.id ?: error("Not signed in")
        profileService.getProfile(userId)
      } catch (e: Exception) {
        null
      }
    }
  }
}