import java.sql.Driver

/**
  * Created by bajaj on 02/06/17.
  */
class Race(val numberOfTeams:Integer, val lengthOfTrack:Integer) extends RaceI {

  val drivers = (for (i <- (1 to numberOfTeams).toList) yield new Driver(new Car(i), 0, this)).toList

  def startRace(): Unit = {
    setStartPosition()
    val timeInterval = 2

    while(!raceFinished()) {
      drivers.foreach(d => if(!driverCompletedTheRace(d)) {
        d.driveForSeconds(timeInterval)
      })
      updateRaceStatus()
      drivers.foreach(d => d.handleCarAfterDriving())
    }

    println("Race is finished")
    printRaceStatus()
  }

  def raceFinished() : Boolean = {
    drivers.foreach(d => if(!driverCompletedTheRace(d)) return false)
    true
  }

  def printRaceStatus() = {
    drivers.sortWith((d1, d2) => d1.totalDriveTime < d2.totalDriveTime)
    println("DriverId CompletionTime  Speed")
    drivers.foreach(d => println(d.car.number, "     ", d.totalDriveTime, "    ", d.car.speed))
  }

  def driverCompletedTheRace(driver: Driver) : Boolean = {
    driver.distanceFromStartPosition >= lengthOfTrack
  }

  def updateRaceStatus() = {
    drivers.sortWith(sortByDistanceFromStart)
  }

  def sortByDistanceFromStart(d1:Driver, d2: Driver) = {
    d1.distanceFromStartPosition > d2.distanceFromStartPosition
  }


  def setStartPosition() = {
    for(a <- 1 to numberOfTeams){
      if(a==1)
        drivers(a).distanceFromStartPosition = 0
      else
        drivers(a).distanceFromStartPosition = drivers(a-1).distanceFromStartPosition - a*200
    }
  }

  override def isAmLast(driver: Driver): Boolean = {
    drivers.last.equals(driver)
  }

  override def anyCarAroundTenMeters(driver: Driver): Boolean = {
     def nextDriver(driver: Driver): Driver = drivers(drivers.indexOf(driver) + 1)

     if(!isAmLast(driver))
       return (nextDriver(driver).distanceFromStartPosition - driver.distanceFromStartPosition) <= 10.0

     false
  }
}
