class Sleeping: Activity(
    energyImpact=20,
    happinessImpact=10,
    description = "müde ahhhh"
)
override fun execute(pet: Pet) {
    super.execute(pet)
    println("Sleeping")
}
