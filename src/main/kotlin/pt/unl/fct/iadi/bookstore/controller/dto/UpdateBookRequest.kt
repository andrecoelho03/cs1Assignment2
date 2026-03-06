package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class UpdateBookRequest(
    @field:Size(min = 1, max = 120)
    @field:Schema(description = "Title of the book")
    val title: String,

    @field:Size(min = 1, max = 80)
    @field:Schema(description = "Author of the book")
    val author: String,

    @field:Positive
    @field:Schema(description = "Price of the book")
    val price: Double,

    @field:Pattern(regexp = "https?://.+", message = "must be a valid URL starting with https://")
    @field:Schema(description = "Remote address (URL) to an image of the book cover")
    val image: String
)