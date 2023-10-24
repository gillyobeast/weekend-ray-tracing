import Colour.Companion.BLACK
import Colour.Companion.WHITE

class RayTracer {
    fun render(canvas: Canvas, samplesPerPixel: Int = 100): String {
        val output = buildHeader(canvas)


        val ground = Sphere(Point(0, -100.5, -1), 100, Lambertian(Colour(.8, .8, 0)))
        val center = Sphere(Point(0, 0, -1), 0.5, Lambertian(Colour(.1, .2, .5)))
        val left = Sphere(Point(-1, 0, -1), 0.5, Dielectric(1.5))
        val right = Sphere(Point(1, 0, -1), 0.5, Metal(Colour(.8, .6, .2), .9))
        val world: Hittable = HittableList(ground, center, left, right)

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


    private tailrec fun colour(ray: Ray, world: Hittable, depth: Int = 50, reflection: Colour = WHITE): Colour {
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
