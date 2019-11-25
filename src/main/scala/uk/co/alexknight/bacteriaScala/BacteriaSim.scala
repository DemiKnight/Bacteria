package uk.co.alexknight.bacteriaScala

import java.util.Scanner

import scala.collection.mutable

object BacteriaSim
{
  var bacteriaStore: mutable.HashSet[Location] = new mutable.HashSet[Location]();

  def input(): Unit =
  {
    var scanner = new Scanner(System.in);


    // scanner.nextL
    do
    {
      val locTemp = for (selStr <- scanner.next().split(",")) yield Integer(selStr)

      bacteriaStore.add(scanner.next() (test: String) -> new Location(locTemp._0, locTemp._1))
    } while (!scanner.hasNext("end"))
  }

  def output(): Unit =
  {
    for(selLoc <- bacteriaStore) println(selLoc)
    print("end")
  }
}
