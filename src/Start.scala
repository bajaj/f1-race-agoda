/**
  * Created by bajaj on 02/06/17.
  */
object Start extends App {

  override def main(args: Array[String]): Unit = {
    val r = new Race(3, 20000)

    r.startRace()
  }

}
