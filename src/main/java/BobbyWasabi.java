import java.util.Scanner;
import java.util.ArrayList;

public class BobbyWasabi {

    private static class Pair {
        private String item;
        private Boolean isMarked;

        public Pair(String item, Boolean isMarked) {
            this.item = item;
            this.isMarked = isMarked;
        }

        public String getFirst() {
            return this.item;
        }

        public Boolean getSecond() {
            return this.isMarked;
        }


    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pair> list = new ArrayList<>();


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
                StringBuilder textList = new StringBuilder("");

                for (int i = 0; i < list.size(); i++) {
                    Pair cur = list.get(i);
                    String curItem = cur.getFirst();
                    Boolean curMarked = cur.getSecond();
                    String checked = curMarked
                            ? "X"
                            : " ";

                    String curTask = String.format("%d. [%s] %s\n", i, checked, curItem);
                    textList.append(curTask);
                }


                String output = decoLine + "\n" + textList.toString() + decoLine;

                System.out.println(output);

                continue;
            }

            // Store user input
            list.add(new Pair(userInput, false));


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
