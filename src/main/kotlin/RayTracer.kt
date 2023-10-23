import Colour.Companion.WHITE


class RayTracer {
    fun render(canvas: Canvas): String {
        val output = buildHeader(canvas)

        //  World   //
        val world: Hittable = HittableList(
            Sphere(Point(0, 0, -1), 0.5),
            Sphere(Point(0, -100.5, -1), 100),
        )

        //  Camera  //
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
                val u = column.d / (canvas.width - 1)
                val v = row.d / (canvas.height - 1)

                val ray = Ray(origin, lowerLeft + u * horizontal + v * vertical - origin)
                output + colour(ray, world)
            }
            output + "\n"
        }
        println("Done!\n\n")
        return output()
    }

    private fun colour(ray: Ray, world: Hittable): Colour {
        val hitRecord = world.hit(ray, 0.0, Double.POSITIVE_INFINITY)
        if (hitRecord != null) {
            val n = hitRecord.outwardNormal
            return Colour(1 + n.x, 1 + n.y, 1 + n.z) * 0.5
        }
        return background(ray)
    }


    private fun background(ray: Ray): Colour {
        val unitNormal = ray.direction.normalised()
        val t = 0.5 * (unitNormal.y + 1)
        return WHITE * (1 - t) + Colour(.7, .5, 1) * t
    }

    private fun buildHeader(canvas: Canvas): StringBuilder {
        val colourSpace = "P3"
        val colourDepth = 255
        return StringBuilder("$colourSpace\n$canvas\n$colourDepth\n")
    }
}
