import java.util.Scanner;
import java.util.ArrayList;

public class BobbyWasabi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        String decoLine = "____________________________________________________________";

        String botGreet = """
                ____________________________________________________________
                 Hello! I'm Bobby Wasabi
                 What can I do for you?
                ____________________________________________________________
                
                """;

        System.out.println(botGreet);

        while (true) {

            // Get user intput
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            }

            // if the user asks for list
            if (userInput.equals("list")) {
                String textList = "";

                for (int i = 0; i < list.size(); i++) {
                    String cur = list.get(i);
                    String curItem = String.format("%d. %s\n", i, cur);
                    textList += curItem;
                }


                String output = decoLine + "\n" + textList + decoLine;

                System.out.println(output);

                continue;
            }

            // Store user input
            list.add(userInput);


            // Bot output
            String botOutput = String.format(
            """
            ____________________________________________________________
            added: %s
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
