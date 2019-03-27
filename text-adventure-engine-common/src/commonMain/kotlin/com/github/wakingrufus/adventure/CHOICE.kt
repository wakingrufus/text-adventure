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

    fun goto(gotoDecision: (T) -> DECISION<T>.() -> Unit) {
        decision = {DECISION<T>().apply(gotoDecision(it))}
    }

    fun gotoMap(map: Map<String, DECISION<T>.() -> Unit>, selector: (T)-> String?){
        goto {selector(it)?.let { map[it] } ?: map.values.first()}
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
