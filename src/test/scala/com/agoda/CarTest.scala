package scala.com.agoda

import org.scalatest.{FlatSpec, FunSuite}


/**
  * Created by bajaj on 03/06/17.
  */
class CarTest extends FunSuite {

    test("Car topSpeed value as function of car number"){
        val car = new Car(3)
        assert(car.topSpeed == 50)
    }

    test("Car acceleration value as function of car number"){
    val car = new Car(3)
    assert(car.acceleration == 6)
    }

    test("Increase in speed after accelerating for some time"){
        val car = new Car(3)
        car.accelerate(2)
        assert(car.speed == 12)

        car.accelerate(2)
        assert(car.speed == 24)
    }


    test("using nitro should increase speed"){
        val car = new Car(3)
        car.accelerate(2)

        car.useNitroIsPossible()
        assert(car.speed == 24)
        assert(car.nitro == 0)
    }

    test("using handling should decrease speed"){
        val car = new Car(3)
        car.accelerate(2)

        car.useHandLing()
        assert(car.speed == 12 * 0.8)
    }

    test("speed of the car should be limited till top speed"){
        val car = new Car(3)
        car.accelerate(100)
        car.accelerate(100)

        assert(car.speed == car.topSpeed)
    }

    test("checking distance after driving for some time"){
        val car = new Car(3)

        car.driveForSeconds(2)
        assert(car.distanceTravelled == 6)

        car.driveForSeconds(2)
        assert(car.distanceTravelled == 36)
    }

    test("checking speed after driving for some time"){
        val car = new Car(3)

        car.driveForSeconds(2)
        assert(car.speed == 12)

        car.driveForSeconds(2)
        assert(car.speed == 24)
    }

}
