import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sqrt

fun interface Material {
    /** Returns a scattered ray and how much attenuation happens, or null if the ray is absorbed*/
    fun scatter(ray: Ray, hitRecord: HitRecord): Pair<Ray, Colour>?

    fun reflect(direction: Vector, normal: Vector): Vector =
        direction - 2 * (direction dot normal) * normal


    fun refract(incoming: Vector, normal: Vector, refractiveRatio: Double): Vector {
        val cosTheta = min(incoming dot normal, 1.0) // should be max?
        val perp = refractiveRatio * (incoming + cosTheta * normal)
        val parallel = normal * (-sqrt(abs(1 - perp.lengthSquared)))
        return perp + parallel

    }
}
