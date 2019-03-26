package com.github.wakingrufus.adventure

@GameDsl
class DECISION<T> {
    var prompt: String = ""
    val choices: MutableList<CHOICE<T>.() -> Unit> = mutableListOf()

    fun choice(name: String, choice: CHOICE<T>.() -> Unit = {}) {
        choices += {
            this.description = name
            this.apply(choice)
        }
    }

    operator fun invoke(): Decision<T> {
        return Decision(
                prompt = prompt,
                choices = choices.map { CHOICE<T>().apply(it)() })
    }
}

class Decision<T>(val prompt: String, val choices: List<Choice<T>>) {

    fun selectChoice(choice: String): Choice<T>? {
        val responseInt = choice.toIntOrNull() ?: 0
        return if (responseInt > 0 && responseInt <= choices.size) {
            choices[responseInt - 1]
        } else {
            null
        }
    }
}
