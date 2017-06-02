/**
  * Created by bajaj on 02/06/17.
  */
class Driver(val car:Car, var startingPositionDistance:Double, val race: Race) {
   var totalDriveTime = 0
   var distanceTravelled = startingPositionDistance

   def driveForSeconds(time: Int): Unit = {
     car.driveForSeconds(time)
     totalDriveTime += time
     distanceTravelled = startingPositionDistance + car.distanceTravelled
   }

   def handleCarAfterDriving(): Unit = {
     if (race.anyCarAroundTenMeters(this))
       car.useHandLing()

     if(race.isAmLast(this))
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
