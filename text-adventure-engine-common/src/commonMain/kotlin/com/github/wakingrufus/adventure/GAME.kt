package com.github.wakingrufus.adventure

@GameDsl
class GAME<T> {
    private var mainDecision: (DECISION<T>.() -> Unit) = {}

    fun start(startingDecision: DECISION<T>.() -> Unit) {
        mainDecision = startingDecision
    }

    operator fun invoke(): Game<T> {
        return Game(DECISION<T>().apply(mainDecision)())
    }
}

class Game<T>(val mainDecision: Decision<T>)