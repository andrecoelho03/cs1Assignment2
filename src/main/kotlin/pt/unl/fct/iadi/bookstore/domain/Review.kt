package pt.unl.fct.iadi.bookstore.domain

data class Review(
    val id: String,
    val rating: Int,
    val comment: String
)