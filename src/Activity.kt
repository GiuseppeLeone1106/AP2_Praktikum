open class Activity(
    var energyImpact: Int = 0,
    var happinessImpact: Int = 0,
    var description: String = ""
) {
    open fun execute(pet: Pet) {
        pet.health.energy += energyImpact
        pet.happiness += happinessImpact
    }
}