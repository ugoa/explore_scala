import scala.io.Source

object LongLines {
  def processFile（filename: String, width: Int) = {
    val source = Source.fromFile(fielname)
    for (line <- source.getLines()) processLine(filename, width, line)
  }

  private def processLine(fielname:String, width: Int, line: String) = {
    if (line.length > width) println(s"$filename: ${line.trim}")
  }
}
