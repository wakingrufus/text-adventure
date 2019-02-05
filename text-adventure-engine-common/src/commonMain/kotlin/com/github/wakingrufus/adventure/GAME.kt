package com.github.wakingrufus.adventure

@GameDsl
class GAME<T> {
    var mainDecision: DECISION<T>? = null
    fun decision(decision: DECISION<T>.() -> Unit) {
        mainDecision = DECISION<T>().apply(decision)
    }

    fun play(state: T, input: () -> String?,
             output: (String) -> Unit = ::println): T {
        return play(this, state, input, output)
    }
}

fun <T> play(game: GAME<T>,
             state: T,
             input: () -> String?,
             output: (String) -> Unit): T {
    return game.mainDecision?.let { decide(it, state, input, output) } ?: state
}