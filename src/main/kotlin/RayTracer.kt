import java.io.File

fun main() {
    val file = File("./output.ppm")
    val width = 200
    val height = 100
    file.writePpmHeader(width, height)
    val lowerLeft = Point(-2, -1, -1)
    val horizontal = Vec3(4, 0, 0)
    val vertical = Vec3(0, 2, 0)
    val origin = Point(0, 0, 0)
    for (row in (height - 1) downTo 0) {
        for (column in 0..<width) {
            val u: Double = column.toDouble() / width.toDouble()
            val v: Double = row.toDouble() / height.toDouble()
            val ray = Ray(origin, lowerLeft + u * horizontal + v * vertical)
            val colour = colour(ray)
            file.write("${colour.r.scale()} ${colour.g.scale()} ${colour.b.scale()}")
        }
    }
}

fun Ray.hitsSphere(center: Point, radius: Double): Boolean {
    val oc = origin - center
    val a = direction dot direction
    val b = 2.0 * (oc dot direction)
    val c = (oc dot oc) - (radius * radius)
    val discriminant = (b * b) - (4 * a * c)
    return discriminant > 0
}

fun colour(ray: Ray): Colour {
    if (ray.hitsSphere(Point(0, 0, -1), 0.5)) {
        return Colour(1, 0, 0)
    }
    return background(ray)
}

private fun background(ray: Ray): Vec3 {
    val unitDir = ray.direction.makeUnitVector()
    val t = 0.5 * (unitDir.y + 1.0)
    return (1.0 - t) * Colour(1, 1, 1) + t * Colour(0.7, 0.2, 1.0)
}

private fun File.writePpmHeader(width: Int, height: Int) {
    writeText("P3\n")
    write("$width $height")
    write("255")
}

private fun File.write(s: String) {
    appendText(s + "\n")
}


private fun Double.scale() = (this * 255.99).toInt()

