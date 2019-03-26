package com.github.wakingrufus.adventure

import kotlin.test.Test
import kotlin.test.assertEquals

class TextAdventureTerminalTest {
    @Test
    fun `test play`() {

        val output = StringBuilder()
        val instance = game<String> {
            start {
                prompt = "prompt"
                choice("choice") {
                    stateChange { "result" }
                    info { "info" }
                }
            }
        }
        val result = instance
                .invoke()
                .play("starting state",input = { "1" }, output = { output.append(it).append("\n") })

        println(output.toString())
        assertEquals("prompt\n1) choice\ninfo\n", output.toString())
        assertEquals(message = "final state is returned", expected = "result", actual = result)
    }
}