package uk.co.alexknight.bacteriaScala

class Location
{
  var xLocation: Int = 0;
  var yLocation: Int = 0;

  override def hashCode(): Int = s"$xLocation$yLocation".hashCode

  override def toString: String = s"${xLocation},${yLocation}"

  def Location(xLoc: Int, yLoc: Int): Location =
  {
    val returnLoc = new Location
    returnLoc.yLocation = yLoc;
    returnLoc.xLocation = xLoc;
    return returnLoc;
  }
}
