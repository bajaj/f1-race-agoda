package scala.com.agoda

import scala.com.agoda.interface.DriverI

/**
  * Created by bajaj on 02/06/17.
  */
class Driver(val car:Car, val startingPositionDistance:Double, val race: Race) extends DriverI {
   var totalDriveTime = 0
   var distanceTravelled = startingPositionDistance
   var completedTheRace = false

  override def driveForSeconds(time: Int) : Unit = {
     if(completedTheRace)
       return
     car.driveForSeconds(time)
     totalDriveTime += time
     distanceTravelled = startingPositionDistance + car.distanceTravelled
   }

  override def handleCarAfterDriving(): Unit = {
     if(completedTheRace)
       return
     if (race.anyCarAroundTenMeters(this))
       car.useHandLing()

     if(race.isIAmLast(this))
       car.useNitroIsPossible()
   }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Driver]

  override def equals(other: Any): Boolean = other match {
    case that: Driver =>
      (that canEqual this) &&
        car == that.car
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(car)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
