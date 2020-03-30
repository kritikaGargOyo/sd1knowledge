package com.example.twofragmentactivity

import com.android.volley.Response
import com.android.volley.VolleyError

@Suppress("unused")
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: VolleyError): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error")
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccess) {
                val body = response.result
                if ( response.hashCode() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        body = body)
                }
            } else {
                val msg = response.error.message
                ApiErrorResponse(msg ?: "unknown error")
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(
    val body: T
) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String?) : ApiResponse<T>()