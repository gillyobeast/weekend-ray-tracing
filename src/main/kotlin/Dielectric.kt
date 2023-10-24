import kotlin.math.min
import kotlin.math.sqrt
import kotlin.random.Random

class Dielectric(private val indexOfRefraction: Double) : Material {


    override fun scatter(ray: Ray, hitRecord: HitRecord): Pair<Ray, Colour> {

        val refractiveRatio = if (hitRecord.normalFacesOut) 1.0 / indexOfRefraction else indexOfRefraction
        val normal = if (hitRecord.normalFacesOut) hitRecord.outwardNormal else -hitRecord.outwardNormal
        val unitDirection = ray.direction.normalised()

        val cosTheta = min(-unitDirection dot normal, 1.0)
        val sinTheta = sqrt(1.0 - cosTheta * cosTheta)
        val cannotRefract = refractiveRatio * sinTheta > 1

        val direction = if (cannotRefract || reflectance(cosTheta, refractiveRatio) > Random.nextDouble())
            reflect(unitDirection, normal)
        else refract(
            unitDirection, normal, refractiveRatio
        )

        return Ray(hitRecord.hitPoint, direction) to Colour.WHITE
    }

    private fun reflectance(cosine: Double, refractiveRatio: Double): Double {
        // Shlick's approximation:
        val ratio = (1 - refractiveRatio) / (1 + refractiveRatio)
        val r0 = ratio * ratio
        return r0 + (1 - r0) * (1 - cosine).fifthPower()
    }

    private fun Double.fifthPower(): Double = this * this * this * this * this
}
