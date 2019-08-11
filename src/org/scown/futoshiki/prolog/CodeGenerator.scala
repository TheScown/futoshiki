package org.scown.futoshiki.prolog

import java.io.PrintStream

import org.scown.futoshiki.{Coordinates, Futoshiki}

case class CodeGenerator(f: Futoshiki) {

  private val values = 1 to f.size

  private val coordinates = (for {
    x <- 1 to f.size
    y <- 1 to f.size
  } yield (x, y)).toList

  private val IN_RANGE =
    Rule(
      Fact("in_range", List(Variable("X"))),
      Terminal(
        Fact("member", List(
          Variable("X"),
          PrologList(values.map(n => Number(n)).toList)
        ))
      )
    )

  private val IS_SET_BASE =
    Terminal(Fact("is_set", List(PrologList(List()))))

  private val IS_SET =
    Rule(
      Fact("is_set", List(HeadTailList(Variable("H"), Variable("T")))),
      Terminal(
        And(
          Fact("is_set", List(Variable("T"))),
          Not(Fact("member", List(Variable("H"), Variable("T"))))
        )
      )
    )

  private val futoshiki = {
    val bodyTerms: List[Term] = coordinates.flatMap({
      case c@(x,y) =>
        val valuePredicate: Term = if (f.get(c).isDefined)
          Equal(c, Number(f.get(c).get))
        else
          Fact("in_range", List(c))

        val rowSet: Term = if (y > 1) Fact("is_set", List(PrologList((for {
          y1 <- 1 to y
        } yield (x, y1)).toList)))
        else Value("placeholder")

        val columnSet: Term = if (x > 1) Fact("is_set", List(PrologList((for {
          x1 <- 1 to x
        } yield (x1, y)).toList)))
        else Value("placeholder")

        val constraints: List[Term] = f.constraints.get(c).getOrElse(Nil).map({
          case (lesser, greater) => LessThan(lesser, greater)
        })

        val predicates = List(
          List(valuePredicate),
          List(rowSet),
          List(columnSet),
          constraints
        ).flatten.filter({
          case Value("placeholder") => false
          case _ => true
        })

        predicates
    })

    Rule(
      Fact("futoshiki", coordinates),
      Terminal(
        andReduce(bodyTerms)
      )
    )
  }

  def writeProlog(out: PrintStream): Unit = {
    out.println(IN_RANGE.toProlog)
    out.println()
    out.println(IS_SET_BASE.toProlog)
    out.println(IS_SET.toProlog)
    out.println()

    out.print(futoshiki.toProlog.replaceAll(",", ",\n"))
  }

  private def andReduce(ts: List[Term]): Term = {
    ts.reduce((left, right) => And(left, right))
  }

  private implicit def coordinates2Variable(c: Coordinates): Variable = c match {
    case (x, y) => Variable("V_" + x + '_' + y)
  }

  private implicit def coordinateList2VariableList(cs: List[Coordinates]): List[Variable] = cs match {
    case Nil => Nil
    case c::tail => c:: coordinateList2VariableList(tail)
  }

}
