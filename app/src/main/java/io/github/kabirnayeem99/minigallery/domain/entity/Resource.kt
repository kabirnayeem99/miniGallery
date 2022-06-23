package io.github.kabirnayeem99.minigallery.domain.entity

/**
 * A Resource class wrapper to indicate a Success, an Error, or a Loading state
 * @param T
 * @property data T?
 * @property message String?
 */
sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)
    class Loading<T> : Resource<T>(null, null)
}