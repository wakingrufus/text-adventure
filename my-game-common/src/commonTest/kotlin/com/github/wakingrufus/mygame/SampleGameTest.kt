package com.github.wakingrufus.mygame


import com.github.wakingrufus.adventure.DECISION
import kotlin.test.Test
import kotlin.test.assertEquals

class MyGameTest {
    @Test
    fun `test main menu`() {
        val outputCollector = StringBuilder()
        val startingState = MyState()
        val actualState = DECISION<MyState>()
                .apply(mainMenu<MyState>())
                .decide(startingState, { "3" }, { outputCollector.append(it + "\n") })
        assertEquals(expected = """Main Menu
            |1) Continue Story
            |2) Status
            |3) Quit
            |
        """.trimMargin(),
                actual = outputCollector.toString())
        assertEquals(expected = startingState, actual = actualState)
    }

    @Test
    fun `test main menu status`() {
        val outputCollector = StringBuilder()
        val startingState = MyState()
        val actualState = DECISION<MyState>()
                .apply(mainMenu<MyState>())
                .decide(startingState, { "2" }, { outputCollector.append(it + "\n") })
        assertEquals(expected = """Main Menu
            |1) Continue Story
            |2) Status
            |3) Quit
            |$actualState
            |
        """.trimMargin(),
                actual = outputCollector.toString())
        assertEquals(expected = startingState, actual = actualState)
    }
}