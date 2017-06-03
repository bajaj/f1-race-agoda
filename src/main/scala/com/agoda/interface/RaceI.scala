package scala.com.agoda.interface

import scala.com.agoda.Driver

/**
  * Created by bajaj on 03/06/17.
  */
trait RaceI {
  def startRace()
  def isIAmLast(driver: Driver): Boolean
  def anyCarAroundTenMeters(driver: Driver): Boolean
}
