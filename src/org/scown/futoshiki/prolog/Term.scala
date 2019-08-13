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

sealed trait Term {

  def toProlog: String

}

sealed trait SimpleTerm extends Term

case class Value(name: String) extends SimpleTerm {

  require(name.charAt(0).isLower)

  override def toProlog: String = name

}

case class Variable(name: String) extends SimpleTerm {

  require(name.charAt(0).isUpper)

  override def toProlog: String = name

}

case class Number(value: Int) extends SimpleTerm {

  override def toProlog: String = value.toString

}

case class Fact(name: String, values: List[SimpleTerm]) extends Term {

  require(name.charAt(0).isLower)

  override def toProlog: String = name + '(' + values.map(_.toProlog).mkString(",") + ')'

}

case class PrologList(values: List[SimpleTerm]) extends SimpleTerm {
  override def toProlog: String = '[' + values.map(_.toProlog).mkString(",") + ']'
}

case class HeadTailList(head: SimpleTerm, tail: SimpleTerm) extends SimpleTerm {
  override def toProlog: String = '[' + head.toProlog + '|' + tail.toProlog + ']'
}

case class Rule(head: Fact, body: Terminal) extends Term {
  override def toProlog: String = head.toProlog + " :- " + body.toProlog
}

case class Bracketed(term: Term) extends Term {
  override def toProlog: String = '(' + term.toProlog + ")"
}

case class Terminal(term: Term) extends Term {
  override def toProlog: String = term.toProlog + '.'
}

case class And(left: Term, right: Term) extends Term {
  override def toProlog: String = left.toProlog + "," + right.toProlog
}

case class Or(left: Term, right: Term) extends Term {
  override def toProlog: String = left.toProlog + ';' + right.toProlog
}

case class Not(body: Term) extends Term {
  override def toProlog: String = "\\+" + body.toProlog
}

case class LessThan(left: SimpleTerm, right: SimpleTerm) extends Term {
  override def toProlog: String = left.toProlog + " < " + right.toProlog
}

case class Equal(left: SimpleTerm, right: SimpleTerm) extends Term {
  override def toProlog: String = left.toProlog + " = " + right.toProlog
}
