package pt.unl.fct.iadi.bookstore.controller.dto

data class ReplaceReviewRequest(
    val rating: Int,
    val comment: String? = null
)