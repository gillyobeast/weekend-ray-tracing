import java.io.File
import kotlin.time.measureTime

fun main() {
    measureTime {

        val aspectRatio = 16.0 / 9.0
        val width = 10
        val height = (width / aspectRatio).toInt()
        val canvas = Canvas(width, height)

        val output: String

        measureTime {
            output = RayTracer().render(canvas)
        }.print("Rendered in: ")

        measureTime {
            with(File("output.ppm")) {
                writeText(output)
            }
        }.print("Wrote file in: ")
    }.print("Total: ")
}

private fun Any.print(prefix: String = "") {
    println(prefix + this)
}

