package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.URL
import pt.unl.fct.iadi.bookstore.service.domain.BookInput
import java.math.BigDecimal

data class CreateBookRequest(
    @field:NotBlank
    @field:Size(min = 1, max = 120)
    @field:Schema(description = "Title of the book")
    val title: String,

    @field:NotBlank
    @field:Size(min = 1, max = 80)
    @field:Schema(description = "Author of the book")
    val author: String,

    @field:NotBlank
    @field:Positive
    @field:Schema(description = "Price of the book")
    val price: BigDecimal,

    @field:NotBlank
    @field:URL
    @field:Schema(description = "Remote address (URL) to an image of the book cover")
    val image: String
) {
    fun toBookInput() = BookInput(
        title = title,
        author = author,
        price = price,
        image = image
    )
}