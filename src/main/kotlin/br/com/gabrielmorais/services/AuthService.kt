package br.com.gabrielmorais.services

import at.favre.lib.crypto.bcrypt.BCrypt
import br.com.gabrielmorais.models.UserInput
import br.com.gabrielmorais.models.UserResponse
import br.com.gabrielmorais.repository.UserRepository
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.mongodb.client.MongoClient
import org.koin.core.KoinComponent
import org.koin.core.inject

class AuthService : KoinComponent {
  private val client: MongoClient by inject()
  private val repo: UserRepository = UserRepository(client)
  private val secret: String = "secret"
  private val algorithm: Algorithm = Algorithm.HMAC256(secret)
  fun signIn(userInput: UserInput): UserResponse? {
    val user = repo.getUserByEmail(userInput.email) ?: error("No such user by that email")
    if (BCrypt.verifyer().verify(userInput.password.toByteArray(Charsets.UTF_8), user.hashPass).verified) {
      //todo Finish signAccessToken
      val token = signAccessToken(user.id)
      return UserResponse(token, user)
    }
    error("password is incorrect")
  }

  private fun signAccessToken(id: String): String {
    return JWT.create()
      .withIssuer("example")
      .withClaim("userId", id)
      .sign(algorithm)
  }

}