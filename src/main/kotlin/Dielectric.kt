class Dielectric(private val indexOfRefraction: Double) : Material {


    override fun scatter(ray: Ray, hitRecord: HitRecord): Pair<Ray, Colour> {

        val refractiveRatio = if (hitRecord.normalFacesOut) 1.0 / indexOfRefraction else indexOfRefraction
        val normal = if (hitRecord.normalFacesOut) hitRecord.outwardNormal else -hitRecord.outwardNormal

        val refractionDirection = refract(ray.direction.normalised(), normal, refractiveRatio)

        return Ray(hitRecord.hitPoint, refractionDirection) to Colour.WHITE
    }

}
