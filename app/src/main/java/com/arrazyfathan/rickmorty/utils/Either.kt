package com.arrazyfathan.rickmorty.utils

sealed class Either<out L, out R> {
    class Left<out L>(val left: L) : Either<L, Nothing>()
    class Right<out R>(val right: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(left)
            is Right -> fnR(right)
        }

    inline fun <V>fold(fnL: (L) -> V, fnR: (R) -> V): V =
        when (this) {
            is Left -> fnL(left)
            is Right -> fnR(right)
        }

}
