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

        if (xs == 0) Integer.compare(y1, y2) else xs
    }
  }
}

