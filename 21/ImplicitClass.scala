case class Rectangle(w: Int, h: Int)

implicit class RectangleMaker(w: Int) {
  def x(h: Int) = Rectangle(w, h)
}
