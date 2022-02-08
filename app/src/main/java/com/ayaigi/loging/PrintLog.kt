package com.ayaigi.loging

import java.io.File


class PrintLog(vararg labels: String) {
    companion object {
        class InvalidLengthException(Is: Int, Should: Int) : Exception("is: $Is; should: $Should")

        private const val del = ";"
        private const val prec = 2
        private fun Float.format(precision: Int): String = "%.${precision}f".format(this)
    }

    private val labels: List<String> = labels.toList()
    private var length = labels.size
    private val values: MutableList<List<Float>> = mutableListOf()

    fun add(items: List<Float>) {
        if (items.size == length) {
            values.add(items)
        } else throw InvalidLengthException(items.size, length)
    }

    fun add(vararg items: Float) {
        if (items.size == length) {
            values.add(items.asList())
        } else throw InvalidLengthException(items.size, length)
    }

    fun printLog(precision: Int = prec, separator: String = del) {
        println("----")
        println("Session")
        println(labels.joinToString(separator = separator))
        for (v in values) {
            println(v.joinToString(separator = separator) { it.format(precision) })
        }
        println("----")
    }

    /**
     * Dir: "C:\\Users\\aikee\\Desktop\\StudioBin\\$filename")
     */
    fun printToFile(filename: String, precision: Int = prec, separator: String = del) {
        val ln = "\n"
        var content = labels.joinToString(separator = separator)
        for (v in values) {
            content += (ln + v.joinToString(separator = separator) { it.format(precision) })
        }
        File("C:\\Users\\aikee\\Desktop\\StudioBin\\$filename").writeText(content)
    }
}
