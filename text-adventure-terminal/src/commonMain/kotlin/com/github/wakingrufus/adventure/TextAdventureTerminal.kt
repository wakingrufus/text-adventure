package com.github.wakingrufus.adventure

fun <T> playInTerminal(game: GAME<T>, state: T): T {
    return game().play(state, { readLine() }, { println(it) })
}

expect fun readLine(): String?

fun <T> Game<T>.play(state: T, input: () -> String?, output: (String) -> Unit): T {
    var newState = state
    var currentDecision: Decision<T>? = mainDecision
    while (currentDecision != null) {
        output(currentDecision.prompt)
        val choices = currentDecision.choices
        choices.forEachIndexed { i, choiceText ->
            output("${i + 1}) ${choiceText.description}")
        }
    //    var selectedChoice: Choice<T>? = null
     //   while (selectedChoice == null) {
            val choiceInput = input()
            choiceInput?.also {
                 currentDecision?.selectChoice(it)?.apply{
                    info?.run {
                        output(this(state))
                    }
                    currentDecision = decision(state)
                    newState = stateChange(state)
                }
            }
  //      }

//        selectedChoice.info?.run {
//            output(this(state))
//        }
//        currentDecision = selectedChoice.decision(state)
//        newState = selectedChoice.stateChange(state)
    }
    return newState
}