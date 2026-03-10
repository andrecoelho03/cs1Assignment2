package pt.unl.fct.iadi.bookstore.service.domain

data class UpdateReviewInput(
    val rating: Int? = null,
    val comment: String? = null
)