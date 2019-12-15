import java.util.ArrayList;
import java.util.Scanner;

class InputReader {
    private Scanner keyboard;
    private static InputReader instance = null;
    private int lineNumber = 0;

    private InputReader() {
        keyboard = new Scanner(System.in);
    }

    //if this InputReader has no instance object create new object call instance, else return that object
    static InputReader getInstance() {
        if (instance == null) {
            instance = new InputReader();
        }
        return instance;
    }

    //crate an arraylist
    ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        String line = "";
        lineNumber = 0;

        try {
            //when it is not the end of input
            while (keyboard.hasNext()) {
                lineNumber++;
                line = keyboard.nextLine();
                //when
                if (line.startsWith("PRINT ")) {
                    commands.add(makePrintCommand(line));
                } else if (line.startsWith("BEGIN_")) {
                    commands.add(makeBlockCommand(line));
                } else if (line.equals("FINISH")) {
                    break;
                } else if (!line.equals("")) {
                    System.out.println(line);
                    throw new BadCommandException("Invalid command.");
                }
            }
        } catch (BadCommandException e) {
            throw new BadCommandException("Line " + lineNumber + " : " + e.getMessage());
        }
        return commands;
    }


    private Command makeBlockCommand(String line) {
        // Removes "BEGIN_" from the current line to get the command type;
        BlockCommand command = new BlockCommand(line.substring(6));

        while (keyboard.hasNext()) {
            lineNumber ++;
            line = keyboard.nextLine();
            //if it starts with END_ and blocktype is the same return command, if empty do nothing
            if (line.equals("END_" + command.getBlockType())) {
                return command;
            } else if (line.equals("")) {
            }
            else {
                //if the line is invalid, like no longer inputing the customer info
                String [] tokens = line.split(" ", 3);
                if (tokens.length != 3 || tokens[1].length() != 1)
                    throw new BadCommandException("Invalid tag.");
                command.addTag(new Tag(tokens));
            }
        }
        return command;
    }

    //after it enters all customer info and reach the print part
    private Command makePrintCommand(String line) {
        String[] tokens = line.split(" ", 5);
        if (tokens.length > 4) {
            throw new BadCommandException("Invalid print command; too many tokens.");
        } else if (tokens.length < 4) {
                throw new BadCommandException("Invalid print command; too few tokens.");
        }
        return new PrintCommand(tokens);
    }
}