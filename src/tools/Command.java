package tools;


import java.util.List;
import java.util.ArrayList;

/**
 * This class is to store the command sent by the user.
 * It shares the same similarity as a Message object except
 * that a command may also has options.
 *
 * Each option is modeled as a TextPair object. A command
 * may have no option, one option, or multiple options.
 *
 * This class support the following methods:
 * - addOption (to add an option)
 * - getOption (to get the value of an option)
 * - getOptions (to get all options)
 */
public class Command extends Message {
    //TODO - add data members and methods
    ArrayList<TextPair> options = new ArrayList<>();
    public Command(String globalName, String id, String commandString, boolean equals) {
        super(globalName, id, commandString, equals);
    }
    public void addOption(String name, String value) {
        options.add(new TextPair(name, value));
    }
    public TextPair getOption(int i) {
        return options.get(i);
    }
    public ArrayList<TextPair> getOptions() {
        return options;
    }
    public String getSenderID() {
        return super.getId();
    }
}
