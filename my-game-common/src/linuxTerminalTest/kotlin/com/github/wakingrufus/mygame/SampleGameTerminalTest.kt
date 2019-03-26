package com.github.wakingrufus.mygame

import com.github.wakingrufus.adventure.game
import com.github.wakingrufus.adventure.play
import kotlin.test.Test
import kotlin.test.assertEquals

class SampleGameTerminalTest {

    @Test
    fun `test main menu`() {
        val outputCollector = StringBuilder()
        val startingState = MyState()
        val actualState = game<MyState> {
            start(mainMenu())
        }
                .invoke()
                .play(startingState, input = { "4" }, output = { outputCollector.append("$it\n") })

        assertEquals(
                expected = """Main Menu
            |1) New Story
            |2) Continue Story
            |3) Status
            |4) Quit
            |
        """.trimMargin(),
                actual = outputCollector.toString())
        assertEquals(expected = startingState, actual = actualState)
    }

    @Test
    fun `test main menu status`() {
        val outputCollector = StringBuilder()
        val startingState = MyState()
        val actualState = game<MyState> {
            start(mainMenu())
        }
                .invoke()
                .play(startingState, { "3" }, { outputCollector.append("$it\n") })
        assertEquals(expected = """Main Menu
            |1) New Story
            |2) Continue Story
            |3) Status
            |4) Quit
            |10 / 10
            |
        """.trimMargin(),
                actual = outputCollector.toString())
        assertEquals(expected = startingState, actual = actualState)

    }
}