package com.ayaigi.loging


/**
 * initialise once
 */
class PrintLog {
    val Fox = Session()
    val Bee = Session()
    val Dog = Session()
    val Cat = Session()
    val Cow = Session()

    companion object {
        class InvalidLengthException(Is: Int, Should: Int) : Exception("is: $Is; should: $Should")

        private const val del = ";"
        private const val prec = 2
        private fun Float.format(precision: Int): String = "%.${precision}f".format(this)
    }

    inner class Session {
        private var length = 0
        private var labels: List<String> = listOf()
        private val values: MutableList<List<Float>> = mutableListOf()
        fun init(labels: List<String>){
            this.labels = labels
            this.length = labels.size
            this.values.clear()
        }
        fun init(vararg labels: String){
            init(labels.asList())
        }
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
        fun print(label: String = "", precision: Int = prec, separator: String = del) {
            println("----")
            println("Session: $label")
            println(labels.joinToString(separator = separator))
            for (v in values) {
                println(v.joinToString(separator = separator) { it.format(precision) })
            }
            println("----")
        }
    }
}
