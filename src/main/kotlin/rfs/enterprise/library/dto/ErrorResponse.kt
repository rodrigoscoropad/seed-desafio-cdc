package rfs.enterprise.library.dto

data class ErrorResponse(
    val message: String,
    val errors: List<ErrorDetail>
)


data class ErrorDetail(
    val field: String,
    val message: String
)