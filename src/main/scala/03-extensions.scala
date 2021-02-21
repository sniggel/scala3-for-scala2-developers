/**
 * EXTENSION METHODS
 * 
 * Scala 3 brings first-class support for "extension methods", which allow adding methods to 
 * classes after their definition. Previously, this feature was emulated using implicits.
 */
object ext_methods:
  def main(args: Array[String]): Unit = {
    println(Email("ggg@gmail.com").username)
    println(Email("ggg@gmail.com").server)
    println(Some("A").zip(Some("B")))
    
    println(Rational(1, 4) + Rational(5, 3))
    println(Rational(5, 3) - Rational(1, 2))
    
    println("abc".equalsIgnoreCase("abc"))
  }
  
  final case class Email(value: String)

  /**
   * EXERCISE 1
   * 
   * Add an extension method to `Email` to retrieve the username of the email address (the part 
   * of the string before the `@` symbol).
   */
  extension (e: Email) def username: String = e.value.split('@').head

  val sherlock = Email("sherlock@holmes.com").username

  /**
   * EXERCISE 2
   * 
   * Add an extension method to `Email` to retrieve the server of the email address (the part of 
   * the string after the `@` symbol).
   */
  // extension
  extension (e: Email) def server: String = e.value.split('@').last
  
  /**
   * EXERCISE 3
   * 
   * Add an extension method to `Option[A]` that can zip one option with another `Option[B]`, to 
   * return an `Option[(A, B)]`.
   */
  // extension 
  extension[A, B] (optA: Option[A]) def zip(optB: Option[B]): Option[(A, B)] =
    optA.fold(None)(a => optB.fold(None)(b => Some((a, b))))

  /**
   * A rational number is one in the form n/m, where n and m are integers.
   */
  final case class Rational(numerator: BigInt, denominator: BigInt):
    override def toString(): String = s"$numerator / $denominator"

  /**
   * EXERCISE 4
   * 
   * Add a collection of extension methods to `Rational`, including `+`, to add two rational 
   * numbers, `*`, to multiply two rational numbers, and `-`, to subtract one rational number 
   * from another rational number.
   */
  def gcd(a: BigInt, b: BigInt): BigInt = if (a == 0) b else gcd(b % a, a)
  def lcm(a: BigInt, b: BigInt): BigInt = (a * b) / gcd(a, b)

  // extension
  extension (left: Rational) def +(right: Rational): Rational =
    val lcd = lcm(left.denominator, right.denominator)
    val num1 = left.numerator * (lcd / left.denominator)
    val num2 = right.numerator * (lcd / right.denominator)
    Rational(num1 + num2, lcd)

  extension (left: Rational) def *(right: Rational): Rational =
    Rational(left.numerator * right.numerator, left.denominator * right.denominator)

  extension (left: Rational) def -(right: Rational): Rational =
    val lcd = lcm(left.denominator, right.denominator)
    val num1 = left.numerator * (lcd / left.denominator)
    val num2 = right.numerator * (lcd / right.denominator)
    Rational(num1 - num2, lcd)

  /**
   * EXERCISE 5
   * 
   * Convert this implicit syntax class to use extension methods.
   */
//  implicit class StringOps(self: String):
//    def equalsIgnoreCase(that: String) = self.toLowerCase == that.toLowerCase
  
  extension (l: String) def equalsIgnoreCase(r: String): Boolean = l.toLowerCase == r.toLowerCase

  /**
   * EXERCISE 6
   * 
   * Import the extension method `isSherlock` into the following object so the code will compile.
   */
  object test:
    import string_extensions.isSherlock
    val test: Boolean = "John Watson".isSherlock

  object string_extensions:
    extension (s: String) def isSherlock: Boolean = s.startsWith("Sherlock")