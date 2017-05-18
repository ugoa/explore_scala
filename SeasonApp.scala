import ChecksumAccumulator.calculate

object SeasonApp extends App {
  for (arg <- args)
    println(arg + ": " + calculate(arg))
}