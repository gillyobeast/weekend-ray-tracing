import java.io.File

fun main() {
    val file = File("./output.ppm")
    val width = 200
    val height = 100
    file.writeText("P3\n")
    file.write("$width $height")
    file.write("255")

    for (row in height.downTo(0)) {
        for (column in 0..width) {
            val red = column.toDouble() / width
            val green = row.toDouble() / width
            val blue = 0.2
            file.write("${red.scale()} ${green.scale()} ${blue.scale()}")
        }
    }
}

private fun File.write(s: String) {
    appendText(s + "\n")
}


private fun Double.scale() = (this * 255.99).toInt()

