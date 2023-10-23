import Colour.Companion.BLACK
import Colour.Companion.WHITE
import kotlin.math.sqrt


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
                val u = column.d / (canvas.width - 1)
                val v = row.d / (canvas.height - 1)

                val ray = Ray(origin, lowerLeft + u * horizontal + v * vertical - origin)
                output + colour(ray)
            }
            output + "\n"
        }
        println("Done!\n\n")
        return output()
    }

    private fun colour(ray: Ray): Colour {
        val sphereHitParam = ray.hitsSphere(Point(0, 0, -1), 0.5)
        if (sphereHitParam != null) {
            val unitNormal = (ray[sphereHitParam] - Vector(0, 0, -1)).normalised()
            return Colour(unitNormal.x + 1, unitNormal.y + 1, unitNormal.z + 1) * .5
        }
        return background(ray)
    }

    private fun Ray.hitsSphere(center: Point, radius: Double): Double? {
        val oc = origin - center
        val a = direction dot direction
        val b = 2.0 * (oc dot direction)
        val c = (oc dot oc) - radius * radius
        val discriminant = b * b - 4 * (a * c)

        return if (discriminant < 0) null else (-b - sqrt(discriminant) / 2.0 * a)
    }

    private fun background(ray: Ray): Colour {
        val unitNormal = ray.direction.normalised()
        val t = 0.5 * (unitNormal.y + 1)
        return WHITE * (1 - t) + BLACK * t
    }

    private fun buildHeader(canvas: Canvas): StringBuilder {
        val colourSpace = "P3"
        val colourDepth = 255
        return StringBuilder("$colourSpace\n$canvas\n$colourDepth\n")
    }
}
