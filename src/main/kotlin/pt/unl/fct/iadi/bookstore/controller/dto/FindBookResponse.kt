package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

data class FindBookResponse(
    @field:Schema(description = "Identifier of the book")
    val isbn: String,

    @field:Schema(description = "Title of the book")
    val title: String,

    @field:Schema(description = "Author of the book")
    val author: String,

    @field:Schema(description = "Price of the book")
    val price: BigDecimal,

    @field:Schema(description = "Remote address (URL) to an image of the book cover")
    val image: String
)