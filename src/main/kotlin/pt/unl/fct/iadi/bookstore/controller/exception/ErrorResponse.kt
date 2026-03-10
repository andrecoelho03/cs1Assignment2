package pt.unl.fct.iadi.bookstore.controller.exception

data class ErrorResponse(
    val error: String,
    val message: String
)