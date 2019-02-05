package com.github.wakingrufus.adventure

@GameDsl
class DECISION<T> {
    var prompt: String = ""
    val choices: MutableList<CHOICE<T>> = mutableListOf()

    fun choice(text: String, choice: CHOICE<T>.() -> Unit = {}) {
        choices += CHOICE<T>(text).apply(choice)
    }

    fun decide(state: T,
               input: () -> String?,
               output: (String) -> Unit): T {
        return decide(this, state, input, output)
    }
}

fun <T> decide(decision: DECISION<T>,
               state: T,
               input: () -> String?,
               output: (String) -> Unit = ::println): T {
    var responseInt: Int
    do {
        output(decision.prompt)
        decision.choices.map { it.compile() }.forEachIndexed { i, choiceText ->
            output("${i + 1}) ${choiceText.description}")
        }
        val response = input()
        responseInt = response?.toIntOrNull() ?: 0
    } while (responseInt < 1 || responseInt > decision.choices.size)
    val choice = decision.choices[responseInt - 1]
    return choose(choice.compile(), state, input, output)
}