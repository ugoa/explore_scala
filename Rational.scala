class Rational(numerator: Int, denominator: Int) {
  require(denominator != 0)

  private val g  = gcd(numerator.abs, denominator.abs)
  val n: Int = numerator / g
  val d: Int = denominator / g

  def this(n: Int) = this(n, 1) // auxiliary constructor

  override def toString = s"$n/$d"

  def + (that: Rational): Rational = new Rational(n * that.d + that.n * d, d * that.d)
  def + (i: Int): Rational = new Rational(n + i * d, d) 

  def - (that: Rational): Rational = new Rational(n * that.d - that.n * d, d * that.d)
  def - (i: Int): Rational = new Rational(n - i * d, d) 

  def * (that: Rational): Rational = new Rational(n * that.d + that.n * d, d * d)
  def * (i: Int): Rational = new Rational(n * i, d)

  def / (that: Rational): Rational = new Rational(n * that.d, d * that.n)
  def / (i: Int): Rational = new Rational(n, d * i)

  // greatest common divisor
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}
