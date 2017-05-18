class Operator {
  val filesHere = (new java.io.File(".")).listFiles
  def fileLines(file: java.io.File) =
    scala.io.Source.fromFile(file).getLines().toList

  def scalaFiles =
    for {
      file <- filesHere if file.getName.endsWith(".scala")
    } yield file

  def grep(pattern: String) = {
    for {
      file <- filesHere if file.getName.endsWith(".scala")
      line <- fileLines(file)
      trimmed = line.trim
      if trimmed.matches(pattern)
    } println(s"$file: $trimmed")
  }
}
