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

    @Test
    fun `test stateChanges`() {

        val output = StringBuilder()
        val instance = game<String> {
            start {
                prompt = "prompt"
                choice("choice") {
                    stateChange { "$it 1" }
                    info { "info" }
                    decision {
                        choice("choice 2"){
                            stateChange { "$it 2" }
                            info { "info 2" }
                        }
                    }
                }
            }
        }
        val result = instance
                .invoke()
                .play("starting state",input = { "1" }, output = { output.append(it).append("\n") })

        println(output.toString())
        assertEquals("""prompt
            |1) choice
            |info
            |
            |1) choice 2
            |info 2
            |""".trimMargin(), output.toString())
        assertEquals(message = "final state is returned", expected = "starting state 1 2", actual = result)
    }
}