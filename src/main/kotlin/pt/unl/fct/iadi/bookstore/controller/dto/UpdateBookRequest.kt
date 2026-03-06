package pt.unl.fct.iadi.bookstore.controller.dto

data class UpdateBookRequest(
    val title: String? = null,
    val author: String? = null,
    val price: Double? = null,
    val image: String? = null
)