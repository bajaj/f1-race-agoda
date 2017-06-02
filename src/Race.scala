import java.sql.Driver

/**
  * Created by bajaj on 02/06/17.
  */
class Race(val numberOfTeams:Integer, val lengthOfTrack:Integer) extends RaceI {

  val drivers = (for (i <- (1 to numberOfTeams).toList) yield new Driver(new Car(i), 0, this)).toList

  def startRace(): Unit = {
    println("Race is starting")
    setStartPosition()
    val timeInterval = 2

    while(!raceFinished()) {
      drivers.foreach(d => d.driveForSeconds(timeInterval))
      updateRaceStatus()
      drivers.foreach(d => d.handleCarAfterDriving())
    }

    println("Race is finished")
    printRaceStatus()
  }

  def updateRaceStatus() = {
    markDriversRaceCompleted()
  }
  def raceFinished() : Boolean = {
    !anyDriverRemainingToFinish
  }

  def printRaceStatus() = {
    println("DriverId CompletionTime(sec)  Speed(m/s)")
    drivers.sortWith((d1, d2) => d1.totalDriveTime < d2.totalDriveTime)
            .foreach(d => println(d.car.number,"         ",d.totalDriveTime, "                 ", d.car.speed))
  }

  def markDriversRaceCompleted() = drivers.filter(d => !d.completedTheRace).foreach(d => d.completedTheRace=driverCompletedTheRace(d))

  def anyDriverRemainingToFinish() = drivers.exists(d => !d.completedTheRace)

  def driverCompletedTheRace(driver: Driver) : Boolean = {
    driver.distanceTravelled >= lengthOfTrack
  }

  def sortByDistanceFromStart(d1:Driver, d2: Driver) = {
    d1.distanceTravelled > d2.distanceTravelled
  }


  def setStartPosition() = {
    for(a <- 1 to numberOfTeams){
      if(a==1)
        drivers(a-1).startingPositionDistance = 0
      else
        drivers(a-1).startingPositionDistance = drivers(a-2).startingPositionDistance - (a-1)*200
    }
  }

  override def isIAmLast(driver: Driver): Boolean = drivers.sortWith(sortByDistanceFromStart).last.equals(driver)


  override def anyCarAroundTenMeters(driver: Driver): Boolean = {
     def nextDriver(driver: Driver): Driver = {
       val temp = drivers.sortWith(sortByDistanceFromStart)
       temp(temp.indexOf(driver) - 1)
       temp(temp.indexOf(driver) - 1)
       temp(temp.indexOf(driver) - 1)
     }

     if(!isIAmFirst(driver))
       return (nextDriver(driver).distanceTravelled - driver.distanceTravelled) <= 10.0

     false
  }

  def isIAmFirst(driver: Driver): Boolean = drivers.sortWith(sortByDistanceFromStart)(0).equals(driver)

}
