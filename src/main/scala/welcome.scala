import enums._
import case_classes._

def assertTypesEqual[A, B >: A <: A]: Unit = ()

object welcome:
  //
  // Please join and say hello:
  //
  // CHAT ROOM: https://discord.gg/bc62QxMWqd
  //
  // Please git clone and build:
  //
  // REPOSITORY: https://github.com/jdegoes/scala3-for-scala2-developers
  //
  // Daily Schedule:
  //
  //    START  : 11:00 AM London Time
  //    BREAK  :  2:00 PM London Time
  //    RESUME :  3:00 PM London Time
  //    END    :  7:00 PM London Time
  def main(args: Array[String]): Unit = {
    // DaysOfWeek
    val dsow: Array[DayOfWeek] = daysOfWeek
    val dow = sunday
    println(dow)

    // Colors
    val customColor = Color.Custom(255, 255, 0)
    println(customColor)
    
    // Workflow
    val wf: String = Workflow.End(s => s.toString).apply(999)
    println(wf)
    
    // Conversion
    val cv = Conversion.AnyToString
    println(cv)
    
    // Email
    val email = Email.fromString("")
    println(email)
    val validEmail = Email.fromString("ABC0123@DEF456.COM")
    println(validEmail)

    // This doesn't work :method copy cannot be accessed as a member of (email : case_classes.Email) from module class
    // 02-data$package$.
//    val changedEmail = changeEmail(validEmail.get)
//    print(changedEmail)

    val newEmail = caseClassApply("hello")
  }