
import java.io.File

fun main() {
    val canvas = 256 by 256

    val output = RayTracer().render(canvas)

    with(File("output.ppm")) {
        writeText(output)
    }
}

