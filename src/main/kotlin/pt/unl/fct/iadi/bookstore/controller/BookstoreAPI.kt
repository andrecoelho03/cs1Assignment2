package pt.unl.fct.iadi.bookstore.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.headers.Header
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import pt.unl.fct.iadi.bookstore.controller.dto.CreateBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.CreateReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.UpdateBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.UpdateReviewRequest

@Tag(name = "Bookstore API", description = "the bookstore API")
interface BookstoreAPI {
    @Operation(
        summary = "Retrieve all books from the store",
        operationId = "findAllBooks",
        tags = ["book"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Found all books"
            )
        ]
    )
    @RequestMapping(
        value = ["/books"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun findAllBooks() : ResponseEntity<*>


    @Operation(
        summary = "Add a new book to the store",
        operationId = "createBook",
        tags = ["book"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Book created successfully",
                headers = [Header(
                    name = "Location",
                    description = "URI of the newly created book",
                    schema = Schema(type = "string", format = "uri")
                )]
            ),
            ApiResponse(
                responseCode = "400", description = "Validation failed"
            ),
            ApiResponse(
                responseCode = "409", description = "ISBN already associated to an existing book"
            )
        ]
    )
    @RequestMapping(
        value = ["/books"],
        consumes = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun createBook(
        @Parameter(
            description = "Book object that needs to be added to the store",
            required = true
        ) @Valid @RequestBody book: CreateBookRequest,
        req: HttpServletRequest
    ): ResponseEntity<*>


    @Operation(
        summary = "Get a single book from the store",
        operationId = "findBook",
        tags = ["book"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Book found successfully",
            ),
            ApiResponse(
                responseCode = "404",
                description = "Book not found",
                headers = [Header(
                    name = "Content-Language"
                )]
            )
        ]
    )
    @RequestMapping(
        value = ["/books/{isbn}"],
        produces = ["application/json"],
        consumes = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun findBook(
        @Parameter(
            description = "ISBN of the book to be searched",
            required = true,
        ) @Valid @PathVariable("isbn") isbn: String,
        @RequestHeader(value = "Accept-Language", required = false) acceptLanguage: String?
    ) : ResponseEntity<*>


    @Operation(
        summary = "Replace a book's information",
        operationId = "replaceBook",
        tags = ["book"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Book replaced successfully",
            ),
            ApiResponse(
                responseCode = "201",
                description = "Book didn't exist, created successfully",
            ),
            ApiResponse(
                responseCode = "204",
                description = "Book replaced successfully",
            ),
            ApiResponse(
                responseCode = "400",
                description = "Validation failed"
            )
        ]
    )
    @RequestMapping(
        value = ["/books/{isbn}"],
        consumes = ["application/json"],
        method = [RequestMethod.PUT]
    )
    fun replaceBook(
        @Parameter(
            description = "ISBN of the book to be replaced",
            required = true,
        ) @Valid @PathVariable("isbn") isbn: String,
        @Parameter(
            description = "Details of the book to be replaced",
            required = true,
        ) @Valid @RequestBody book: ReplaceBookRequest
    ): ResponseEntity<*>


    @Operation(
        summary = "Partially update a book's information",
        operationId = "updateBook",
        tags = ["book"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "Book updated successfully",
            ),
            ApiResponse(
                responseCode = "400",
                description = "ISBN mismatch"
            ),
            ApiResponse(
                responseCode = "404",
                description = "Book not found",
            )
        ]
    )
    @RequestMapping(
        value = ["/books/{isbn}"],
        consumes = ["application/json"],
        method = [RequestMethod.PATCH]
    )
    fun updateBook(
        @Parameter(
            description = "ISBN of the book to be updated",
            required = true,
        ) @Valid @PathVariable("isbn") isbn: String,
        @Parameter(
            description = "Details of the book to be updated",
            required = true,
        ) @Valid @RequestBody book: UpdateBookRequest
    ): ResponseEntity<*>


    @Operation(
        summary = "Delete a book",
        operationId = "deleteBook",
        tags = ["book"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "Book deleted",
            ),
            ApiResponse(
                responseCode = "404",
                description = "Book not found",
            )
        ]
    )
    @RequestMapping(
        value = ["/books/{isbn}"],
        method = [RequestMethod.DELETE]
    )
    fun deleteBook(
        @Parameter(
            description = "ISBN of the book to be deleted",
            required = true,
        ) @PathVariable("isbn") isbn: String
    ) : ResponseEntity<*>


    @Operation(
        summary = "See all reviews of a book",
        operationId = "findBookReviews",
        tags = ["review"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Found all book reviews",
            ),
            ApiResponse(
                responseCode = "400",
                description = "Validation failed"
            ),
            ApiResponse(
                responseCode = "404",
                description = "Book not found",
            )
        ]
    )
    @RequestMapping(
        value = ["/books/{isbn}/reviews"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun findBookReviews(
        @Parameter(
            description = "ISBN of the book to be found",
            required = true,
        ) @Valid @PathVariable("isbn") isbn: String
    ): ResponseEntity<*>


    @Operation(
        summary = "Create a new book review",
        operationId = "createBookReview",
        tags = ["review"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Book created successfully",
                headers = [Header(
                    name = "Location",
                    description = "URI of the newly created book",
                    schema = Schema(type = "string", format = "uri")
                )]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Validation failed"
            ),
            ApiResponse(
                responseCode = "404",
                description = "Book not found",
            )
        ]
    )
    @RequestMapping(
        value = ["/books/{isbn}/reviews"],
        consumes = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun createBookReview(
        @Parameter(
            description = "ISBN of the review to be created",
            required = true,
        ) @Valid @PathVariable("isbn") isbn: String,
        @Parameter(
            description = "Details of the review to be created",
            required = true,
        ) @Valid @RequestBody review: CreateReviewRequest,
        req: HttpServletRequest
    ): ResponseEntity<*>


    @Operation(
        summary = "Replace a book's review",
        operationId = "replaceBookReview",
        tags = ["review"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "Book replaced successfully"
            ),
            ApiResponse(
                responseCode = "400",
                description = "Validation failed"
            ),
            ApiResponse(
                responseCode = "404",
                description = " not found"
            )
        ]
    )
    @RequestMapping(
        value = ["/books/{isbn}/reviews/{id}"],
        consumes = ["application/json"],
        method = [RequestMethod.PUT]
    )
    fun replaceBookReview(
        @Parameter(
            description = "ISBN of the book to be replaced",
            required = true,
        ) @Valid @PathVariable("isbn") isbn: String,
        @Parameter(
            description = "Id of the review to be replaced",
            required = true,
        ) @Valid @PathVariable("id") id: String,
        @Parameter(
            description = "Details of the review to be replaced",
            required = true,
        ) @Valid @RequestBody review: ReplaceReviewRequest
    ): ResponseEntity<*>


    @Operation(
        summary = "Update a book review",
        operationId = "updateBookReview",
        tags = ["review"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "Book updated successfully",
            ),
            ApiResponse(
                responseCode = "404",
                description = "Book not found",
            )
        ]
    )
    @RequestMapping(
        value = ["/books/{isbn}/reviews/{id}"],
        consumes = ["application/json"],
        method = [RequestMethod.PATCH]
    )
    fun updateBookReview(
        @Parameter(
            description = "ISBN of the review to be updated",
            required = true,
        ) @Valid @PathVariable("isbn") isbn: String,
        @Parameter(
            description = "Id of the review to be updated",
            required = true,
        ) @Valid @PathVariable("id") id: String,
        @Parameter(
            description = "Details of the review to be updated",
            required = true,
        ) @Valid @RequestBody review: UpdateReviewRequest
    ): ResponseEntity<*>


    @Operation(
        summary = "Delete a book review",
        operationId = "deleteBookReview",
        tags = ["review"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "Book removed successfully",
            ),
            ApiResponse(
                responseCode = "404",
                description = "Book or Review not found",
            )
        ]
    )
    @RequestMapping(
        value = ["/books/{isbn}/reviews/{id}"],
        method = [RequestMethod.DELETE]
    )
    fun deleteBookReview(
        @Parameter(
            description = "ISBN of the review to be deleted",
            required = true,
        ) @PathVariable("isbn") isbn: String,
        @Parameter(
            description = "Id of the review to be deleted",
            required = true,
        ) @Valid @PathVariable("id") id: String
    ): ResponseEntity<*>
}