package com.github.wakingrufus.mygame

import com.github.wakingrufus.adventure.DECISION
import com.github.wakingrufus.adventure.game

data class MyState(val hp: Int = 10,
                   val maxHp: Int = 10,
                   val checkpoint: String? = null)

val shortcutMap: Map<String, DECISION<MyState>.() -> Unit> = mapOf(
        "1" to wakeUp(),
        "2" to mainMenu()
)

fun mainMenu(): DECISION<MyState>.() -> Unit = {
    prompt = "Main Menu"
    choice("New Story") {
        decision(wakeUp())
    }
    choice("Continue Story") {
    //    goto { it.checkpoint?.let { shortcutMap[it] } ?: wakeUp() }
        gotoMap (shortcutMap){it.checkpoint}
    }
    choice("Status") {
        info { myState -> myState.hp.toString() + " / " + myState.maxHp.toString() }
    }
    choice("Quit")
}

val sampleGame = game<MyState> {
    start(mainMenu())
}

