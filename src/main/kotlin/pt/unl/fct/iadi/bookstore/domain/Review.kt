package pt.unl.fct.iadi.bookstore.domain

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.UUID

data class Review(
    @field:NotBlank
    @field:Schema(description = "Identifier of the review")
    val id: UUID,

    @field:NotBlank
    @field:Schema(description = "Identifier of the book")
    val isbn: String,

    @field:NotBlank
    @field:Size(min = 1, max = 5)
    @field:Schema(description = "Rating of the review")
    val rating: Int,

    @field:Size(max = 500)
    @field:Schema(description = "Comment of the review")
    val comment: String? = null
)