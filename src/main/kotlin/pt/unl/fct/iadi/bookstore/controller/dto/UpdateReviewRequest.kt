package pt.unl.fct.iadi.bookstore.controller.dto

data class UpdateReviewRequest(
    val rating: Int,
    val comment: String
)