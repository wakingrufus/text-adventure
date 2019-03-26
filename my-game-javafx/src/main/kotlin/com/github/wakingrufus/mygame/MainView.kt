package com.github.wakingrufus.mygame

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.control.TextArea
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mu.KLogging
import tornadofx.*

class MainView : View() {
    companion object : KLogging()

    var outputBox: TextArea by singleAssign()
    val output = FXCollections.observableArrayList<String>()
    val input = SimpleStringProperty()

    init {
        GlobalScope.launch {
            gameThread(input, output)
        }

        output.onChange {
            outputBox.paragraphs.setAll(output)
        }
    }


    override val root = borderpane {
        minHeight = 100.percent.value

        center {
            vbox {
                outputBox = textarea()
                textfield(input)
            }
        }
    }
}