import kotlin.math.min
import kotlin.math.sqrt

class Dielectric(private val indexOfRefraction: Double) : Material {


    override fun scatter(ray: Ray, hitRecord: HitRecord): Pair<Ray, Colour> {

        val refractiveRatio = if (hitRecord.normalFacesOut) 1.0 / indexOfRefraction else indexOfRefraction
        val normal = if (hitRecord.normalFacesOut) hitRecord.outwardNormal else -hitRecord.outwardNormal
        val unitDirection = ray.direction.normalised()

        val cosTheta = min(-unitDirection dot normal, 1.0)
        val sinTheta = sqrt(1.0 - cosTheta * cosTheta)
        val cannotRefract = refractiveRatio * sinTheta > 1

        val direction = if (cannotRefract)
            reflect(unitDirection, normal)
        else refract(
            unitDirection, normal, refractiveRatio
        )

        return Ray(hitRecord.hitPoint, direction) to Colour.WHITE
    }

}
