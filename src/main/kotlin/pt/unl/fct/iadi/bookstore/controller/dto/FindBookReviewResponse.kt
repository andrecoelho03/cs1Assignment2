package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

data class FindBookReviewResponse(
    @field:Schema(description = "Identifier of the review")
    val id: UUID,

    @field:Schema(description = "Rating of the review")
    val rating: Int,

    @field:Schema(description = "Comment of the review")
    val comment: String? = null
)