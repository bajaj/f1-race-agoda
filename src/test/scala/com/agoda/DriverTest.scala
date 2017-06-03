package scala.com.agoda

import org.scalatest.FunSuite

/**
  * Created by bajaj on 03/06/17.
  */
class DriverTest extends FunSuite {

  test("TotalDriveTime is calculated properly") {
    val race = new Race(3, 100)
    val car = new Car(3)
    val driver = new Driver(car, 0, race)
    assert(driver.totalDriveTime == 0)

    driver.driveForSeconds(2)
    assert(driver.totalDriveTime == 2)

    driver.driveForSeconds(2)
    assert(driver.totalDriveTime == 4)
  }

  test("StartingPositionDistance is initialized properly") {
    val race = new Race(3, 100)

    assert(race.drivers.head.startingPositionDistance == 0)
    assert(race.drivers(1).startingPositionDistance == -200)
    assert(race.drivers.last.startingPositionDistance == -600)
  }

  test("DistanceTravelled while in the race is calculated properly") {
    val race = new Race(3, 100)
    val car1 = new Car(3)

    val driver1 = new Driver(car1, 0, race)
    driver1.driveForSeconds(2)

    assert(driver1.distanceTravelled ==  car1.distanceTravelled)

    val car2 = new Car(3)
    val driver2 = new Driver(car2, -10, race)
    driver2.driveForSeconds(2)
    assert(driver2.distanceTravelled ==  (-10 + car2.distanceTravelled))
  }

  test("testDriveForSeconds") {
    val race = new Race(3, 100)
    val lastDriver = race.drivers.last
    lastDriver.driveForSeconds(2)

    assert(lastDriver.totalDriveTime == 2)
    assert(lastDriver.distanceTravelled == (lastDriver.startingPositionDistance + lastDriver.car.distanceTravelled))
    lastDriver.driveForSeconds(2)
    assert(lastDriver.totalDriveTime == 4)
  }

  test("last driver in the race should use nitro") {
    val race = new Race(3, 100)
    val lastDriver = race.drivers.last

    assert(lastDriver.car.nitro == 1)
    lastDriver.handleCarAfterDriving()
    assert(lastDriver.car.nitro == 0)
  }

  test("driver should handle the car within 10 meters") {
    val race = new Race(3, 100)
    race.drivers(1).distanceTravelled = -595
    val lastDriver = race.drivers.last

    lastDriver.car.speed = 30
    lastDriver.car.nitro = 0

    assert(lastDriver.car.speed == 30)
    lastDriver.handleCarAfterDriving()
    assert(lastDriver.car.speed == 0.8 * 30)
  }

  test("CompletedTheRace flag should be set properly") {
    val race = new Race(3, 100)
    val car = new Car(3)
    val driver = new Driver(car, 0, race)
    assert(!driver.completedTheRace)
  }

  test("two driver are equal if they have same car") {
    val race = new Race(3, 100)
    val car = new Car(3)
    val driver = new Driver(car, 0, race)
    val driver1 = new Driver(car, 1, race)
    assert(driver.equals(driver1))
  }

}
