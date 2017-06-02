/**
  * Created by bajaj on 02/06/17.
  */
class Car(val number:Integer) {
    val handlingFactor = 0.8
    val topSpeed = 150.0 + 10*number
    val acceleration = 2*number

    var nitro = 1
    var speed  = 0.0
    var distanceTravelled = 0.0

    def useNitroIsPossible(): Unit = {
      if (nitro != 0) {
        nitro -= 1
        speed = Math.max(speed*2, topSpeed)
      }
    }

   def useHandLing(): Unit = {
     speed = handlingFactor * speed
   }

   def accelerate(time: Int): Unit = {
     speed += acceleration*time
   }

   def driveForSeconds(time: Int): Unit = {
     distanceTravelled += speed * time + 0.5*acceleration*time
     accelerate(time)
   }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Car]

  override def equals(other: Any): Boolean = other match {
    case that: Car =>
      (that canEqual this) &&
        number == that.number
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(number)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}