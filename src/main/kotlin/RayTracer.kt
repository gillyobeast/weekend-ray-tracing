class RayTracer {
    fun render(canvas: Canvas): String {
        val output = buildHeader(canvas)

        // Camera //
        val viewportHeight = 2.0
        val viewportWidth = viewportHeight * canvas.aspectRatio
        val focalLength = 1.0

        val origin = Point()
        val horizontal = Vector(viewportWidth, 0, 0)
        val vertical = Vector(0, viewportHeight, 0)
        val lowerLeft: Point = origin - .5 * horizontal - .5 * vertical - Vector(0, 0, focalLength)

        for (row in canvas.rows.reversed()) {
            println("Scanlines remaining: ${row + 1}")
            for (column in canvas.columns) {
                val u = column.d - (canvas.width - 1)
                val v = row.d - (canvas.height - 1)

                output + colour(Ray(origin, lowerLeft + u * horizontal + v * vertical - origin))
            }
            output + "\n"
        }
        println("Done!\n\n")
        return output()
    }

    private fun colour(ray: Ray): Colour {
        val unit = ray.direction.normalised()
        val t = .5 * (unit.y + 1)
        return Colour(1, 1, 1) * (1 - t) + Colour(0.5, 0.7, 1) * t
    }

    private fun buildHeader(canvas: Canvas): StringBuilder {
        val colourSpace = "P3"
        val colourDepth = 255
        return StringBuilder("$colourSpace\n$canvas\n$colourDepth\n")
    }

}
