fun <T> playInNativeTerminal(game: GAME<T>, state: T): T {
    return game.mainDecision?.let { decide(it, state,  { readLine() }, { println(it) }) } ?: state
}