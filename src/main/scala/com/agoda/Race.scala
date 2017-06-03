package scala.com.agoda

import scala.collection.mutable.ListBuffer
import scala.com.agoda.interface.RaceI

/**
  * Created by bajaj on 02/06/17.
  */
class Race(val numberOfTeams:Integer, val lengthOfTrack:Integer) extends RaceI {
  val driverStartPositions = startPositions()
  val drivers = (for (i <- (1 to numberOfTeams).toList) yield new Driver(new Car(i), driverStartPositions(i-1), this)).toList

  override def startRace(): Unit = {
    println("Race is starting")
    val timeInterval = 2

    while(!raceFinished()) {
      drivers.foreach(d => d.driveForSeconds(timeInterval))
      updateRaceStatus()
      drivers.foreach(d => d.handleCarAfterDriving())
    }

    println("Race is finished")
    printRaceStatus()
  }

  override def isIAmLast(driver: Driver): Boolean = drivers.sortWith(sortByDistanceFromStart).last.equals(driver)


  override def anyCarAroundTenMeters(driver: Driver): Boolean = {
     def nextDriver(driver: Driver): Driver = {
       val temp = drivers.sortWith(sortByDistanceFromStart)
       temp(temp.indexOf(driver) - 1)
     }

     if(!isIAmFirst(driver))
       return (nextDriver(driver).distanceTravelled - driver.distanceTravelled) <= 10.0

     false
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

  def startPositions() : List[Double] = {
    var startPositionList = new ListBuffer[Double]()
    for(a <- 1 to numberOfTeams){
      if(a==1)
        startPositionList += 0
      else
      startPositionList += startPositionList.toList.last - (a-1)*200
    }
    startPositionList.toList
  }

  def isIAmFirst(driver: Driver): Boolean = drivers.sortWith(sortByDistanceFromStart).head.equals(driver)

}
