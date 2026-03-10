package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.URL
import pt.unl.fct.iadi.bookstore.service.domain.BookInput
import pt.unl.fct.iadi.bookstore.service.domain.UpdateBookInput
import java.math.BigDecimal

data class UpdateBookRequest(
    @field:Size(min = 1, max = 120)
    @field:Schema(description = "Title of the book")
    val title: String? = null,

    @field:Size(min = 1, max = 80)
    @field:Schema(description = "Author of the book")
    val author: String? = null,

    @field:Positive
    @field:Schema(description = "Price of the book")
    val price: BigDecimal? = null,

    @field:URL
    @field:Schema(description = "Remote address (URL) to an image of the book cover")
    val image: String? = null
) {
    fun toUpdateBookInput() = UpdateBookInput(
        title = title,
        author = author,
        price = price,
        image = image
    )
}