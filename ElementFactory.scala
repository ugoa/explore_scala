object Element {
  def elem(contents: Array[String]): Element = new ArrayElement(contents)

  def elem(char: Char, width: Int, height: Int): Element =
    new UniformElement(char, width, height)

  def elem(line: String): Element = new LineElement(line)
}
