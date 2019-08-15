/*
 * Copyright 2017 Alex Scown
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package dev.scown.futoshiki.prolog

/**
  * Trait representing a Prolog term
  */
sealed trait Term {

  /**
    * @return The Prolog representation of the term
    */
  def toProlog: String

}

/**
  * Marker trait to denote some terms as simple
  */
sealed trait SimpleTerm extends Term

/**
  * Represents a Prolog value.
  *
  * @param name The value. MUST start with a lowercase character
  */
case class Value(name: String) extends SimpleTerm {

  require(name.charAt(0).isLower)

  override def toProlog: String = name

}

/**
  * A Prolog variable
  *
  * @param name The variable name. MUST start with an uppercase character
  */
case class Variable(name: String) extends SimpleTerm {

  require(name.charAt(0).isUpper)

  override def toProlog: String = name

}

/**
  * A Prolog integer
  *
  * @param value The integer value
  */
case class Number(value: Int) extends SimpleTerm {

  override def toProlog: String = value.toString

}

/**
  * A Prolog fact
  * @param name The name of the fact
  * @param values The terms that make up the fact
  */
case class Fact(name: String, values: List[SimpleTerm]) extends Term {

  require(name.charAt(0).isLower)

  override def toProlog: String = name + '(' + values.map(_.toProlog).mkString(",") + ')'

}

/**
  * A Prolog list
  * @param values The values that make up the list
  */
case class PrologList(values: List[SimpleTerm]) extends SimpleTerm {
  override def toProlog: String = '[' + values.map(_.toProlog).mkString(",") + ']'
}

/**
  * A Prolog list decomposed into a head and a tail
  * @param head The head of the list
  * @param tail The tail of the list
  */
case class HeadTailList(head: SimpleTerm, tail: SimpleTerm) extends SimpleTerm {
  override def toProlog: String = '[' + head.toProlog + '|' + tail.toProlog + ']'
}

/**
  * A Prolog rule
  * @param head The fact represented by the rule
  * @param body The body of the rule
  */
case class Rule(head: Fact, body: Terminal) extends Term {
  override def toProlog: String = head.toProlog + " :- " + body.toProlog
}

/**
  * A Prolog term wrapped in parentheses
  * @param term The wrapped term
  */
case class Bracketed(term: Term) extends Term {
  override def toProlog: String = '(' + term.toProlog + ")"
}

/**
  * A Prolog term followed by a termination character
  * @param term The terminated term
  */
case class Terminal(term: Term) extends Term {
  override def toProlog: String = term.toProlog + '.'
}

/**
  * A conjunction of two Prolog terms
  * @param left The left term
  * @param right The right term
  */
case class And(left: Term, right: Term) extends Term {
  override def toProlog: String = left.toProlog + "," + right.toProlog
}

/**
  * A disjunction of two Prolog terms
  * @param left The left term
  * @param right The right term
  */
case class Or(left: Term, right: Term) extends Term {
  override def toProlog: String = left.toProlog + ';' + right.toProlog
}

/**
  * A negated Prolog term
  * @param body The negated term
  */
case class Not(body: Term) extends Term {
  override def toProlog: String = "\\+" + body.toProlog
}

/**
  * A Prolog term that is less than another term
  * @param left The lesser term
  * @param right The greater term
  */
case class LessThan(left: SimpleTerm, right: SimpleTerm) extends Term {
  override def toProlog: String = left.toProlog + " < " + right.toProlog
}

/**
  * A Prolog term that is equal to another term
  * @param left The first term
  * @param right The second term
  */
case class Equal(left: SimpleTerm, right: SimpleTerm) extends Term {
  override def toProlog: String = left.toProlog + " = " + right.toProlog
}
