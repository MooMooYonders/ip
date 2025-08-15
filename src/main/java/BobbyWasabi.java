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

        public void setSecond(Boolean bool) {
            this.isMarked = bool;
        }

        public String checked() {
            if (this.isMarked) {
                return "X";
            } else {
                return " ";
            }
        }


    }

    public static boolean isValidInteger(String s, int arrLen) {
        String[] wordList = s.split(" ");

        // not valid command length
        if (wordList.length != 2) {
            return false;
        }

        // not valid command
        if (!(wordList[0].equals("mark") || wordList[0].equals("unmark"))) {
            return false;
        }

        // not a valid integer
        try {
            int indx = Integer.parseInt(wordList[1]);
            if (indx >= arrLen) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }

        return true;
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

            // Get user input
            String userInput = scanner.nextLine();

            // if the user says bye
            if (userInput.equals("bye")) {
                break;
            }

            // if the user asks for list
            if (userInput.equals("list")) {
                StringBuilder textList = new StringBuilder("Here are the tasks in your list:\n");

                for (int i = 0; i < list.size(); i++) {
                    Pair cur = list.get(i);
                    String curItem = cur.getFirst();
                    String checked = cur.checked();

                    String curTask = String.format("%d. [%s] %s\n", i, checked, curItem);
                    textList.append(curTask);
                }


                String output = decoLine + "\n" + textList.toString() + decoLine;

                System.out.println(output);

                continue;
            }

            // checks if the user wants to mark/unmark task
            if (BobbyWasabi.isValidInteger(userInput, list.size())) {
                String[] wordList = userInput.split(" ");
                int indx = Integer.parseInt(wordList[1]);
                String command = wordList[0];
                Pair targetTask = list.get(indx);

                if (command.equals("mark")) {
                    targetTask.setSecond(true);
                } else {
                    list.get(indx).setSecond(false);
                }

                String curTask = String.format(
                        "%d. [%s] %s\n",
                        indx,
                        targetTask.checked(),
                        targetTask.getFirst());

                String output = String.format("""
                            Nice! I've marked this task as done:
                               %s""",
                        curTask);

                System.out.println(decoLine + "\n" + output + decoLine);

                continue;
            }

            // no special commands given

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
