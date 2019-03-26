package com.github.wakingrufus.mygame

import javafx.beans.property.StringProperty
import javafx.collections.ObservableList
import javafx.scene.text.Text
import javafx.stage.Stage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import mu.KLogging
import tornadofx.*
import kotlin.coroutines.EmptyCoroutineContext

class MyGameJfx : App(MainView::class) {
    companion object : KLogging()

    override fun start(stage: Stage) {
        super.start(stage)
        val rootEm = Math.rint(Text().layoutBounds.height)

        stage.width = rootEm * 80
        stage.height = rootEm * 60
        //   stage.icons.add(Image(this.javaClass.getResourceAsStream("/images/avatar-default.png")))
    }
}

suspend fun gameThread(prop: StringProperty, output: ObservableList<String>) {
//    sampleGame(output = { output.add(it) }, input = {
//        withContext(CoroutineScope(EmptyCoroutineContext).coroutineContext) {
//            getInput(prop)
//        }
//    }).play(state = MyState())
}

suspend fun getInput(prop: StringProperty): String {
    while (prop.isEmpty.value) {
        delay(1000)
    }
    val choice = prop.value
    prop.value = null
    return choice
}