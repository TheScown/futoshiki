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
  override def toProlog: String = left.toProlog + ',' + right.toProlog
}

case class Or(left: Term, right: Term) extends Term {
  override def toProlog: String = left.toProlog + ';' + right.toProlog
}

case class Not(left: Term, right: Term) extends Term {
  override def toProlog: String = left.toProlog + "\\+" + right.toProlog
}
