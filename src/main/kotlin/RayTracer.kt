class RayTracer {
    fun render(canvas: Canvas): String {
        val output = buildHeader(canvas)

        for (row in canvas.rows.reversed()) {
            println("Scanlines remaining: ${row + 1}")
            for (column in canvas.columns) {
                output + colour(row, canvas, column)
            }
            output + "\n"
        }
        println("Done!\n\n")
        return output()
    }

    private fun colour(row: Int, canvas: Canvas, column: Int): Colour {
        return Colour(
            row.d / (canvas.width - 1),
            column.d / (canvas.height - 1),
            0.25
        )
    }

    private fun buildHeader(canvas: Canvas): StringBuilder {
        val colourSpace = "P3"
        val colourDepth = 255
        return StringBuilder("$colourSpace\n$canvas\n$colourDepth\n")
    }

}
