package org.scown.futoshiki

case class Futoshiki(private val values: Map[Coordinates, Int], constraints: Constraints, size: Int = 5) {

  def get(c: Coordinates): Option[Int] = values.get(c)

  def +(c: Coordinates, v: Int): Futoshiki = copy(values = values + (c -> v))

}

case class Constraints(private val constraints: List[Constraint]) {

  private val map = constraints.groupBy({
    case(lesser, greater) => CoordinatesOrdering.max(lesser, greater)
  })

  def get(c: Coordinates): Option[List[Constraint]] = map.get(c)

}

private object CoordinatesOrdering extends Ordering[Coordinates] {
  override def compare(c1: Coordinates, c2: Coordinates): Int = c1 match {
    case (x1, y1) => c2 match {
      case (x2, y2) =>
        val xs = Integer.compare(x1, x2)

        if (xs != 0) Integer.compare(y1, y2) else xs
    }
  }
}

