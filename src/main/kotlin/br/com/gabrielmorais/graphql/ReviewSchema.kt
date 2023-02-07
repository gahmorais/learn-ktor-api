package br.com.gabrielmorais.graphql

import br.com.gabrielmorais.models.Review
import br.com.gabrielmorais.models.ReviewInput
import br.com.gabrielmorais.services.ReviewService
import com.apurebase.kgraphql.Context
import com.apurebase.kgraphql.schema.dsl.SchemaBuilder

fun SchemaBuilder.reviewSchema(reviewService: ReviewService) {
  inputType<ReviewInput> {
    description = "The input of the review without the identifier"
  }

  type<Review> {
    description = "Review object with the attributes text and rating"
  }

  query("getReview") {
    description = "Get an existing review"
    resolver { reviewId: String ->
      try {
        reviewService.getReview(reviewId)
      } catch (e: Exception) {
        null
      }
    }
  }

  mutation("createReview") {
    description = "Create a new review"
    resolver { dessertId: String, reviewInput: ReviewInput, ctx: Context ->
      try {
        val userId = "abc"
        reviewService.createReview(userId, dessertId, reviewInput)
      } catch (e: Exception) {
        null
      }
    }
  }

  mutation("updateReview") {
    description = "Update an existing review"
    resolver { reviewId: String, reviewInput: ReviewInput, ctx: Context ->
      try {
        val userId = "abc"
        reviewService.updateReview(userId, reviewId, reviewInput)
      } catch (e: Exception) {
        null
      }
    }
  }

  mutation("deleteReview") {
    description = "Delete a review"
    resolver { reviewId: String, ctx: Context ->
      try {
        val userId = "abc"
        reviewService.deleteReview(userId, reviewId)
      } catch (e: Exception) {
        null
      }
    }
  }

}