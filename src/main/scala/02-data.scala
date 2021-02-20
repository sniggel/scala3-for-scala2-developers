/**
 * ENUMS
 * 
 * Scala 3 adds support for "enums", which are to sealed traits like case classes 
 * were to classes. That is, enums cut down on the boilerplate required to use 
 * the "sealed trait" pattern for modeling so-called sum types, in a fashion very 
 * similar to how case classes cut down on the boilerplate required to use 
 * classes to model so-called product types.
 * 
 * Strictly speaking, Scala 3 enums are not the same as Java enums: while the 
 * constructors of enums are finite, and defined statically at compile-time in the 
 * same file, these constructors may have parameters, and therefore, the total 
 * number of values of any enum type could be large or infinite.
 * 
 * Enums and case classes provide first-class support for "algebraic data types" 
 * in Scala 3.
 */
package enums

: 
  /**
   * EXERCISE 1
   * 
   * Convert this "sealed trait" to an enum.
   */
//  sealed trait DayOfWeek
  enum DayOfWeek:
    case Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
  end DayOfWeek
//  object DayOfWeek:
//    case object Sunday extends DayOfWeek
//    case object Monday extends DayOfWeek
//    case object Tuesday extends DayOfWeek
//    case object Wednesday extends DayOfWeek
//    case object Thursday extends DayOfWeek
//    case object Friday extends DayOfWeek
//    case object Saturday extends DayOfWeek

  /**
   * EXERCISE 2
   * 
   * Explore interop with Java enums by finding all values of `DayOfWeek`, and by 
   * finding the value corresponding to the string "Sunday".
   */
  def daysOfWeek: Array[DayOfWeek] = DayOfWeek.values
  def sunday: DayOfWeek = DayOfWeek.valueOf("Sunday")

  /**
   * EXERCISE 3
   * 
   * Convert this "sealed trait" to an enum.
   * 
   * Take special note of the inferred type of any of the case constructors!
   */
//  sealed trait Color 
//  object Color:
//    case object Red extends Color 
//    case object Green extends Color 
//    case object Blue extends Color
//    final case class Custom(red: Int, green: Int, blue: Int) extends Color

  enum Color(red: Int, green: Int, blue: Int):
    case Custom(red: Int, green: Int, blue: Int) extends Color(red, green, blue)
    case Red extends Color(255, 0, 0)
    case Green extends Color(0, 255, 0)
    case Blue extends Color(0, 0, 255)
  end Color

  /**
   * EXERCISE 4
   * 
   * Convert this "sealed trait" to an enum.
   * 
   * Take special note of the inferred type parameters in the case constructors!
   */
//  sealed trait Result[+Error, +Value]
//  object Result:
//    final case class Succeed[Value](value: Value) extends Result[Nothing, Value]
//    final case class Fail[Error](error: Error) extends Result[Error, Nothing]

  enum Result[+Error, +Value](error: Error, value: Value):
    case Succeed(error: Nothing, value: Value) extends Result(error, value)
    case Failure(error: Error, value: Nothing) extends Result(error, value)
  end Result

  /**
   * EXERCISE 5
   * 
   * Convert this "sealed trait" to an enum.
   * 
   * Take special note of the inferred type parameters in the case constructors!
   */
//  sealed trait Workflow[-Input, +Output]
//  object Workflow:
//    final case class End[Output](value: Output) extends Workflow[Any, Output]
  enum Workflow[-Input, +Output] extends (Input => Output):
    case End[I, O](f: I => O) extends Workflow[I, O]
    final def apply(input: Input): Output = this match
      case end: End[i, o] => end.f(input)
  end Workflow

  /**
   * EXERCISE 6
   * 
   * Convert this "sealed trait" to an enum.
   */
//  sealed trait Conversion[-From, +To]
//  object Conversion:
//    case object AnyToString extends Conversion[Any, String]
//    case object StringToInt extends Conversion[String, Option[Int]]
  enum Conversion[-From, +To]:
    case AnyToString(from: Any) extends Conversion[Any, String]
    case StringToInt(from: String) extends Conversion[String, Option[Int]]
  end Conversion

/**
 * CASE CLASSES
 * 
 * Scala 3 makes a number of improvements to case classes.
 */
package case_classes:
  /**
   * EXERCISE 1
   * 
   * By making the public constructor private, make a smart constructor for `Email` so that only 
   * valid emails may be created.
   */
  final case class Email private (value: String)
  object Email:
    def fromString(v: String): Option[Email] = if (isValidEmail(v)) Some(Email(v)) else None

    def isValidEmail(v: String): Boolean = v.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
  end Email

  /**
   * EXERCISE 2
   * 
   * Try to make a copy of an existing `Email` using `Email#copy` and note what happens.
   * 
   */
  def changeEmail(email: Email): Email = ???// email.copy("ffff@dddd.com")
  // I cannot create a copy: method copy cannot be accessed as a member of (email : case_classes.Email) from module
  // class 02-data$package$.

  /**
   * EXERCISE 3
   * 
   * Try to create an Email directly by using the generated constructor in the companion object.
   * 
   */
  def caseClassApply(value: String): Email = ??? //Email.fromString(value)
  // The constructor fromString returns an Option, not an Email

/**
 * PATTERN MATCHING
 * 
 * Scala 3 provides upgrades to the power and flexibility of pattern matching.
 */  
object pattern_matching:
  /**
   */
  def foo: Int = 2