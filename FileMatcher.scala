import java.io.File

object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere if matcher(file.getName)) yield file

  def filesEnding(query: String) = filesMatching(_.endWith(query))
  def filesContaining(query: String) = filesMatching(_.contains(query))
  def filesRegex(query: String) = filesMatching(_.matches(query))


}

def withPrintWriter(file: File)(op:PrintWriter => Unit) = {
  val writer = new PrintWriter(file)

  try {
    op(writer)
  } finally {
    writer.close()
  }
}

val file = new File("date.ext")
withPrintWriter(file) { writer =>
  writer.println(new java.util.Date)
}
