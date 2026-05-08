import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.Stage//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import de.th_koeln.imageprovider.Assets

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("Hello World!")
    var Monster  = Actor(Assets.monster.MONSTER1, xInit = 10 , yInit  = 20 , widthInit = 200, heightInit = 200);
    var stage = Stage();
    var Kodee = Actor(Assets.KODEE, xInit = 170 , yInit  = 180 , widthInit = 200, heightInit = 200)

    for (i in 0..4) {

        val snackActor = Actor(Assets.snacks.DONUT1)

        // Positionen verändern
        snackActor.x = 100 + i * 120
        snackActor.y = 200

        // Größe setzen
        snackActor.width = 80
        snackActor.height = 80

        // Zur Stage hinzufügen
        stage.addActor(snackActor)}

    Monster.reactionForMouseClick={
        Monster.motion.speed =5
        Monster.motion.direction=90
        Monster.animation.turtleControl.turnRight(10)
        Monster.animation.turtleControl.forward(5)
    }


    Kodee.reactionForMouseClick={
        Kodee.motion.speed =5
        Kodee.motion.direction=90
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