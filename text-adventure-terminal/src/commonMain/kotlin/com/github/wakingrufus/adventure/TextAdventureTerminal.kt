package com.github.wakingrufus.adventure

fun <T> playInTerminal(game: GAME<T>, state: T): T {
    return game.mainDecision?.let { decide(it, state, { readLine() }, { println(it) }) } ?: state
}

expect fun readLine(): String?