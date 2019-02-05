package com.github.wakingrufus.mygame


fun main(){
    playInTerminal(game = sampleGame, state = MyState()).let{
        println(it.toString())
    }
}