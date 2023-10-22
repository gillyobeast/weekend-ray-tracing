import java.io.File
import kotlin.time.measureTime

fun main() {
    val canvas = 256 by 256

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

