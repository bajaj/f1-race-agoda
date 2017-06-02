/**
  * Created by bajaj on 02/06/17.
  */
object Start extends App {

  override def main(args: Array[String]): Unit = {

    println("Enter number of teams")
    val noOfTeams = io.StdIn.readInt().ensuring(_ > 1, "No of teams should be greater than 1")

    println("Enter length of race track")
    val lengthRaceTrack = io.StdIn.readInt().ensuring(_ > 1, "Length of track should be greater than 1")
    
    val r = new Race(noOfTeams, lengthRaceTrack)

    r.startRace()
  }

}
