/*
 * Copyright 2017 Alex Scown
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package dev.scown.futoshiki

/**
  * A Futoshiki puzzle instance
  * @param values The values currently known in the puzzle
  * @param constraints The indexed constraints of the puzzle
  * @param size The size of the puzzle
  */
case class Futoshiki(private val values: Map[Coordinates, Int], constraints: Constraints, size: Int = 5) {

  /**
    * Get the value at the specified coordinate
    * @param c The coordinates
    * @return The value at the coordinates or None if the value is unknown
    */
  def get(c: Coordinates): Option[Int] = values.get(c)

  /**
    * Insert a known value into the puzzle
    * @param c The coordinates
    * @param v The known value
    * @return A new puzzle instance with the value added
    */
  def +(c: Coordinates, v: Int): Futoshiki = copy(values = values + (c -> v))

}

/**
  * Index of constraints by square
  *
  * @param constraints A list of all the constraints in the puzzle
  */
case class Constraints(private val constraints: List[Constraint]) {

  private val map = constraints.groupBy({
    case(lesser, greater) => CoordinatesOrdering.max(lesser, greater)
  })

  /**
    * @param c The corrdinate
    * @return A list of constraints for which c is the greater coordinate, or None if there are no constraints
    */
  def get(c: Coordinates): Option[List[Constraint]] = map.get(c)

}

/**
  * Orders coordinates by first dimension, followed by second dimension
  */
private object CoordinatesOrdering extends Ordering[Coordinates] {
  override def compare(c1: Coordinates, c2: Coordinates): Int = c1 match {
    case (x1, y1) => c2 match {
      case (x2, y2) =>
        val xs = Integer.compare(x1, x2)

        if (xs == 0) Integer.compare(y1, y2) else xs
    }
  }
}

