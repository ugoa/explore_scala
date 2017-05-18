package org.david.funScala

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

class ArrayElement(
  val contents: Array[String]
) extends Element {
  override def demo() = println("ArrayElement invoked")
}

class LineElement(s: String) extends Element {
  val contents = Array(s)
  override def height = 1
  override def width = s.length

  override def demo() = println("LineElement invoked")
}

class UniformElement(val contents: Array[String]) extends Element
