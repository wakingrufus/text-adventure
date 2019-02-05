package com.github.wakingrufus.adventure

@DslMarker
annotation class GameDsl

fun <T> game(game: GAME<T>.() -> Unit): GAME<T> {
    return GAME<T>().apply(game)
}