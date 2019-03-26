package com.github.wakingrufus.adventure

import kotlin.test.Test
import kotlin.test.assertEquals

class GameTest {
    @Test
    fun test() {
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
        val result = instance.invoke()

        println(output.toString())
        assertEquals(actual = result.mainDecision.prompt, expected = "prompt")
    }
}
