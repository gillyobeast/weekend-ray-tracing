import java.io.File
import java.time.Instant
import kotlin.random.Random
import kotlin.time.measureTime

private val random = Random(Instant.now().nano)
fun main() {
    println(measureTime {
        val file = File("./output.ppm")
        val width = 200
        val height = 100
        val samples = 100

        file.writePpmHeader(width, height)

        val camera = Camera()

        val world = HittableList(
            Sphere(Point(0, 0, -1), 0.5),
            Sphere(Point(0.0, -100.5, -1.0), 100),
        )

        for (row in (height - 1) downTo 0) {
            for (column in 0..<width) {
                val colour = (1..samples)
                    .asSequence()
                    .map {
                        val u: Double = column.perturb() / width.d
                        val v: Double = row.perturb() / height.d
                        colour(camera.getRay(u, v), world)
                    }.reduce(Colour::plus) / samples.d
                file.write(colour)
            }
        }
    })

}

private fun randomPointInUnitSphere(): Point =
    generateSequence { (2.0 * random.nextPoint()) - Point(1, 1, 1) }
        .first { it.squaredLength() < 1 }

private fun Random.nextPoint() = Point(nextDouble(), nextDouble(), nextDouble())

private val Int.d: Double
    get() = toDouble()

private fun Int.perturb() = d + random.nextDouble()

fun colour(ray: Ray, world: HittableList): Colour {
    val hit = world.hit(ray, 0.00, Double.MAX_VALUE)
    return if (hit != null) colourFor(hit.normalAtPoint) else background(ray)
}

private fun colourFor(normal: Vec3): Colour {
    return 0.5 * Colour(normal.x + 1, normal.y + 1, normal.z + 1)
}

private fun background(ray: Ray): Vec3 {
    val unitDir = ray.direction.makeUnitVector()
    val t = 0.5 * (unitDir.y + 1.0)
    return (1.0 - t) * Colour(1, 1, 1) + t * Colour(0.5, 0.7, 1.0)
}

private fun File.writePpmHeader(width: Int, height: Int) {
    writeText("P3\n")
    write("$width $height")
    write("255")
}

private fun File.write(s: String) {
    appendText(s + "\n")
}

private fun File.write(colour: Vec3) {
    write("${colour.r.scale()} ${colour.g.scale()} ${colour.b.scale()}")
}


private fun Double.scale() = (this * 255.99).toInt()

