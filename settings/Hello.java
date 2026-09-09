// A compact source file (JEP 512, final in Java 25).
//
// Notice what is missing compared to classic Java:
//   - no class declaration, the file itself is the class
//   - no imports, java.base is imported as a module for you
//   - no "public static void main(String[] args)", just "void main()"
//   - no "System.out.println", the new java.lang.IO class gives you IO.println
//
// Run it with: java Hello.java

record Talk(String title, String speaker, int minutes) {}

List<Talk> talks = List.of(
    new Talk("Claude Code: Start Simple, Scale Smart", "Dan Vega", 50),
    new Talk("What's New in Java 25", "Jane Doe", 45),
    new Talk("Spring Boot 4 in Practice", "John Smith", 60)
);

void main() {
    IO.println("Conference Schedule");
    IO.println("===================");
    printSchedule();
    IO.println();
    IO.println("Total time: " + totalMinutes() + " minutes");
}

void printSchedule() {
    for (Talk talk : talks) {
        IO.println(talk.title() + " by " + talk.speaker() + " (" + talk.minutes() + " min)");
    }
}

int totalMinutes() {
    return talks.stream().mapToInt(Talk::minutes).sum();
}
