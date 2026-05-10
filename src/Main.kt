import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.Stage
import de.th_koeln.basicstage.coordinatesystem.WorldConstants
import de.th_koeln.imageprovider.Assets
import kotlin.random.Random

fun main() {

    var counter = 0
    var Monster = Actor(Assets.monster.MONSTER1, xInit = 10, yInit = 20, widthInit = 100, heightInit = 100)
    var stage = Stage()
    var Kodee = Actor(Assets.KODEE, xInit = 170, yInit = 180, widthInit = 200, heightInit = 200)

    for (i in 0..5) {
        val snackActor = Actor(Assets.snacks.DONUT1)

        snackActor.x = Random.nextInt(WorldConstants.STAGE_WIDTH - 130)
        snackActor.y = Random.nextInt(WorldConstants.STAGE_HEIGHT - 130)
        snackActor.width = 80
        snackActor.height = 80

        snackActor.reactionForMouseClick = {
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
                    Monster.text.content = "Ich bin satt"
                } else {
                    Monster.text.content = "$counter"
                }
            }
        }

        stage.addActor(snackActor)
    }

    Kodee.reactionForMouseClick = {
        Kodee.motion.speed = 5
        Kodee.motion.direction = 90
        Kodee.animation.turtleControl.turnRight(10)
        Kodee.animation.turtleControl.forward(5)
    }

    Kodee.reactionForMousePressed = {
        val myAnimation = PropertyAnimationValueChange(
            _start = 0,
            _end = 100,
            numberOfSteps = 20,
            actor = Kodee,
            propertyName = AnimatableProperties.X
        )
        Kodee.animation.queue.addPropertyAnimation(myAnimation)
    }

    stage.addActor(Monster)
    stage.addActor(Kodee)
}