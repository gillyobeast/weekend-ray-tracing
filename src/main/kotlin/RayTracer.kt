import java.io.File
fun main() {
    val file = File("./output.ppm")
    val width = 200
    val height = 100
    file.writeText("P3\n")
    file.write("$width $height")
    file.write("255")

    for (row in (height - 1).downTo(0)) {
        for (column in 0..<width) {
            val colour = Colour(column.toDouble() / width, row.toDouble() / width, 0.2)
            file.write("${colour.r.scale()} ${colour.y.scale()} ${colour.z.scale()}")
        }
    }
}

private fun File.write(s: String) {
    appendText(s + "\n")
}


private fun Double.scale() = (this * 255.99).toInt()

