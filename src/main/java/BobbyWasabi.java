import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class BobbyWasabi {

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

    public static String addTaskOutput(Task task, int num) {
        String decoLine = "____________________________________________________________";

        String s = String.format("""
                ____________________________________________________________
                Got it. I've added this task:
                    %s
                Now you have %d tasks in the list.
                """,
                task, num);

        return s;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();


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
                    Task cur = list.get(i);

                    String curTask = String.format("%d. %s\n", i, cur);
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
                Task targetTask = list.get(indx);

                if (command.equals("mark")) {
                    targetTask.setIsMarked(true);
                } else {
                    list.get(indx).setIsMarked(false);
                }

                String curTask = String.format(
                        "%d. %s\n",
                        indx,
                        targetTask);

                String output = String.format("""
                            Nice! I've marked this task as done:
                               %s""",
                        curTask);

                System.out.println(decoLine + "\n" + output + decoLine);

                continue;
            }

            // Todo, Deadline or Event
            String[] wordList = userInput.split(" ");
            if (wordList[0].equals("todo")) {
                String description = userInput.split("todo ")[1];
                Task todo = new ToDo(description, false);
                list.add(todo);

                System.out.println(BobbyWasabi.addTaskOutput(todo, list.size()));
                continue;

            } else if (wordList[0].equals("deadline")) {
                String[] deadline = userInput.split("/by", 2);

                if (deadline.length == 2) {
                    String description = deadline[0].split("deadline ")[1];
                    Task deadlineTask = new Deadline(description, false, deadline[1]);
                    list.add(deadlineTask);

                    System.out.println(BobbyWasabi.addTaskOutput(deadlineTask, list.size()));
                    continue;
                }

            } else if (wordList[0].equals("event")) {
                String[] event1 = userInput.split("/from", 2);
                String[] event2 = userInput.split("/to", 2);

                if (event1.length == 2 && event2.length == 2) {
                    String description = event1[0].split("event ")[1];
                    Task eventTask = new Event(description, false, event1[1]);
                    list.add(eventTask);

                    System.out.println(BobbyWasabi.addTaskOutput(eventTask, list.size()));
                    continue;
                }

            }

            // no special commands given

            // Store user input
            list.add(new Task(userInput, false));
            Task justAdded = list.get(list.size() - 1);

            // Bot output
            System.out.println(BobbyWasabi.addTaskOutput(justAdded, list.size()));

        }

        System.out.println("""
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
               
                """);

        scanner.close();
    }
}
