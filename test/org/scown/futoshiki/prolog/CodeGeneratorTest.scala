/*
 * Copyright 2017 Alex Scown
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.scown.futoshiki.prolog

import org.scown.futoshiki.{Constraints, Futoshiki}

/**
  * Created by alexscown on 4/6/17.
  */
class CodeGeneratorTest extends org.scalatest.FunSuite {

  test("20/05/2017") {
    val puzzle = Futoshiki(
      Map((1,1) -> 4),
      Constraints(List(
        ((1,2), (1,3)),
        ((1,3), (1,4)),
        ((2,1), (1,1)),
        ((2,4), (2,5)),
        ((3,1), (2,1)),
        ((3,1), (3,2)),
        ((3,2), (3,3)),
        ((3,2), (4,2)),
        ((4,3), (3,3)),
        ((4,3), (4,2)),
        ((5,2), (4,2)),
        ((4,5), (5,5)),
        ((5,4), (5,3)),
        ((5,5), (5,4))
      ))
    )

    CodeGenerator(puzzle).writeProlog(System.out)
  }

  test("03/06/2017") {
    val puzzle = Futoshiki(
      Map(),
      Constraints(List(
        ((1,2), (1,3)),
        ((1,3), (1,4)),
        ((1,5), (1,4)),
        ((2,2), (2,1)),
        ((3,2), (2,2)),
        ((3,3), (2,3)),
        ((3,2), (3,1)),
        ((3,2), (3,3)),
        ((4,3), (3,3)),
        ((4,4), (4,3)),
        ((5,2), (4,2)),
        ((4,4), (5,4)),
        ((4,1), (5,1)),
        ((5,1), (5,2))
      ))
    )

    CodeGenerator(puzzle).writeProlog(System.out)
  }

}
