import java.util.Scanner;

public class BobbyWasabi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String botGreet = """
                ____________________________________________________________
                 Hello! I'm Bobby Wasabi
                 What can I do for you?
                ____________________________________________________________
                
                """;

        System.out.println(botGreet);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput == "bye") {
                break;
            }

            String botOutput = String.format(
            """
            ____________________________________________________________
            %s
            ____________________________________________________________
                
            """, userInput);

            System.out.println(botOutput);

        }

        System.out.println("""
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
               
                """);

        scanner.close();
    }
}
