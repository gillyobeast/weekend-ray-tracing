class RayTracer {
    fun render(canvas: Canvas): String {
        val output = buildHeader(canvas)

        for (row in canvas.rows.reversed()) {
            println("Scanlines remaining: ${row+1}")
            for (column in canvas.columns) {
                val r = row.d / (canvas.width - 1)
                val g = column.d / (canvas.height - 1)
                val b = 0.25

                output + "${r.scale()} ${g.scale()} ${b.scale()}    "
            }
            output + "\n"
        }
        println("Done!\n\n")
        return output.toString()
    }

    private fun Double.scale() = (255.999 * this).toInt()

    private fun buildHeader(canvas: Canvas): StringBuilder {
        val colourSpace = "P3"
        val colourDepth = 255
        return StringBuilder("$colourSpace\n$canvas\n$colourDepth\n")
    }

}
