import Colour.Companion.BLACK
import Colour.Companion.WHITE
import Vector.Companion.randomUnitVector

class RayTracer {
    fun render(canvas: Canvas, samplesPerPixel: Int = 100): String {
        val output = buildHeader(canvas)

        val world: Hittable = HittableList(
            Sphere(Point(0, 0, -1), 0.5),
            Sphere(Point(0, -100.5, -1), 100),
        )

        val camera = Camera(canvas)

        for (row in canvas.rows.reversed()) {
            println("Scanlines remaining: ${row + 1}")
            for (column in canvas.columns) {
                val colour = ((1..samplesPerPixel).fold(BLACK) { totalColour, _ ->
                    val (u, v) = canvas.randomPointIn(column, row)
                    val ray = camera[u, v]
                    totalColour + colour(ray, world)
                } / samplesPerPixel).gammaCorrect()
                output + colour
            }
            output + "\n"
        }
        println("Done!\n\n")
        return output()
    }


    private tailrec fun colour(ray: Ray, world: Hittable, depth: Int = 50, reflectivity: Double = 1.0): Colour {
        if (depth <= 0) return BLACK
        val hitRecord = world.hit(ray, 0.001, Double.POSITIVE_INFINITY)
        if (hitRecord != null) {
            val n = hitRecord.outwardNormal
            val target = hitRecord.hitPoint + n + randomUnitVector()
            return colour(
                Ray(hitRecord.hitPoint, target - hitRecord.hitPoint), world, depth - 1, 0.5 * reflectivity
            )
        }
        return background(ray) * reflectivity
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
