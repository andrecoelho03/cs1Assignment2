package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import pt.unl.fct.iadi.bookstore.service.domain.ReviewInput
import pt.unl.fct.iadi.bookstore.service.domain.UpdateReviewInput

data class UpdateReviewRequest(
    @field:Size(min = 1, max = 5)
    @field:Schema(description = "Rating of the review")
    val rating: Int? = null,

    @field:Size(max = 500)
    @field:Schema(description = "Comment of the review")
    val comment: String? = null
) {
    fun toUpdateReviewInput(): UpdateReviewInput {
        return UpdateReviewInput(
            rating = rating,
            comment = comment
        )
    }
}