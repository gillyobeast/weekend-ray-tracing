import java.io.File
import kotlin.time.measureTime

fun main() {

    val aspectRatio = 16.0 / 9.0
    val width = 400
    val height = (width / aspectRatio).toInt()
    val canvas = Canvas(width, height, aspectRatio)

    val output: String

    measureTime {
        output = RayTracer().render(canvas)
    }.print("Rendered in: ")

    measureTime {
        with(File("output.ppm")) {
            writeText(output)
        }
    }.print("Wrote file in: ")
}

private fun Any.print(prefix: String = "") {
    println(prefix + this)
}

