package com.github.wakingrufus.mygame

import com.github.wakingrufus.adventure.DECISION


fun wakeUp(): DECISION<MyState>.() -> Unit = {
    prompt = "You wake up"
    choice("Go downstairs") {
        info { "You go downstairs, but trip. today is not your day. you go back to bed." }
        stateChange {
            it.copy(hp = it.hp - 1)
        }
        decision(wakeUp())
    }
    choice("go back to bed") {
        decision(wakeUp())
        stateChange {
            it.copy(hp = minOf(it.hp + 1, it.maxHp))
        }
    }
    choice("quit")
}