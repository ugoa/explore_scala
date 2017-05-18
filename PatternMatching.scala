import math.Pi

sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

val pi = math.Pi

def simplifyTop(expr: Any) = expr match {
  // constant pattern
  case Pi => "found Constant Pi"
  // variable as constant pattern 
  case `pi` => "found Constant Pi"
  // constructor pattern
  case UnOp("-", UnOp("-", e)) => e // Double negation
  case BinOp("+", e, Number(0)) => e // Add zero
  case BinOp("*", e, Number(1)) => e // Muitiplying by one

  // sequence pattern with fixed length
  case List(0, _, _) => println("found 0")
  // sequence pattern with arbitrary length
  case List(0, _*) => println("found 0")

  // tuple pattern
  case (a, b, c) => s"matched $a $b $c"

  // typed pattern
  case s: String => s.length
  case m: Map[_, _] => m.size
  // cannot do the following because type erasure
  // case m: Map[Int, Int] => m.size
  // but Array can
  case arr: Array[String] => "list of strings"

  // pattern guard
  case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2))
  case n: Int if n < 0 => n
  case s: String if s(0) == 'a' => s.length

  // if the expr is an Array, return the second element
  // this match is not exhaustive
  case x :: y :: _ => y

  // wildcard pattern
  case _ => expr
}

abstract class Element {

  def contents: Array[String]

  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length

  def demo() = println("Element's implementation invoked")

  def above(that: Element): Element = elem(this.contents ++ that.contents)
  
  def beside(that: Element): Element = {
    new ArrayElement(
      for (
        (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2
    )
  }

  override def toString = contents.mkString("\n")
}

object Element {
  def elem(contents: Array[String]): Element = new ArrayElement(contents)

  def elem(char: Char, width: Int, height: Int): Element =
    new UniformElement(char, width, height)

  def elem(line: String): Element = new LineElement(line)
}


class ExprFormatter {
  private val opGroups =
    Array(
      Set("|", "||"),
      Set("&", "&&"),
      Set("^"),
      Set("==", "!="),
      Set("<", "<=", ">", ">="),
      Set("+", "-"),
      Set("*", "%")
    )
  private val precedence = {
    val assocs =
      for {
        i <- 0 until opGroups.length
        op <- opGroups(i)
      } yield op -> i
    assocs.toMap
  } 
  private val unaryPrececence = opGroups.length
  private val fractionPrecedence = -1

  private def format(e: Expr, enclPrec: Int): Element = {
    import Element.elem
    e match {
      case Var(name) => elem(name)
      case Number(num) =>
        def stripDot(s: String) =>
          if (s endsWith ".0") s.subString(0, s.length - 2) else s
        elem(stripDot(num))
      case UnOp(op, arg) =>
        elem(op) beside format(arg, unaryPrececence)
    }
  }
}
