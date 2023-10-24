import Colour.Companion.BLACK
import Colour.Companion.BLUE
import Colour.Companion.RED
import Colour.Companion.WHITE
import kotlin.math.PI
import kotlin.math.cos


class RayTracer {
    fun render(canvas: Canvas, samplesPerPixel: Int = 100): String {
        val output = buildHeader(canvas)

        val r = cos(PI/4)

        val left = Sphere(Point(-r,0, -1), r, Lambertian(BLUE))
        val right = Sphere(Point(r, 0, -1), r, Lambertian(RED))
        val world: Hittable = HittableList(left, right)

        val camera = Camera(90, canvas.aspectRatio)

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


    private tailrec fun colour(ray: Ray, world: Hittable, depth: Int = 10, reflection: Colour = WHITE): Colour {
        if (depth <= 0) return BLACK
        val hitRecord = world.hit(ray, 0.001, Double.POSITIVE_INFINITY) ?: return background(ray) * reflection
        val (scatteredRay, attenuation) = hitRecord.material.scatter(ray, hitRecord) ?: return BLACK

        return colour(
            scatteredRay, world, depth - 1, attenuation * reflection
        )
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
