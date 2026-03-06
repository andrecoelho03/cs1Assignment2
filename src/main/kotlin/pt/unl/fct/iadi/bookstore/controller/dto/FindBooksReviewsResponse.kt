package pt.unl.fct.iadi.bookstore.controller.dto

import pt.unl.fct.iadi.bookstore.domain.Review

data class FindBooksReviewsResponse(
    val reviews: List<Review>
)