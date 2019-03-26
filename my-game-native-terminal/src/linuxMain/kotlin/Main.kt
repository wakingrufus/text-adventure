package com.github.wakingrufus.mygame

import com.github.wakingrufus.adventure.playInTerminal
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    playInTerminal(game = sampleGame, state = MyState()).let{
        println(it.toString())
    }
}