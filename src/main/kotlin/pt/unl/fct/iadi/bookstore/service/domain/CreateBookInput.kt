package pt.unl.fct.iadi.bookstore.service.domain

data class CreateBookInput(
    val title: String,
    val author: String,
    val price: Double,
    val image: String
)