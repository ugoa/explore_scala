class PreferredPrompt(val preference: String)

object JoesPrefs {
  implicit val prompt = new PreferredPrompt("Yes, master> ")
}

object Greeter {
  def greet(name: String)(implicit prompt: PreferredPrompt) = {
    println(s"Welcome, $name. The system is ready")
    println(prompt.preference)
  }
}

