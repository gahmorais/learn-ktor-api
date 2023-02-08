package br.com.gabrielmorais.graphql

import br.com.gabrielmorais.models.User
import br.com.gabrielmorais.models.UserInput
import br.com.gabrielmorais.services.AuthService
import com.apurebase.kgraphql.schema.dsl.SchemaBuilder

fun SchemaBuilder.authSchema(authService: AuthService) {
  mutation("signIn") {
    description = "Authenticate an existing user"
    resolver { userInput: UserInput ->
      try {
        authService.signIn(userInput)
      } catch (e: Exception) {
        null
      }
    }
  }

  mutation("signUp") {
    description = "Authenticate an new user"
    resolver { userInput: UserInput ->
      try {
        authService.signUp(userInput)
      } catch (e: Exception) {
        null
      }
    }
  }

  type<User>(){
    User::hashPass.ignore()
  }

}