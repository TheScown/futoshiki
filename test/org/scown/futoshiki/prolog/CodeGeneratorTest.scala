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
