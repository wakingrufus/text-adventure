package com.github.wakingrufus.mygame

import com.github.wakingrufus.adventure.playInJvmTerminal

fun main(){
    play(game = sampleGame, state = MyState()).let{
        println(it.toString())
    }
}