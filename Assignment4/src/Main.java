import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        try {
            //return instance of Inputreader, and add those to arraylist
            InputReader inputReader = InputReader.getInstance();
            ArrayList<Command> commands = inputReader.getCommands();
            Iterator<Command> currentCommand = commands.iterator();

            CommandHandler commandHandler = new CommandHandler(new Database());

            //when the currentCommand haven't end, get the next command
            while (currentCommand.hasNext()) {
                commandHandler.run(currentCommand.next());
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (BadCommandException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}
