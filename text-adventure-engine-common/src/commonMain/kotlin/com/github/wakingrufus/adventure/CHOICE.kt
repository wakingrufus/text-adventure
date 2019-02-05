package com.github.wakingrufus.adventure

@GameDsl
class CHOICE<T>(val description: String) {
    private var decision: ((T) -> DECISION<T>)? = null
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

    private var stateChange: ((T) -> T) = { it }
    fun stateChange(delta: (T) -> T) {
        stateChange = delta
    }

    fun choose(state: T, input: () -> String?, output: (String) -> Unit): T {
        return choose(this.compile(), state, input, output)
    }

    fun compile(): Choice<T> {
        return Choice(description = description, decision = decision, info = info, stateChange = stateChange)
    }
}

data class Choice<T>(val description: String,
                     val decision: ((T) -> DECISION<T>)?,
                     val stateChange: ((T) -> T),
                     val info: ((T) -> String)?)

fun <T> choose(choice: Choice<T>, state: T, input: () -> String?, output: (String) -> Unit): T {
    return choice.apply {
        choice.info?.run {
            output(this.invoke(state))
        }
    }.stateChange(state).let { s ->
        choice.decision?.invoke(s)?.let { decide(it, s, input, output) } ?: s
    }
}