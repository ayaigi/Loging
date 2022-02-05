package com.ayaigi.loging

import kotlin.properties.Delegates


enum class PrintLogIds(id: Int) {
    Fox(0),
    Bee(1),
    Dog(2)
}

/**
 * initialise once
 */
class PrintLog {
    val Fox = Session()
    val Bee = Session()
    val Dog = Session()

    companion object {
        class InvalidLengthException(Is: Int, Should: Int) : Exception("is: $Is; should: $Should")

        private const val del = ';'
        private const val delS = del.toString()
        private const val prec = 2
        private fun Float.format(): String = "%.${prec}f".format(this)
    }

    inner class Session() {
        private var length = 0
        private var labels: List<String> = listOf()
        private val values: MutableList<List<Float>> = mutableListOf()
        fun init(labels: List<String>){
            this.labels = labels
            this.length = labels.size
            this.values.clear()
        }
        fun add(l: List<Float>) {
            if (l.size == length) {
                values.add(l)
            } else throw InvalidLengthException(l.size, length)
        }
        fun print() {
            println("----")
            println("Session:")
            println(labels.joinToString(separator = delS))
            for (v in values) {
                println(v.joinToString(separator = delS) { it.format() })
            }
            println("----")
        }
    }
}
