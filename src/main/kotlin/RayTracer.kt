class RayTracer {
    fun render(canvas: Canvas): String {
        val output = buildHeader(canvas)

        for (row in canvas.rows.reversed()) {
            println("Scanlines remaining: ${row + 1}")
            for (column in canvas.columns) {
                output + Colour(
                    row.d / (canvas.width - 1),
                    column.d / (canvas.height - 1),
                    0.25
                )
            }
            output + "\n"
        }
        println("Done!\n\n")
        return output()
    }

    private fun Double.scale() = (255.999 * this).toInt()

    private fun buildHeader(canvas: Canvas): StringBuilder {
        val colourSpace = "P3"
        val colourDepth = 255
        return StringBuilder("$colourSpace\n$canvas\n$colourDepth\n")
    }

}
