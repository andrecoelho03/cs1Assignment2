package pt.unl.fct.iadi.bookstore.service.domain

data class ReviewInput(
    val rating: Int,
    val comment: String? = null
)