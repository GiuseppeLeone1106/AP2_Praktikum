import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.Stage
import de.th_koeln.basicstage.coordinatesystem.WorldConstants
import de.th_koeln.imageprovider.Assets
import kotlin.random.Random
import kotlin.test.asserter

fun main() {

    var counter = 0
    val snackBar = Actor(Assets.EMPTY, xInit = 100, yInit = 0, widthInit = 100, heightInit = 60)
    val stage = Stage()
    val kodee = Actor(Assets.KODEE, xInit = 170, yInit = 180, widthInit = 200, heightInit = 200)
    val energyBar = Actor(Assets.EMPTY, widthInit = 100, heightInit = 60, xInit = 0, yInit = 0)
    val happinessBar = Actor(Assets.EMPTY ,widthInit = 100, heightInit = 60 , xInit = 200, yInit = 0)
    val football = Actor(Assets.misc.BALL, widthInit = 50, heightInit = 50, xInit = 100, yInit = 100)
    val pet = Pet()
    petHasFun(pet)
    happinessBar.text.content="${pet.happiness}"
    energyBar.text.content = "${pet.health.energy}"
    energyBar.animation.everyNsteps.timeSpan = 10
    energyBar.animation.everyNsteps.reactionForTimePassed = { lifeGoesOn(pet, energyBar) }


    val bakeCookies  = BakeCookies()
    val running      = Running()
    val playFootball = PlayFootball()

    val bakeCookiesButton = Actor(Assets.EMPTY, xInit = 300,   yInit = 0,   widthInit = 100, heightInit = 50)
    val runningButton     = Actor(Assets.EMPTY, xInit = 400, yInit = 0,   widthInit = 100, heightInit = 50)
    val footballButton    = Actor(Assets.EMPTY, xInit = 500, yInit = 0,   widthInit = 100, heightInit = 50)

    bakeCookiesButton.text.content = "Kekse backen"
    runningButton.text.content     = "Laufen"
    footballButton.text.content    = "Fußball spielen"



    updateUI(pet,energyBar,happinessBar)

    bakeCookiesButton.reactionForMouseClick = {
        pet.doActivity(bakeCookies)
        updateUI(pet,energyBar,happinessBar)
    }

    runningButton.reactionForMouseClick = {
        pet.doActivity(running)
        updateUI(pet,energyBar,happinessBar)
    }

    football.reactionForMouseClick = {
        pet.doActivity(playFootball)
        updateUI(pet,energyBar,happinessBar)
    }


    for (i in 0..5) {
        val snackActor = Actor(Assets.snacks.DONUT1)

        snackActor.x = Random.nextInt(WorldConstants.STAGE_WIDTH - 130)
        snackActor.y = Random.nextInt(WorldConstants.STAGE_HEIGHT - 130)
        snackActor.width = 80
        snackActor.height = 80

        snackActor.reactionForMouseClick = {
            val snack = Item(
                name = "Donut",
                category = ItemCategory.FOOD,
                amount = 1,
                energyImpact = 10,
                happinessImpact = 5
            )
            pet.addItem(snack)
            pet.happiness += snack.happinessImpact
            happinessBar.text.content = "${pet.happiness}"
            if (snackActor.opacity > 0) {
                val fadeOut = PropertyAnimationValueChange(
                    _start = 100,
                    _end = 0,
                    numberOfSteps = 30,
                    actor = snackActor,
                    propertyName = AnimatableProperties.OPACITY
                )
                snackActor.animation.queue.addPropertyAnimation(fadeOut)
                counter++
                if (counter >= 5) {
                    snackBar.text.content = "Ich bin satt"
                } else {
                    snackBar.text.content = "$counter"
                }
            }
        }

        stage.addActor(snackActor)
    }

    kodee.reactionForMouseClick = {
        kodee.motion.speed = 5
        kodee.motion.direction = 90
        kodee.animation.turtleControl.turnRight(10)
        kodee.animation.turtleControl.forward(5)
    }

    kodee.reactionForMousePressed = {
        val myAnimation = PropertyAnimationValueChange(
            _start = 0,
            _end = 100,
            numberOfSteps = 20,
            actor = kodee,
            propertyName = AnimatableProperties.X
        )
        kodee.animation.queue.addPropertyAnimation(myAnimation)
    }
    pet.doActivity(BakeCookies())
    pet.doActivity(Running())
    pet.doActivity(PlayFootball())
    stage.addActor(snackBar)
    stage.addActor(kodee)
    stage.addActor(energyBar)
    stage.addActor(happinessBar)
    stage.addActor(bakeCookiesButton)
    stage.addActor(runningButton)
    stage.addActor(footballButton)
    stage.addActor(football)
}

fun lifeGoesOn(pet: Pet, energyBar: Actor) {
    pet.health.energy -= Random.nextInt(0, 20)
    energyBar.text.content = if (pet.health.energy <= 0) "Ich bin müde" else "${pet.health.energy}"
}

fun petHasFun(myPet:Pet) {

    val ball = Item(
        name = "Ball",
        category = ItemCategory.TOY,
        amount = 1,
        energyImpact = 0,
        happinessImpact = 20
    )

    val book = Item(
        name = "Book",
        category = ItemCategory.TOY,
        amount = 1,
        energyImpact = 0,
        happinessImpact = 15
    )

    myPet.addItem(ball)
    myPet.addItem(book)
    myPet.removeItem(ball)
}
fun setupClickableItem(actor: Actor, item: Item, pet: Pet, energyBar: Actor, happinessBar: Actor) {
    actor.reactionForMouseClick = {
        if (actor.opacity > 0) {
            val fadeOut = PropertyAnimationValueChange(
                _start = 100,
                _end = 0,
                numberOfSteps = 30,
                actor = actor,
                propertyName = AnimatableProperties.OPACITY
            )
            actor.animation.queue.addPropertyAnimation(fadeOut)

            pet.addItem(item)

            // update UI
            energyBar.text.content = "Energy: ${pet.health.energy}"
            happinessBar.text.content = "Happiness: ${pet.happiness}"
        }
    }
}
fun updateUI(pet: Pet, energyBar: Actor, happinessBar: Actor) {
    energyBar.text.content    = "Energy: ${pet.health.energy}"
    happinessBar.text.content = "Happiness: ${pet.happiness}"
}