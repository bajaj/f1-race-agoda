package scala.com.agoda

import org.scalatest.FunSuite

/**
  * Created by bajaj on 03/06/17.
  */
class RaceTest extends FunSuite {

  test("race should finish") {
    val race = new Race(3, 100)
    assert(!race.raceFinished())

    race.startRace()
    assert(race.raceFinished())
  }

  test("test-1 completion time should be properly calculated") {
    val race = new Race(3, 100)
    race.startRace()

    assert(race.drivers.head.totalDriveTime == 12)
    assert(race.drivers(1).totalDriveTime == 14)
    assert(race.drivers.last.totalDriveTime == 18)
  }

  test("test-2 completion time should be properly calculated") {
    val race = new Race(3, 400)
    race.startRace()

    assert(race.drivers.head.totalDriveTime == 22)
    assert(race.drivers(1).totalDriveTime == 20)
    assert(race.drivers.last.totalDriveTime == 24)
  }

  test("test-1 speed at the end of the race should be properly calculated") {
    val race = new Race(3, 100)
    race.startRace()

    assert(race.drivers.head.car.speed == 24.0)
    assert(race.drivers(1).car.speed  == 47.22222222222222)
    assert(race.drivers.last.car.speed  == 50.0)
  }

  test("test-2 speed at the end of the race should be properly calculated") {
    val race = new Race(3, 400)
    race.startRace()

    assert(race.drivers.head.car.speed == 44.0)
    assert(race.drivers(1).car.speed  == 47.22222222222222)
    assert(race.drivers.last.car.speed  == 50.0)
  }

  test("whether the driver is the first driver in the race") {
    val race = new Race(3, 400)
    val firstDriver = race.drivers.head
    assert(race.isIAmFirst(firstDriver))
    assert(!race.isIAmFirst(race.drivers.last))
  }

  test("whether the driver is the last driver in the race") {
      val race = new Race(3, 100)
      val lastDriver = race.drivers.last
      val firstDriver = race.drivers.head
      assert(race.isIAmLast(lastDriver))
      assert(!race.isIAmLast(firstDriver))
  }

  test("testDriverCompletedTheRace") {
    val race = new Race(3, 100)
    val car = new Car(3)
    val driver = new Driver(car, 0, race)
    assert(!race.driverCompletedTheRace(driver))
    driver.distanceTravelled = 105
    assert(race.driverCompletedTheRace(driver))
  }

  test("testAnyDriverRemainingToFinish") {
    val race = new Race(3, 100)
    assert(race.anyDriverRemainingToFinish())

    race.startRace()
    assert(!race.anyDriverRemainingToFinish())
  }

  test("testAnyCarAroundTenMeters") {
    val race = new Race(3, 100)
    assert(!race.anyCarAroundTenMeters(race.drivers.head))
    assert(!race.anyCarAroundTenMeters(race.drivers(1)))
    assert(!race.anyCarAroundTenMeters(race.drivers.last))
  }

  test("test whether drivers are properly assigned start position") {
    val race = new Race(3, 100)
    val startPosition = race.startPositions()
    assert(startPosition.head == 0)
    assert(startPosition(1) == -200)
    assert(startPosition.last == -600)
  }

}
