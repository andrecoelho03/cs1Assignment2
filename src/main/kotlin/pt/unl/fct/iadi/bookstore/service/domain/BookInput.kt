package pt.unl.fct.iadi.bookstore.service.domain

import java.math.BigDecimal

data class BookInput(
    val title: String,
    val author: String,
    val price: BigDecimal,
    val image: String
)