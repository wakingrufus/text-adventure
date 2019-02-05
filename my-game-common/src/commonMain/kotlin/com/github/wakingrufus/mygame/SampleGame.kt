package com.github.wakingrufus.mygame

import com.github.wakingrufus.adventure.DECISION
import com.github.wakingrufus.adventure.game

data class MyState(val hp: Int = 10,
                   val maxHp: Int = 10,
                   val currentDecision: DECISION<MyState> = DECISION<MyState>().apply(wakeUp<MyState>()))

fun <T> wakeUp(): DECISION<MyState>.() -> Unit = {
    prompt = "You wake up"
    choice("Go downstairs") {
        info { "You go downstairs, but trip. today is not your day. you go back to bed." }
        stateChange {
            it.copy(hp = it.hp - 1)
        }
    }
    choice("go back to bed") {
        decision(wakeUp<MyState>())
        stateChange {
            it.copy(hp = minOf(it.hp + 1, it.maxHp))
        }
    }
    choice("quit")
}

fun <T> mainMenu(): DECISION<MyState>.() -> Unit = {
    prompt = "Main Menu"
    choice("Continue Story") {
        stateDecision { state ->
            state.currentDecision
        }
    }
    choice("Status") {
        info { myState -> myState.toString() }
    }
    choice("Quit")
}

val sampleGame= game<MyState> {
    decision { mainMenu<MyState>() }
}