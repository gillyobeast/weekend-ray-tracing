fun main() {
    val width = 200
    val height = 100
    println("P3")
    println("$width $height")
    println("255")

    for (row in height.downTo(0)) {
        for (column in 0..width) {
            val red = column.toDouble() / width
            val green = row.toDouble() / width
            val blue = 0.2
            println("${red.scale()} ${green.scale()} ${blue.scale()}")
        }
    }
}

private fun Double.scale() = (this * 255.99).toInt()

