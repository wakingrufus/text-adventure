package com.github.wakingrufus.mygame

import com.github.wakingrufus.adventure.game
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SampleGameTest {

    @Test
    fun `test main menu`() {
        val actualState = game<MyState> {
            start(mainMenu())
        }()

        assertEquals(expected = "Main Menu", actual = actualState.mainDecision.prompt)
    }

    @Test
    fun `test main menu status`() {
        val actualState = game<MyState> {
            start(mainMenu())
        }.invoke()
        val inputState = MyState()

        val statusChoice = actualState.mainDecision.selectChoice("3")
        assertNotNull(statusChoice)
        assertEquals(expected = "10 / 10", actual = statusChoice.info?.invoke(inputState))

    }

    @Test
    fun `test wakeUp`() {
        val actualState = game<MyState> {
            start(wakeUp())
        }()

        assertEquals(expected = "You wake up", actual = actualState.mainDecision.prompt)
    }

    @Test
    fun `test continue`() {
        val actualState = game<MyState> {
            start(mainMenu())
        }.invoke()
        val inputState = MyState(checkpoint = "2")

        val statusChoice = actualState.mainDecision.selectChoice("2")
        assertNotNull(statusChoice)
        val gotoTarget = statusChoice.decision(inputState)
        assertEquals(expected = "Main Menu", actual = gotoTarget?.prompt)
    }
}