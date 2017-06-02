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
      drivers.foreach(d => if(!driverCompletedTheRace(d)) {
        d.driveForSeconds(timeInterval)
      })
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
    println("DriverId CompletionTime  Speed")
    drivers.sortWith((d1, d2) => d1.totalDriveTime < d2.totalDriveTime)
            .foreach(d => println(d.car.number,"     ",d.totalDriveTime, "               ", d.car.speed))
  }

  def driverCompletedTheRace(driver: Driver) : Boolean = {
    driver.distanceFromStartPosition >= lengthOfTrack
  }

  def sortByDistanceFromStart(d1:Driver, d2: Driver) = {
    d1.distanceFromStartPosition > d2.distanceFromStartPosition
  }


  def setStartPosition() = {
    for(a <- 1 to numberOfTeams){
      if(a==1)
        drivers(a-1).distanceFromStartPosition = 0
      else
        drivers(a-1).distanceFromStartPosition = drivers(a-2).distanceFromStartPosition - (a-1)*200
    }
  }

  override def isAmLast(driver: Driver): Boolean = drivers.sortWith(sortByDistanceFromStart).last.equals(driver)


  override def anyCarAroundTenMeters(driver: Driver): Boolean = {
     def nextDriver(driver: Driver): Driver = {
       val temp = drivers.sortWith(sortByDistanceFromStart)
       temp(temp.indexOf(driver) - 1)
     }

     if(!isIAmFirst(driver))
       return (nextDriver(driver).distanceFromStartPosition - driver.distanceFromStartPosition) <= 10.0

     false
  }

  def isIAmFirst(driver: Driver): Boolean = drivers.sortWith(sortByDistanceFromStart)(0).equals(driver)

}
