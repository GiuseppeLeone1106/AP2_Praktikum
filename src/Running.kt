class Running : Activity(
    energyImpact = -20,
    happinessImpact = 10,
    description = "Laufen"
) {
    override fun execute(pet: Pet) {
        super.execute(pet)
        print("Puh!")
    }
}