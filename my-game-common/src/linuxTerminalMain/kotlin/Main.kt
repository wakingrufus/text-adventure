import com.github.wakingrufus.adventure.playInTerminal
import com.github.wakingrufus.mygame.MyState
import com.github.wakingrufus.mygame.sampleGame
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    playInTerminal(game = sampleGame, state = MyState()).let {
        println(it.toString())
    }
}