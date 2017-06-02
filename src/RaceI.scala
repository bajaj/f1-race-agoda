/**
  * Created by bajaj on 02/06/17.
  */
trait RaceI {
  def isIAmLast(driver: Driver): Boolean
  def anyCarAroundTenMeters(driver: Driver): Boolean
}
