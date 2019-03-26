package com.github.wakingrufus.adventure

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class DecisionTest {

    @Test
    fun `test selectChoice`() {
        val sb = StringBuilder()
        val decision = DECISION<String>().apply {
            prompt = "prompt"
            choice("choice 1") {
                description = "choice desc 1"
            }
            choice("choice 2") {
                description = "choice desc 2"
                stateChange { "$it choice 2 made" }
            }
        }()
        val actual = decision.selectChoice("2")
        assertEquals(expected = actual?.description, actual = "choice desc 2")
    }
}