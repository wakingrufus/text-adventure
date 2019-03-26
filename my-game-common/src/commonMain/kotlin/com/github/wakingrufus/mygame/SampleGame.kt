package com.github.wakingrufus.mygame

import com.github.wakingrufus.adventure.DECISION
import com.github.wakingrufus.adventure.game

data class MyState(val hp: Int = 10,
                   val maxHp: Int = 10,
                   val checkpoint: String? = null)


fun wakeUp(): DECISION<MyState>.() -> Unit = {
    prompt = "You wake up"
    choice("Go downstairs") {
        info { "You go downstairs, but trip. today is not your day. you go back to bed." }
        stateChange {
            it.copy(hp = it.hp - 1)
        }
    }
    choice("go back to bed") {
        decision(wakeUp())
        stateChange {
            it.copy(hp = minOf(it.hp + 1, it.maxHp))
        }
    }
    choice("quit")
}

val shortcutMap: Map<String, () -> DECISION<MyState>.() -> Unit> = mapOf(
        "1" to ::wakeUp
)

fun mainMenu(): DECISION<MyState>.() -> Unit = {
    prompt = "Main Menu"
    choice("New Story") {
        decision(wakeUp())
    }
    choice("Continue Story") {
        val decision: (MyState) -> DECISION<MyState>.() -> Unit = {
            (it.checkpoint?.let { checkpoint -> shortcutMap[checkpoint]?.invoke() } ?: wakeUp())
        }
        goto { DECISION<MyState>().apply(decision(it)) }
    }
    choice("Status") {
        info { myState -> myState.hp.toString() + " / " + myState.maxHp.toString() }
    }
    choice("Quit")
}

val sampleGame = game<MyState> {
    start(mainMenu())
}

