package com.github.wakingrufus.adventure

@GameDsl
class CHOICE<T> {
    var description: String = ""
    private var decision: ((T) -> DECISION<T>?) = { null }
    private var info: ((T) -> String)? = null

    fun decision(block: DECISION<T>.() -> Unit) {
        decision = { DECISION<T>().apply(block) }
    }

    fun stateDecision(stateDecision: (T) -> DECISION<T>) {
        decision = stateDecision
    }

    fun info(text: (T) -> String) {
        info = text
    }

    fun goto(gotoDecision: (T) -> DECISION<T>?) {
        decision = gotoDecision
    }

    private var stateChange: ((T) -> T) = { it }
    fun stateChange(delta: (T) -> T) {
        stateChange = delta
    }

    operator fun invoke(): Choice<T> {
        return Choice(
                description = description,
                decision = { state -> decision.invoke(state)?.invoke() },
                info = info,
                stateChange = stateChange)
    }
}

class Choice<T>(
        val description: String,
        val decision: (T) -> Decision<T>?,
        val info: ((T) -> String)? = null,
        val stateChange: ((T) -> T))
