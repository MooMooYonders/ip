import java.util.Scanner;
import java.util.ArrayList;

public class BobbyWasabi {

    public static boolean isValidInteger(String s, int arrLen) throws BobbyWasabiException {
        String[] wordList = s.split(" ");

        // not valid command length
        if (wordList.length != 2) {
            throw new BobbyWasabiException("To use the mark or unmark command, we only accept two inputs - the command and the integer");
        }


        // not a valid integer
        try {
            int indx = Integer.parseInt(wordList[1]);
            if (indx > arrLen) {
                throw new BobbyWasabiException("Index given in input is out of range, please try an index within the range of your list");
            }

        } catch (NumberFormatException e) {
            throw new BobbyWasabiException("Please input an index following your command");
        }

        return true;
    }

    public static String addTaskOutput(Task task, int num) {

        String s = String.format("""
                ____________________________________________________________
                Got it. I've added this task:
                    %s
                Now you have %d tasks in the list.
                ____________________________________________________________
                """,
                task, num);

        return s;
    }

    public static String generateErrorMsg(String e) {

        String s = String.format("""
                ____________________________________________________________
                OOPS!!! %s
                ____________________________________________________________
                """,
                e);

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

            String command = userInput.split(" ")[0];


            // if the user says bye
            if (command.equals("bye")) {
                break;
            }

            // if the user asks for list
            if (command.equals("list")) {
                StringBuilder textList = new StringBuilder("Here are the tasks in your list:\n");

                for (int i = 0; i < list.size(); i++) {
                    Task cur = list.get(i);

                    String curTask = String.format("%d. %s\n", i + 1, cur);
                    textList.append(curTask);
                }


                String output = decoLine + "\n" + textList.toString() + decoLine;

                System.out.println(output);

                continue;
            }



            // checks if the user wants to mark/unmark task
            if (command.equals("mark") || command.equals("unmark")) {
                try {
                    if (BobbyWasabi.isValidInteger(userInput, list.size())) {
                        String[] wordList = userInput.split(" ");
                        int indx = Integer.parseInt(wordList[1]);
                        Task targetTask = list.get(indx - 1);

                        if (command.equals("mark")) {
                            targetTask.setIsMarked(true);
                            String curTask = String.format(
                                    "%d. %s\n",
                                    indx,
                                    targetTask);

                            String output = String.format("""
                                            Nice! I've marked this task as done:
                                               %s""",
                                    curTask);

                            System.out.println(decoLine + "\n" + output + decoLine);
                        } else {
                            targetTask.setIsMarked(false);
                            String curTask = String.format(
                                    "%d. %s\n",
                                    indx,
                                    targetTask);

                            String output = String.format("""
                                            Nice! I've marked this task as not done yet:
                                               %s""",
                                    curTask);

                            System.out.println(decoLine + "\n" + output + decoLine);
                        }

                        continue;
                    }
                } catch (BobbyWasabiException e) {
                    System.out.println(BobbyWasabi.generateErrorMsg(e.getMessage()));
                    continue;
                }
            }


            // Todo, Deadline or Event
            try {
                if (command.equals("todo")) {

                    String[] descriptions = userInput.split("todo ");

                    if (descriptions.length <= 1) {
                        throw new BobbyWasabiException("Plase provide a description for todo");
                    }

                    String description = descriptions[1];

                    if (description == "") {
                        throw new BobbyWasabiException("Plase provide a description for todo");
                    }

                    Task todo = new ToDo(description, false);
                    list.add(todo);

                    System.out.println(BobbyWasabi.addTaskOutput(todo, list.size()));
                    continue;

                } else if (command.equals("deadline")) {
                    String[] bySplit = userInput.split("/by", 2);

                    if (bySplit.length < 2) {
                        throw new BobbyWasabiException("You did not provide the deadline!");
                    }

                    String[] descriptions = bySplit[0].split("deadline ");

                    if (descriptions.length < 2) {
                        throw new BobbyWasabiException("The deadline task description cannot be blank!");
                    }

                    if (descriptions[1].trim().isEmpty()) {
                        throw new BobbyWasabiException("The deadline task description cannot be blank!");
                    }

                    String deadline = bySplit[1];

                    if (deadline.trim().isEmpty()) {
                        throw new BobbyWasabiException("The deadline cannot be blank!");
                    }

                    Task deadlineTask = new Deadline(descriptions[1], false, bySplit[1]);
                    list.add(deadlineTask);

                    System.out.println(BobbyWasabi.addTaskOutput(deadlineTask, list.size()));
                    continue;


                } else if (command.equals("event")) {
                    String[] fromSplit = userInput.split("/from", 2);

                    // there was no /from command
                    if (fromSplit.length < 2) {
                        throw new BobbyWasabiException("You did not provide the start duration!");
                    }


                    String[] toSplit = fromSplit[1].split("/to", 2);

                    // there was no /to command
                    if (toSplit.length < 2) {
                        throw new BobbyWasabiException("You did not provide the end duration!");
                    }

                    // starting description is empty
                    if (toSplit[0].trim().isEmpty()) {
                        throw new BobbyWasabiException("Your starting duration is blank!");
                    } else if (toSplit[1].trim().isEmpty()) {
                        throw new BobbyWasabiException("Your ending duration is blank!");
                    }


                    String[] descriptions = fromSplit[0].split("event ");

                    if (descriptions.length < 2) {
                        throw new BobbyWasabiException("The event description cannot be blank!");
                    }


                    if (descriptions[1].trim().isEmpty()) {
                        throw new BobbyWasabiException("You did not provide a description of your event!");
                    }

                    Task eventTask = new Event(descriptions[1], false, toSplit[0], toSplit[1]);
                    list.add(eventTask);

                    System.out.println(BobbyWasabi.addTaskOutput(eventTask, list.size()));
                    continue;


                }
            } catch (BobbyWasabiException e) {
                System.out.println(BobbyWasabi.generateErrorMsg(e.getMessage()));
                continue;
            }

            // no special commands given
            System.out.println(BobbyWasabi.generateErrorMsg("Please provide a valid command!"));

        }

        System.out.println("""
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
               
                """);

        scanner.close();
    }
}
