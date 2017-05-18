import java.time.LocalTime
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._

Future {
  Thread.sleep(5000)
  println(s"This is future at ${LocalTime.now}")
}
println(s"This is present at ${LocalTime.now}")
val f = Future {
  Thread.sleep(2000)
  42
}
Await.result(f, 2.seconds)

val ff = Future {
  Thread.sleep(10000)
  if (math.random() < 0.5) throw new Exception else 42
}

import scala.util._
f.onComplete {
  case Success(v) => println(s"the answer is $v")
  case Failure(ex) => println(ex.getMessage)
}
